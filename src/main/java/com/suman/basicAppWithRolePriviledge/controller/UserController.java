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

	@PostMapping("/signUpAsEmployee")
	public CustomResponseEntity<?> signUpAsEmployee(@RequestBody UserModel userModel) {
		return userServiceApi.signUpAsEmployee(userModel);
	}

	@PostMapping("/signUpAsCustomer")
	public CustomResponseEntity<?> signUpAsCustomer(@RequestBody UserModel userModel) {
		return userServiceApi.signUpAsCustomer(userModel);
	}

	@PostMapping("/signUpAsPublic")
	public CustomResponseEntity<?> signUpAsPublic(@RequestBody UserModel userModel) {
		return userServiceApi.signUpAsPublic(userModel);
	}

	@PostMapping("/signIn")
	public CustomResponseEntity<?> signIn(@RequestBody LoginUser loginUser) {
		return userServiceApi.signIn(loginUser);
	}
}
