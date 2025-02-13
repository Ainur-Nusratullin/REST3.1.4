package ru.nusratullin.bootcrud.ProjectBoot.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.nusratullin.bootcrud.ProjectBoot.model.Role;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;
import ru.nusratullin.bootcrud.ProjectBoot.service.RoleService;
import ru.nusratullin.bootcrud.ProjectBoot.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DataLoader {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    @Transactional
    public void loadData() {
        try {
            if (roleService.findByName("ROLE_USER").isEmpty()) {
                roleService.save(new Role("ROLE_USER"));
            }

            if (roleService.findByName("ROLE_ADMIN").isEmpty()) {
                roleService.save(new Role("ROLE_ADMIN"));
            }
        } catch (Exception e) {
            System.err.println("Ошибка при создании ролей: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        try {
            if (userService.findByEmail("admin@mail.ru").isEmpty()) {
                User admin = new User();
                admin.setName("admin");
                admin.setSurname("admin");
                admin.setAge(27);
                admin.setEmail("admin@mail.ru");
                admin.setPassword("admin");
                Set<Role> adminRoles = new HashSet<>();
                roleService.findByName("ROLE_ADMIN").ifPresent(adminRoles::add);
                roleService.findByName("ROLE_USER").ifPresent(adminRoles::add);
                Set<String> adminRoleNames = adminRoles.stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet());
                userService.saveUser(admin.getName(), admin.getSurname(), admin.getAge(),
                        admin.getEmail(), admin.getPassword(), adminRoleNames);
            }
        } catch (Exception e) {
            System.err.println("Ошибка при создании админа: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try {
            if (userService.findByEmail("user@mail.ru").isEmpty()) {
                User user = new User();
                user.setName("user");
                user.setSurname("user");
                user.setAge(29);
                user.setEmail("user@mail.ru");
                user.setPassword("user");
                Set<Role> userRoles = new HashSet<>();
                roleService.findByName("ROLE_USER").ifPresent(userRoles::add);
                Set<String> userRoleNames = userRoles.stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet());
                userService.saveUser(user.getName(), user.getSurname(), user.getAge(),
                        user.getEmail(), user.getPassword(), userRoleNames);
            }
        } catch (Exception e) {
            System.err.println("Ошибка при создании юзера: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
