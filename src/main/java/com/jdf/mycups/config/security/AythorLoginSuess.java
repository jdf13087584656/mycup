package com.jdf.mycups.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("myAuthenticationSuccessHandler")
public class AythorLoginSuess extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    JwtAuthorService jwtAuthorService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //成功後將token写入head
        jwtAuthorService.addAuthenticatiotoHttpHeader(response, authentication);

    }


}
