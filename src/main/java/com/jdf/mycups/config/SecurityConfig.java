package com.jdf.mycups.config;

import com.jdf.mycups.config.security.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;
    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置不需要经过spring security保护的资源
        web.ignoring()
                .antMatchers("/403", "/404", "/405", "/500")
                .antMatchers("/assets/**")
                .antMatchers("/libs/**")
//                .antMatchers("/WEB-INF/jsp/**")
                .antMatchers("/favicon.ico")
        ;
    }
    @Override
    public void configure(HttpSecurity http)throws Exception{
        http.formLogin().loginPage("/login").loginProcessingUrl("/login/form").failureUrl("/login-error").permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable();

    }
    @Override
    public void configure(AuthenticationManagerBuilder auto)throws Exception{
        auto.authenticationProvider(myAuthenticationProvider);

    }


}
