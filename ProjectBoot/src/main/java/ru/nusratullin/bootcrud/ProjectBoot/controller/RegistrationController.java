package ru.nusratullin.bootcrud.ProjectBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;
import ru.nusratullin.bootcrud.ProjectBoot.service.UserService;

import java.util.Set;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/registration";
    }

    @PostMapping("/registration")
    public String registerUser(@RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam int age,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam Set<String> roles) {
        userService.saveUser(name, surname, age, email, password, roles);
        return "redirect:/login";
    }
}
