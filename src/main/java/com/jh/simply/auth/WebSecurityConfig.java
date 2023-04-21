package com.jh.simply.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jh.simply.auth.Authentication.filter.CustomAuthFilter;
import com.jh.simply.auth.Authentication.handler.CustomAuthFailureHandler;
import com.jh.simply.auth.Authentication.handler.CustomAuthSuccessHandler;
import com.jh.simply.auth.Authentication.provider.CustomAuthenticationProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
// @EnableWebSecurity
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            // .authorizeHttpRequests(authz -> {
            //     authz.antMatchers("asdf").permitAll().anyRequest().authenticated();
            // })
            .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
            
            ;

        // [STEP7] 최종 구성한 값을 사용함.
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(customAuthenticationProvider());
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(bCryptPasswordEncoder());
    }
    
    @Bean
    public CustomAuthSuccessHandler customLoginSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

    @Bean
    public CustomAuthFailureHandler customLoginFailureHandler() {
        return new CustomAuthFailureHandler();
    }

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CustomAuthFilter customAuthenticationFilter() throws Exception{
        log.debug("0.WebSecurityConfig");
        CustomAuthFilter customAuthenticationFilter = new CustomAuthFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/cuslogin");     // 접근 URL
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());    // '인증' 성공 시 해당 핸들러로 처리를 전가한다.
        customAuthenticationFilter.setAuthenticationFailureHandler(customLoginFailureHandler());    // '인증' 실패 시 해당 핸들러로 처리를 전가한다.
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }
}
