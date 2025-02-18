package ru.nusratullin.bootcrud.ProjectBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    @Query("Select u from User u left join fetch u.roles where u.email=:email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("Select u from User u left join fetch u.roles where u.username=:username")
    Optional<User> findByUsername(@Param("username") String username);
}

