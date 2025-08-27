package com.learningjava.wotapi.infrastructure.repo;

import com.learningjava.wotapi.infrastructure.model.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}