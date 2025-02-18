package ru.nusratullin.bootcrud.ProjectBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nusratullin.bootcrud.ProjectBoot.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}