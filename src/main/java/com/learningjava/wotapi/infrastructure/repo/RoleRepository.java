package com.learningjava.wotapi.infrastructure.repo;

import com.learningjava.wotapi.infrastructure.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}