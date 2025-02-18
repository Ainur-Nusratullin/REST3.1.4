package ru.nusratullin.bootcrud.ProjectBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.nusratullin.bootcrud.ProjectBoot.dao.RoleRepository;
import ru.nusratullin.bootcrud.ProjectBoot.dao.UserRepository;
import ru.nusratullin.bootcrud.ProjectBoot.model.Role;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;

import java.util.Set;


@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> {
                    Role role = new Role("ADMIN");
                    return roleRepository.save(role);
                });

        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> {
                    Role role = new Role("USER");
                    return roleRepository.save(role);
                });

        User adminUser = userRepository.findByUsername("admin@mail.ru")
                .orElseGet(() -> {
                    User user = new User();
                    user.setUsername("admin@mail.ru");
                    user.setSurname("surname");
                    user.setEmail("admin@mail.ru");
                    user.setAge(39);
                    user.setPassword(passwordEncoder.encode("admin"));
                    user.setRoles(Set.of(adminRole, userRole));
                    return userRepository.save(user);
                });

        User regularUser = userRepository.findByUsername("user@mail.ru")
                .orElseGet(() -> {
                    User user = new User();
                    user.setUsername("user@mail.ru");
                    user.setSurname("surname");
                    user.setEmail("user@mail.ru");
                    user.setAge(25);
                    user.setPassword(passwordEncoder.encode("user"));
                    user.setRoles(Set.of(userRole));
                    return userRepository.save(user);
                });
    }
}

