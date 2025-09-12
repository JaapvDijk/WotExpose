package com.learningjava.wotapi.infrastructure.persistance.repo;

import com.learningjava.wotapi.infrastructure.persistance.entity.Role;
import com.learningjava.wotapi.shared.constant.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}