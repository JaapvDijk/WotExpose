package com.learningjava.wotapi.api;

import com.learningjava.wotapi.api.model.entity.Privilege;
import com.learningjava.wotapi.api.model.entity.Role;
import com.learningjava.wotapi.api.model.entity.User;
import com.learningjava.wotapi.api.repo.PrivilegeRepository;
import com.learningjava.wotapi.api.repo.RoleRepository;
import com.learningjava.wotapi.api.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InitDbService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    public InitDbService(UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Privilege readPrivilege = new Privilege();
        readPrivilege.setName("READ_PRIVILEGE");
        Privilege writePrivilege = new Privilege();
        writePrivilege.setName("WRITE_PRIVILEGE");
        privilegeRepository.saveAll(Arrays.asList(readPrivilege, writePrivilege));

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        adminRole.setPrivileges(Arrays.asList(readPrivilege, writePrivilege));
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        userRole.setPrivileges(List.of(readPrivilege));
        roleRepository.save(userRole);

        User admin = new User();
        admin.setFullName("admin");
        admin.setEmail("admin@wotapi.nl");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRoles(List.of(adminRole));
        userRepository.save(admin);

        User user = new User();
        user.setFullName("user");
        user.setEmail("user@wotapi.nl");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setRoles(List.of(userRole));
        userRepository.save(user);
    }
}