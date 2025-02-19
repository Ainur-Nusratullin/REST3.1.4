package ru.nusratullin.bootcrud.ProjectBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.nusratullin.bootcrud.ProjectBoot.dao.RoleDao;
import ru.nusratullin.bootcrud.ProjectBoot.dao.UserDao;
import ru.nusratullin.bootcrud.ProjectBoot.model.Role;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;

import java.util.HashSet;
import java.util.Set;


@Component
public class DataLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    @Autowired
    public DataLoader(PasswordEncoder passwordEncoder, UserDao userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {

        Role AdminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        Set<Role> roleAdminUser = new HashSet<>();
        roleAdminUser.add(AdminRole);
        roleAdminUser.add(userRole);
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(userRole);

        User admin = userDao.findByEmail("admin@mail.ru")
                .orElseGet(() -> {
                    User user = new User();
                    user.setUsername("admin");
                    user.setSurname("admin");
                    user.setEmail("admin@mail.ru");
                    user.setAge(27);
                    user.setPassword(passwordEncoder.encode("admin"));
                    user.setRoles(roleAdminUser);
                    return userDao.save(user);
                });

        User user1 = userDao.findByEmail("user@mail.ru")
                .orElseGet(() -> {
                    User user = new User();
                    user.setUsername("user");
                    user.setSurname("user");
                    user.setEmail("user@mail.ru");
                    user.setAge(25);
                    user.setPassword(passwordEncoder.encode("user"));
                    user.setRoles(roleUser);
                    return userDao.save(user);
                });
    }
}

