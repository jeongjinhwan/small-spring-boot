package com.jh.simply.auth.Authentication.service;

import lombok.extern.slf4j.Slf4j;
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
        // UserMapper um = sqlSession.getMapper(UserMapper.class);
        // return um.login(userDto);
        Optional<UserDto> outUserDto = Optional.of(new UserDto(1,"gaebogchi","shinhan@1","jh","S"));
        log.debug(userDto.getUserId());
        log.debug(outUserDto.get().getUserId());
        
        if(userDto.getUserId().equals(outUserDto.get().getUserId()) && userDto.getUserPw().equals(outUserDto.get().getUserPw())){
            return outUserDto;
        }
        else
        {
            return null;
        }
    }

    public List<UserDto> selectUserList(UserDto userDto) {
        // UserMapper um = sqlSession.getMapper(UserMapper.class);
        // return um.selectUserList(userDto);
        return null;
    }
}
