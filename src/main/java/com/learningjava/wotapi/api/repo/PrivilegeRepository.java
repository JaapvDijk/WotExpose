package com.learningjava.wotapi.api.repo;

import com.learningjava.wotapi.api.model.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}