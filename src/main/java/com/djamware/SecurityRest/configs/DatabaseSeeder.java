package com.djamware.SecurityRest.configs;

import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.djamware.SecurityRest.models.Role;
import com.djamware.SecurityRest.models.User;
import com.djamware.SecurityRest.repositories.RoleRepository;
import com.djamware.SecurityRest.repositories.UserRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(RoleRepository roleRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Seed ADMIN role
        Role adminRole = roleRepository.findByRole("ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setRole("ADMIN");
            roleRepository.save(adminRole);
            System.out.println("Seeded ADMIN role.");
        }

        // Seed default admin user
        if (userRepository.findByEmail("admin@djamware.com") == null) {
            User adminUser = new User();
            adminUser.setFullname("Admin");
            adminUser.setEmail("admin@djamware.com");
            adminUser.setPassword(passwordEncoder.encode("admin123")); // Change in production!
            adminUser.setRoles(Collections.singleton(adminRole));
            userRepository.save(adminUser);
            System.out.println("Seeded admin user: admin@djamware.com / admin123");
        }
    }
}
