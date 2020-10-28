package com.suman.basicAppWithRolePriviledge.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponse<T> {
    private String message;
    private T userInfo;
    private JwtAuthenticationResponse jwt;
}
