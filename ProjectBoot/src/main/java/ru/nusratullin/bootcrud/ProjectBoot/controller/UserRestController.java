package ru.nusratullin.bootcrud.ProjectBoot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nusratullin.bootcrud.ProjectBoot.dao.UserDao;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserDao userDao;

    public UserRestController(UserDao userRepository) {
        this.userDao = userRepository;
    }

    @GetMapping("/profile_user")
    public ResponseEntity<Optional<User>> getAuthorizedUser(@AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> user = userDao.findByUsername(userDetails.getUsername());
        System.out.println(user);
        return ResponseEntity.ok(user);
    }
}

