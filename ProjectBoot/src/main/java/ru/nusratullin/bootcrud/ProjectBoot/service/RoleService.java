package ru.nusratullin.bootcrud.ProjectBoot.service;

import ru.nusratullin.bootcrud.ProjectBoot.model.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(String name);

    void save(Role roleUser);
}
