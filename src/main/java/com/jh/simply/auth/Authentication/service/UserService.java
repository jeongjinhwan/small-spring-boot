package com.jh.simply.auth.Authentication.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;

import com.jh.simply.auth.Authentication.model.UserDto;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    /**
     * 로그인 구현체
     *
     * @param userDto UserDto
     * @return Optional<UserDto>
     */
    public Optional<UserDto> login(UserDto userDto) {
        Optional<UserDto> outUserDto = Optional.of(new UserDto("gaebogchi","shinhan@1"));
        log.debug(userDto.getUserId());
        log.debug(outUserDto.get().getUserId());
        
        return outUserDto;
    }

    public List<UserDto> selectUserList(UserDto userDto) {
        return null;
    }
}
