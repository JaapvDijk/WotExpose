package com.learningjava.wotapi.infrastructure.persistance;

import com.learningjava.wotapi.shared.constant.RegionType;
import com.learningjava.wotapi.shared.constant.RoleType;
import com.learningjava.wotapi.infrastructure.persistance.entity.Privilege;
import com.learningjava.wotapi.infrastructure.persistance.entity.Role;
import com.learningjava.wotapi.infrastructure.persistance.entity.User;
import com.learningjava.wotapi.infrastructure.persistance.entity.worldoftanks.Vehicle;
import com.learningjava.wotapi.infrastructure.persistance.entity.worldoftanks.VehicleKey;
import com.learningjava.wotapi.infrastructure.persistance.repo.PrivilegeRepository;
import com.learningjava.wotapi.infrastructure.persistance.repo.RoleRepository;
import com.learningjava.wotapi.infrastructure.persistance.repo.UserRepository;
import com.learningjava.wotapi.infrastructure.persistance.repo.VehicleRepository;
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
    private static final String USER_PASSWORD = "user123";

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

        var readPrivilege = new Privilege();
        readPrivilege.setName("READ_PRIVILEGE");
        Privilege writePrivilege = new Privilege();
        writePrivilege.setName("WRITE_PRIVILEGE");
        privilegeRepository.saveAll(Arrays.asList(readPrivilege, writePrivilege));

        var adminRole = new Role();
        adminRole.setRole(RoleType.ADMIN);
        adminRole.setPrivileges(Set.of(readPrivilege, writePrivilege));
        roleRepository.save(adminRole);

        var userRole = new Role();
        userRole.setRole(RoleType.USER);
        userRole.setPrivileges(Set.of(readPrivilege));
        roleRepository.save(userRole);

        var admin = new User();
        admin.setFullName("admin");
        admin.setEmail("admin@wotapi.nl");
        admin.setPassword(passwordEncoder.encode(USER_PASSWORD));
        admin.setRoles(Set.of(adminRole));
        adminUser = userRepository.save(admin);

        normalUser = new User();
        normalUser.setFullName("user");
        normalUser.setEmail("user@wotapi.nl");
        normalUser.setPassword(passwordEncoder.encode(USER_PASSWORD));
        normalUser.setRoles(Set.of(userRole));
        userRepository.save(normalUser);

        for (int i = 0; i < 100; i++)
        {
            var user = new User();
            user.setFullName("user"+i);
            user.setEmail("user"+i+"@wotapi.nl");
            user.setPassword(passwordEncoder.encode(USER_PASSWORD));
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
        }
    }

    private void addMissingVehicles() {
        //TODO: add missing for NA & ASIA
        //TODO Add -64833 research what it is
        var missingVehicles = new ArrayList<>(List.of(
            new Vehicle(LocalDate.now(), new VehicleKey(17153, RegionType.EU), "Obj. 430B", "Object 430B", "ussr", false, "", "mediumTank", 10, true, new Vehicle.Images()),
            new Vehicle(LocalDate.now(), new VehicleKey(16913, RegionType.EU), "Waffenträger auf E 100", "Waffenträger auf E 100", "germany", false, "", "AT-SPG", 10, true, new Vehicle.Images()),
            new Vehicle(LocalDate.now(), new VehicleKey(14337, RegionType.EU), "Object 263B", "Object 263B", "ussr", false, "", "AT-SPG", 10, true, new Vehicle.Images()),
            new Vehicle(LocalDate.now(), new VehicleKey(12033, RegionType.EU), "SU-122-5", "SU-122-5", "ussr", false, "", "AT-SPG", 9, true, new Vehicle.Images()),
            new Vehicle(LocalDate.now(), new VehicleKey(56833, RegionType.EU), "T-44-122", "T-44-122", "ussr", true, "", "AT-SPG", 7, true, new Vehicle.Images())
        ));

        vehicleRepository.saveAll(missingVehicles);
    }
}