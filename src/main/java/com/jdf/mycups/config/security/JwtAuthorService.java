package com.jdf.mycups.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdf.mycups.common.Result;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class JwtAuthorService {


    /**
     * 过期时间 2小时
     */
    // TODO: 目前过期时间是1年，调试方便。
    static final long EXPIRATIONTIME = 1000L * 60 * 60 * 24 * 30 * 12;

    /**
     * JWT 密码
     */
    static final String SECRET = "yearcon";
    /**
     * TOKEN前缀
     */
    static final String TOKEN_PREFIX = "Bearer ";
    /**
     * 存放Token的Header Key
     */
    static final String HEADER_STRING = "token";

    /**
     * 自定义的 playload
     */
    static final String AUTHORITIES = "authorities";

    /**
     * 自定义的 playload
     */
    static final String ROLE = "roles";


    /**
     * 将jwt token 写入header头部和body中
     *
     * @param response
     * @param authentication
     */
    public static void addAuthenticatiotoHttpHeader(HttpServletResponse response, Authentication authentication) {

        String token = Jwts.builder()
                //生成token的时候可以把自定义数据加进去,比如用户权限
                .claim(AUTHORITIES, "AUTH_WRITE")
                .claim(ROLE, "ROLE_ADMIN,ROLE_USER")
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        //把token设置到响应头中去
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        try {
            //设置返回格式
            Result result = Result.init();
            result.setData("token", TOKEN_PREFIX + token);
            ObjectMapper mapper = new ObjectMapper();
            response.setHeader("Content-type", "text/html;charset=utf-8");
            response.getWriter().write(mapper.writeValueAsString(result));
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
    }


    /**
     * 从请求头中解析出 Authentication
     *
     * @param request
     * @return
     */
    public static Authentication getAuthentication(HttpServletRequest request) throws Exception {
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);
        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            return null;
        }
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new CredentialsExpiredException("登录过期");
        } catch (JwtException e) {
            throw new BadCredentialsException("登录Token错误。");
        }

        String auth = (String) claims.get(AUTHORITIES);
        // 得到 权限（角色）
        List<GrantedAuthority> authorities = AuthorityUtils.
                commaSeparatedStringToAuthorityList((String) claims.get(AUTHORITIES));
        //得到用户名
        String username = claims.getSubject();
        //得到过期时间
        Date expiration = claims.getExpiration();
        //判断是否过期
        Date now = new Date();
        if (now.getTime() > expiration.getTime()) {
            throw new CredentialsExpiredException("该账号已过期,请重新登陆");
        }

        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, token, authorities);
        }
        return null;
    }
}
