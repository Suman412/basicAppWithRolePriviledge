package com.suman.basicAppWithRolePriviledge.repository;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suman.basicAppWithRolePriviledge.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Transactional
    @Modifying
    @Query(value = "Select * from t_roles where name in(:roles)", nativeQuery = true)
    Set<Role> findByNames(Set<String> roles);
}
