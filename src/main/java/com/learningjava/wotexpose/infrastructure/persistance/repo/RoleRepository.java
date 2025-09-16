package com.learningjava.wotexpose.infrastructure.persistance.repo;

import com.learningjava.wotexpose.infrastructure.persistance.entity.Role;
import com.learningjava.wotexpose.shared.constant.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}