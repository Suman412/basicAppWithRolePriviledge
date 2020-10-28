package com.suman.basicAppWithRolePriviledge.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {
	private String email;
	private String password;
}
