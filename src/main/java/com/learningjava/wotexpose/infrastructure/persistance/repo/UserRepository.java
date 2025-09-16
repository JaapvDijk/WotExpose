package com.learningjava.wotexpose.infrastructure.persistance.repo;

import com.learningjava.wotexpose.infrastructure.persistance.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(attributePaths = {"roles", "roles.privileges"})
    Optional<User> findByEmail(String email);
}
