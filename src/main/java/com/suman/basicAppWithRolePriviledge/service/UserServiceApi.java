package com.suman.basicAppWithRolePriviledge.service;

import org.springframework.stereotype.Component;

import com.suman.basicAppWithRolePriviledge.payload.CustomResponseEntity;
import com.suman.basicAppWithRolePriviledge.payload.LoginUser;
import com.suman.basicAppWithRolePriviledge.payload.UserModel;

@Component
public interface UserServiceApi {

	CustomResponseEntity<?> signUpAsAdmin(UserModel userModel);

	CustomResponseEntity<?> signUpAsUser(UserModel userModel);

	CustomResponseEntity<?> signIn(LoginUser loginUser);

}
