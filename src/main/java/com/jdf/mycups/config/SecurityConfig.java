package com.jdf.mycups.config;

import com.jdf.mycups.config.security.AythorLoginSuess;
import com.jdf.mycups.config.security.Filter.TokenAuthenticationFilter;
import com.jdf.mycups.config.security.MyAuthenticationProvider;
import com.jdf.mycups.config.security.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;
    @Autowired
    AythorLoginSuess aythorLoginSuess;
//    @Autowired
//    TokenAuthenticationFilter tokenAuthenticationFilter;
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
//        .defaultSuccessUrl("/index").
        http.formLogin().loginPage("/login").loginProcessingUrl("/login/form").failureUrl("/login-error").successHandler(aythorLoginSuess).permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .authorizeRequests()
                .antMatchers("/index").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement()                        // 定制我们自己的 session 策略
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 调整为让 Spring Security 不创建和使用 session


        http.addFilterBefore(new TokenAuthenticationFilter(authenticationManager(),new UnauthorizedEntryPoint()), UsernamePasswordAuthenticationFilter.class);

    }
    @Override
    public void configure(AuthenticationManagerBuilder auto)throws Exception{
        auto.eraseCredentials(false);
        auto.authenticationProvider(myAuthenticationProvider);

    }


}
