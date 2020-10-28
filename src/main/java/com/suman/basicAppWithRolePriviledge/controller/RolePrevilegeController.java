package com.suman.basicAppWithRolePriviledge.controller;

import java.time.LocalDateTime;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.suman.basicAppWithRolePriviledge.payload.CustomResponseEntity;

@RestController
public class RolePrevilegeController {
	@PreAuthorize(value = "hasAuthority('EMPLOYEE_USER')")
	@GetMapping("/employeeRoleTest")
	public CustomResponseEntity<?> employeeRoleTest(@RequestHeader("Authorization") String authorization) {
		return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.OK, "employee-role");
	}

	@PreAuthorize(value = "hasAuthority('CUSTOMER_USER')")
	@GetMapping("/customerRoleTest")
	public CustomResponseEntity<?> customerRoleTest(@RequestHeader("Authorization") String authorization) {
		return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.OK, "customer-role");
	}

//	@Secured(value = "ROLE_PUBLIC")
	@RolesAllowed("PUBLIC_USER")
	// @PreAuthorize("hasAuthority('ROLE_PUBLIC')")
	// @PreAuthorize(value = "hasAuthority('PUBLIC_USER')")
	@GetMapping("/publicRoleTest")
	public CustomResponseEntity<?> publicRoleTest(@RequestHeader("Authorization") String authorization) {
		return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.OK, "public-role");
	}

	@PreAuthorize(value = "hasAuthority('read')")
	@GetMapping("/readPrivilegeTest")
	public CustomResponseEntity<?> readPrivilegeTest(@RequestHeader("Authorization") String authorization) {
		return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.OK, "read-privilege");
	}

	@PreAuthorize(value = "hasAuthority('write')")
	@GetMapping("/writePrivilegeTest")
	public CustomResponseEntity<?> writePrivilegeTest(@RequestHeader("Authorization") String authorization) {
		return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.OK, "write-privilege");
	}

	@PreAuthorize(value = "hasAuthority('update')")
	@GetMapping("/updatePrivilegeTest")
	public CustomResponseEntity<?> updatePrivilegeTest(@RequestHeader("Authorization") String authorization) {
		return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.OK, "update-privilege");
	}
}
