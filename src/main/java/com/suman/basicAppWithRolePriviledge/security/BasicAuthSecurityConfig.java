package com.suman.basicAppWithRolePriviledge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(1)
public class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf()
                .disable()
                .requestMatchers()
                .antMatchers(
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html"
                )
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/privacy-policy.html"
                )
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().hasRole("BASIC_USER")
                .and()
                .httpBasic().and()
                .exceptionHandling().accessDeniedPage("/403");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser(loginProperties.getServiceNowUser())
//                .password(passwordEncoderAuth().encode(loginProperties.getServiceNowPass()))
//                .roles("BASIC_USER")
//                .and()
				.withUser("suman").password(passwordEncoderAuth().encode("suman"))
                .roles("BASIC_USER");
    }

    @Bean
    public PasswordEncoder passwordEncoderAuth() {
        return new BCryptPasswordEncoder();
    }

}

