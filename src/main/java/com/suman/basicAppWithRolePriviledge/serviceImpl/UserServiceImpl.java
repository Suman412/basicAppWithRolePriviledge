package com.suman.basicAppWithRolePriviledge.serviceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.suman.basicAppWithRolePriviledge.model.Role;
import com.suman.basicAppWithRolePriviledge.model.User;
import com.suman.basicAppWithRolePriviledge.payload.CustomResponseEntity;
import com.suman.basicAppWithRolePriviledge.payload.JwtAuthenticationResponse;
import com.suman.basicAppWithRolePriviledge.payload.LoginUser;
import com.suman.basicAppWithRolePriviledge.payload.SignUpResponse;
import com.suman.basicAppWithRolePriviledge.payload.UserModel;
import com.suman.basicAppWithRolePriviledge.repository.RoleRepository;
import com.suman.basicAppWithRolePriviledge.repository.UserRepository;
import com.suman.basicAppWithRolePriviledge.security.JwtTokenProvider;
import com.suman.basicAppWithRolePriviledge.security.UserPrincipal;
import com.suman.basicAppWithRolePriviledge.service.UserServiceApi;

@Service
public class UserServiceImpl implements UserServiceApi {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Value("${app.jwtCmsExpirationInSec}")
	private int jwtCmsExpirationInSec;

	@Value("${app.jwtExpirationInSec}")
	private int jwtExpirationInMs;

	@Override
	public CustomResponseEntity<?> signUpAsEmployee(UserModel userModel) {
		List<String> roles = Arrays.asList("EMPLOYEE_USER");
		if (Boolean.TRUE.equals(userRepository.existsByEmail(userModel.getEmail()))) {
			return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.BAD_REQUEST,
					new SignUpResponse<>("Email Address already in use!", null, null));
		}
		User user = new User();
		user.setFullName(userModel.getFullName());
		user.setEmail(userModel.getEmail().toLowerCase());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		Set<String> roleNames = new HashSet<>(roles);
		Set<Role> userRole = roleRepository.findByNames(roleNames);
		user.setRoles(userRole);
		user.setIsActive(true);
		User save = userRepository.save(user);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userModel.getEmail().toLowerCase(),
						userModel.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		String refreshToken = tokenProvider.generateRefreshToken(authentication);
		return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.OK,
				new SignUpResponse<>("User registered successfully!", save,
						new JwtAuthenticationResponse(jwt, refreshToken)));
	}
	@Override
	public CustomResponseEntity<?> signUpAsCustomer(UserModel userModel) {
		List<String> roles = Arrays.asList("CUSTOMER_USER");
		if (Boolean.TRUE.equals(userRepository.existsByEmail(userModel.getEmail()))) {
			return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.BAD_REQUEST,
					new SignUpResponse<>("Email Address already in use!", null, null));
		}
		User user = new User();
		user.setFullName(userModel.getFullName());
		user.setEmail(userModel.getEmail().toLowerCase());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		Set<String> roleNames = new HashSet<>(roles);
		Set<Role> userRole = roleRepository.findByNames(roleNames);
		user.setRoles(userRole);
		user.setIsActive(true);
		User save = userRepository.save(user);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userModel.getEmail().toLowerCase(), userModel.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		String refreshToken = tokenProvider.generateRefreshToken(authentication);
		return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.OK, new SignUpResponse<>(
				"User registered successfully!", save, new JwtAuthenticationResponse(jwt, refreshToken)));
	}

	@Override
	public CustomResponseEntity<?> signUpAsPublic(UserModel userModel) {
		List<String> roles = Arrays.asList("PUBLIC_USER");
		if (userRepository.existsByEmail(userModel.getEmail())) {
			return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.BAD_REQUEST,
					new SignUpResponse<>("Email Address already in use!", null, null));
		}
		User user = new User();
		user.setFullName(userModel.getFullName());
		user.setEmail(userModel.getEmail().toLowerCase());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		Set<String> roleNames = new HashSet<>(roles);
		Set<Role> userRole = roleRepository.findByNames(roleNames);
		user.setRoles(userRole);
		user.setIsActive(true);
		User save = userRepository.save(user);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userModel.getEmail().toLowerCase(), userModel.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		String refreshToken = tokenProvider.generateRefreshToken(authentication);
		return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.OK, new SignUpResponse<>(
				"User registered successfully!", save, new JwtAuthenticationResponse(jwt, refreshToken)));
	}

	@Override
	public CustomResponseEntity<?> signIn(LoginUser loginUser) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		Optional<User> byId = userRepository.findById(userPrincipal.getId());
		if (byId.isPresent()) {
			String jwt = tokenProvider.generateToken(authentication);
			String refreshToken = tokenProvider.generateRefreshToken(authentication);
			return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.OK,
					new JwtAuthenticationResponse(jwt, refreshToken));
		}
		return new CustomResponseEntity<>(LocalDateTime.now(), HttpStatus.OK,
				new JwtAuthenticationResponse(null, null));
	}
}
