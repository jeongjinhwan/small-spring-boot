package com.jh.simply.auth.Authentication.model;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    private String userId;
    private String userPw;
}
