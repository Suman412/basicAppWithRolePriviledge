package com.suman.basicAppWithRolePriviledge.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
	private String fullName;

	private String email;

	private String password;

	private String phoneNumber;
}
