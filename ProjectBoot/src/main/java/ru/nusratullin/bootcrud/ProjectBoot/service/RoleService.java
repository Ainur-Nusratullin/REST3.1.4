package ru.nusratullin.bootcrud.ProjectBoot.service;

import ru.nusratullin.bootcrud.ProjectBoot.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();
}
