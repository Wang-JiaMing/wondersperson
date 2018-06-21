package com.wondersgroup.config;

import com.wondersgroup.service.CustomUserService;
import com.wondersgroup.utils.Md5Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @projectName:securityDemo
 * @packageName:com.wjm.security.config
 * @authorName:wangjiaming
 * @createDate:2018-02-08
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return Md5Util.getMD5String(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                String mdCharSequence = Md5Util.getMD5String(charSequence.toString());
                String md5s = Md5Util.getMD5String(s.toString());
                if (mdCharSequence.equals(s)) {
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/register","/demo").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll().failureUrl("/login?error").permitAll()
                .and()
                .logout().permitAll();


    }
}
