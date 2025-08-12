package com.learningjava.wotapi.api;

import com.learningjava.wotapi.api.model.entity.Privilege;
import com.learningjava.wotapi.api.model.entity.Role;
import com.learningjava.wotapi.api.model.entity.User;
import com.learningjava.wotapi.api.repo.PrivilegeRepository;
import com.learningjava.wotapi.api.repo.RoleRepository;
import com.learningjava.wotapi.api.repo.UserRepository;
import com.learningjava.wotapi.api.constant.Roles;
import lombok.Getter;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Component
@Profile({"dev", "test"})
public class DbSeeder {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Getter
    private User adminUser;
    @Getter
    private User normalUser;
    @Getter
    private final String normalUserPassword = "user123";

    public DbSeeder(UserRepository userRepository,
                    RoleRepository roleRepository,
                    PrivilegeRepository privilegeRepository,
                    BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void init() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        privilegeRepository.deleteAll();

        Privilege readPrivilege = new Privilege();
        readPrivilege.setName("READ_PRIVILEGE");
        Privilege writePrivilege = new Privilege();
        writePrivilege.setName("WRITE_PRIVILEGE");
        privilegeRepository.saveAll(Arrays.asList(readPrivilege, writePrivilege));

        Role adminRole = new Role();
        adminRole.setName(Roles.ROLE_ADMIN);
        adminRole.setPrivileges(Set.of(readPrivilege, writePrivilege));
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName(Roles.ROLE_USER);
        userRole.setPrivileges(Set.of(readPrivilege));
        roleRepository.save(userRole);

        User admin = new User();
        admin.setFullName("admin");
        admin.setEmail("admin@wotapi.nl");
        admin.setPassword(passwordEncoder.encode(normalUserPassword));
        admin.setRoles(Set.of(adminRole));
        adminUser = userRepository.save(admin);

        User user = new User();
        user.setFullName("user");
        user.setEmail("user@wotapi.nl");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setRoles(Set.of(userRole));
        normalUser = userRepository.save(user);
    }
}