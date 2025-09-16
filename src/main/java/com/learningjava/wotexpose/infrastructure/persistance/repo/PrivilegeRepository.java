package com.learningjava.wotexpose.infrastructure.persistance.repo;

import com.learningjava.wotexpose.infrastructure.persistance.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}