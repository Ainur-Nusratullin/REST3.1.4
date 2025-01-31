package ru.nusratullin.bootcrud.ProjectBoot.service;



import ru.nusratullin.bootcrud.ProjectBoot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    void save(User user);

    void deleteById(int id);

    void edit(User user);

    User getById(int id);
}

