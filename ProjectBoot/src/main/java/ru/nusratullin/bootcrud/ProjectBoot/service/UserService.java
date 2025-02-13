package ru.nusratullin.bootcrud.ProjectBoot.service;

import ru.nusratullin.bootcrud.ProjectBoot.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    void saveUser(String name, String surname, int age, String email, String password, Set<String> roleNames);

    Optional<User> findByEmail(String name);

    void createUser(String name, String surname, int age, String email, String password, Set<String> roleNames);

    List<User> readAllUser();

    Optional<User> readUserById(Long id);

    void updateUser(Long id, String name, String surname, int age, String email, String password, Set<String> roleNames);

    void deleteUserById(Long id);

    User getUserHome(User user);

    boolean checkIfAdmin(User user);
}
