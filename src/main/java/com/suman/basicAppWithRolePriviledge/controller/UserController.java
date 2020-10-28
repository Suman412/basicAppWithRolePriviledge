package com.suman.basicAppWithRolePriviledge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suman.basicAppWithRolePriviledge.payload.CustomResponseEntity;
import com.suman.basicAppWithRolePriviledge.payload.LoginUser;
import com.suman.basicAppWithRolePriviledge.payload.UserModel;
import com.suman.basicAppWithRolePriviledge.service.UserServiceApi;

@RestController
public class UserController {
	@Autowired
	private UserServiceApi userServiceApi;
	@PostMapping("/signUpAsAdmin")
	public CustomResponseEntity<?> signUpAsAdmin(@RequestBody UserModel userModel) {
		return userServiceApi.signUpAsAdmin(userModel);
	}

	@PostMapping("/signUpAsUser")
	public CustomResponseEntity<?> signUpAsUser(@RequestBody UserModel userModel) {
		return userServiceApi.signUpAsUser(userModel);
	}

	@PostMapping("/signIn")
	public CustomResponseEntity<?> signIn(@RequestBody LoginUser loginUser) {
		return userServiceApi.signIn(loginUser);
	}
}
