package ru.nusratullin.bootcrud.ProjectBoot.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email")
//    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByEmail(String email);

//    User findByName(String name);
}