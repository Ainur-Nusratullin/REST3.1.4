package ru.nusratullin.bootcrud.ProjectBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;
import ru.nusratullin.bootcrud.ProjectBoot.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("principalUser", userService.getUserHome(user));
        model.addAttribute("isAdmin", userService.checkIfAdmin(user));
        return "user";
    }
}
