package ru.nusratullin.bootcrud.ProjectBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nusratullin.bootcrud.ProjectBoot.model.Role;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;
import ru.nusratullin.bootcrud.ProjectBoot.service.UserService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getAllUser(Model model, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userService.getByEmail(username);
        System.out.println(loggedInUser);

        List<String> roles = loggedInUser.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        if (roles.contains("ROLE_USER")) {

            model.addAttribute("allUser", Collections.singletonList(loggedInUser));
        } else {
            List<User> users = userService.getAllUser();
            model.addAttribute("allUser", users);
        }
        return "home";
    }
}
