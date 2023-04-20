package com.jh.simply.auth.Authentication.provider;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jh.simply.auth.Authentication.model.UserDetailsDto;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    @Resource
    private UserDetailsService userDetailsService;

    @NonNull
    private BCryptPasswordEncoder passwordEncoder;

    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("2.CustomAuthenticationProvider");
        
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        
        log.debug("----------");
        log.debug("{}",token);
        log.debug("{}",token.getName());
        log.debug("{}",token.getCredentials());
        log.debug("----------");
        // 'AuthenticaionFilter' 에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        String userId = token.getName();
        String userPw = (String) token.getCredentials();

        // Spring Security - UserDetailsService를 통해 DB에서 아이디로 사용자 조회
        UserDetailsDto userDetailsDto = (UserDetailsDto) userDetailsService.loadUserByUsername(userId);
        log.debug("---------------------------------------------------------------------------------------------------------------------------------------------------0");
        
        log.debug(userPw);
        log.debug(userDetailsDto.getPassword());
        log.debug(userDetailsDto.getUserPw());

        // if (!userDetailsDto.getPassword().equalsIgnoreCase(userPw) ) {
        //     log.debug("----------------------------------------------------------------------------------------------------------------------------------------------------2");
        //     throw new BadCredentialsException(userDetailsDto.getUsername() + "Invalid password");
        // }
        // return new UsernamePasswordAuthenticationToken(userDetailsDto, userPw, userDetailsDto.getAuthorities());

        if (!(userDetailsDto.getUserPw().equalsIgnoreCase(userPw) )) {
            log.debug("---------------------------------------------------------------------------------------------------------------------------------------------------1");
            throw new BadCredentialsException(userDetailsDto.getUserId() + "Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetailsDto, userPw, userDetailsDto.getAuthorities());
        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
