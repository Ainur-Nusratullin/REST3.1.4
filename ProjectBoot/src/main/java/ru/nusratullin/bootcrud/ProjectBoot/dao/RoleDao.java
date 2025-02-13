package ru.nusratullin.bootcrud.ProjectBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nusratullin.bootcrud.ProjectBoot.model.Role;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
