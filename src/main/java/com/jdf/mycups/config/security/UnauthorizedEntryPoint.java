package com.jdf.mycups.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdf.mycups.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        try {
            response.setHeader("Content-type", "text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Result result = Result.init();
            ObjectMapper mapper = new ObjectMapper();
//            result.setRetCode(HseErroyCode.CODE_030009.getResultCode());
            result.setRetCode("03009");
            result.setReason("登录异常");
            response.getWriter().write(mapper.writeValueAsString(result));
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
            log.error("{}", e);
        }
    }
}
