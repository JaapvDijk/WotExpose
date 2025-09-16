package com.learningjava.wotexpose.infrastructure.persistance;

import com.learningjava.wotexpose.infrastructure.persistance.entity.Privilege;
import com.learningjava.wotexpose.infrastructure.persistance.entity.Role;
import com.learningjava.wotexpose.infrastructure.persistance.entity.User;
import com.learningjava.wotexpose.infrastructure.persistance.repo.PrivilegeRepository;
import com.learningjava.wotexpose.infrastructure.persistance.repo.RoleRepository;
import com.learningjava.wotexpose.infrastructure.persistance.repo.UserRepository;
import com.learningjava.wotexpose.shared.constant.RoleType;
import lombok.Getter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Component
@Profile({"dev", "test"})
public class TestDataSeeder {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Getter
    private User adminUser;
    @Getter
    private User normalUser;
    @Getter
    private static final String USER_PASSWORD = "user123";

    public TestDataSeeder(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PrivilegeRepository privilegeRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        addUsers();
    }

    private void addUsers() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        privilegeRepository.deleteAll();

        var readPrivilege = new Privilege();
        readPrivilege.setName("READ_PRIVILEGE");
        Privilege writePrivilege = new Privilege();
        writePrivilege.setName("WRITE_PRIVILEGE");
        privilegeRepository.saveAll(Arrays.asList(readPrivilege, writePrivilege));

        var adminRole = new Role();
        adminRole.setRoleType(RoleType.ADMIN);
        adminRole.setPrivileges(Set.of(readPrivilege, writePrivilege));
        roleRepository.save(adminRole);

        var userRole = new Role();
        userRole.setRoleType(RoleType.USER);
        userRole.setPrivileges(Set.of(readPrivilege));
        roleRepository.save(userRole);

        var admin = new User();
        admin.setFullName("admin");
        admin.setEmail("admin@api.nl");
        admin.setPassword(passwordEncoder.encode(USER_PASSWORD));
        admin.setRoles(Set.of(adminRole));
        adminUser = userRepository.save(admin);

        normalUser = new User();
        normalUser.setFullName("user");
        normalUser.setEmail("user@api.nl");
        normalUser.setPassword(passwordEncoder.encode(USER_PASSWORD));
        normalUser.setRoles(Set.of(userRole));
        userRepository.save(normalUser);

        for (int i = 0; i < 100; i++)
        {
            var user = new User();
            user.setFullName("user"+i);
            user.setEmail("user"+i+"@api.nl");
            user.setPassword(passwordEncoder.encode(USER_PASSWORD));
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
        }
    }
}