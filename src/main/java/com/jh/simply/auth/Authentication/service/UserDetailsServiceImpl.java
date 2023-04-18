package com.jh.simply.auth.Authentication.service;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jh.simply.auth.Authentication.model.UserDetailsDto;
import com.jh.simply.auth.Authentication.model.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService us) {
        this.userService = us;
    }
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("UserDetailsServiceImpl_{}",username);
        UserDto userDto = UserDto
                .builder()
                .userId(username)
                .build();
            
        if (StringUtils.hasText(username)) 
        {
            return userService.login(userDto)
                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserId()))))
                    .orElseThrow(() -> new AuthenticationServiceException(username));
        }
        else 
        {
            throw new BadCredentialsException(username);
        }
    }
    
}
