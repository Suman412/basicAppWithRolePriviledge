package com.suman.basicAppWithRolePriviledge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suman.basicAppWithRolePriviledge.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmailIgnoreCase(String email);

	// Boolean existsByUsername(String username);

	boolean existsByEmail(String email);

   // Boolean existsByPhoneNumber(String number);
}
