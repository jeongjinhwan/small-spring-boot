package com.jh.simply.auth.Authentication.model;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    private int userSq;
    private String userId;
    private String userPw;
    private String userNm;
    private String userSt = "S";
}
