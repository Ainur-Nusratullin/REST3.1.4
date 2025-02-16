package ru.nusratullin.bootcrud.ProjectBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nusratullin.bootcrud.ProjectBoot.model.Role;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;
import ru.nusratullin.bootcrud.ProjectBoot.service.UserService;

import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("principalUser", userService.getUserHome(user));
        model.addAttribute("allUser", userService.readAllUser());
        Set<Role> allRoles = user.getRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin";
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam int age,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam Set<String> roles) {
        userService.saveUser(name, surname, age, email, password, roles);
        return "redirect:/admin/";
    }

    @GetMapping("/editUser")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.readUserById(id));
        return "admin";
    }

    @PostMapping("/editUser")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam int age,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam(required = false) Set<String> roles) {

        userService.updateUser(id, name, surname, age, email, password, roles);
        return "redirect:/admin/";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }

    @GetMapping("/find")
    public String getUserById(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("user", userService.readUserById(id));
        return "admin";
    }
}
