package com.learningjava.wotapi.api;

import com.learningjava.wotapi.api.model.entity.Privilege;
import com.learningjava.wotapi.api.model.entity.Role;
import com.learningjava.wotapi.api.model.entity.User;
import com.learningjava.wotapi.api.model.worldoftanks.entity.Vehicle;
import com.learningjava.wotapi.api.model.worldoftanks.entity.VehicleKey;
import com.learningjava.wotapi.api.repo.PrivilegeRepository;
import com.learningjava.wotapi.api.repo.RoleRepository;
import com.learningjava.wotapi.api.repo.UserRepository;
import com.learningjava.wotapi.api.constant.Roles;
import com.learningjava.wotapi.api.repo.VehicleRepository;
import lombok.Getter;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
@Profile({"dev", "test"})
public class DbSeeder {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final VehicleRepository vehicleRepository;

    @Getter
    private User adminUser;
    @Getter
    private User normalUser;
    @Getter
    private final String normalUserPassword = "user123";

    public DbSeeder(UserRepository userRepository,
                    RoleRepository roleRepository,
                    PrivilegeRepository privilegeRepository,
                    BCryptPasswordEncoder passwordEncoder,
                    VehicleRepository vehicleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
        this.vehicleRepository = vehicleRepository;
    }

    public void init() {
        addUsers();

        addMissingVehicles();
    }

    private void addUsers() {
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

        for (int i = 0; i < 100; i++)
        {
            user = new User();
            user.setFullName("user"+i);
            user.setEmail("user"+i+"@wotapi.nl");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
        }
    }

    private void addMissingVehicles() {
        //TODO: complete the list, check if args are valid, add missing for NA & ASIA
        //64833 ?
        var missingVehicles = new ArrayList<Vehicle>(List.of(
            new Vehicle(LocalDate.now(), new VehicleKey(17153, "EU"), "Obj. 430B", "Object 430B", "USSR", false, "", "mediumTank", 10, true),
            new Vehicle(LocalDate.now(), new VehicleKey(16913, "EU"), "Waffenträger auf E 100", "Waffenträger auf E 100", "Germany", false, "", "AT-SPG", 10, true),
            new Vehicle(LocalDate.now(), new VehicleKey(14337, "EU"), "Object 263B", "Object 263B", "USSR", false, "", "AT-SPG", 10, true),
            new Vehicle(LocalDate.now(), new VehicleKey(12033, "EU"), "SU-122-5", "SU-122-5", "USSR", false, "", "AT-SPG", 9, true),
            new Vehicle(LocalDate.now(), new VehicleKey(56833, "EU"), "T-44-122", "T-44-122", "USSR", true, "", "AT-SPG", 7, true)
        ));

        vehicleRepository.saveAll(missingVehicles);
    }
}