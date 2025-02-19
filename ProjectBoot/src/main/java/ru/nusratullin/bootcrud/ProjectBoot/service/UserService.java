package ru.nusratullin.bootcrud.ProjectBoot.service;

import ru.nusratullin.bootcrud.ProjectBoot.model.User;

import java.util.List;

public interface UserService {
    List<User> showAllUsers();

    User getUserById(Long id);

    void save(User user);

    void delete(Long id);
}