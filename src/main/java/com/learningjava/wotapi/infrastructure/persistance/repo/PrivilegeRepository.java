package com.learningjava.wotapi.infrastructure.persistance.repo;

import com.learningjava.wotapi.infrastructure.persistance.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}