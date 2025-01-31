package ru.nusratullin.bootcrud.ProjectBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;
import ru.nusratullin.bootcrud.ProjectBoot.service.UserService;

import java.util.List;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/allUser")
    public String getAllUser(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("allUser", users);
        return "user";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/allUser";
    }

    @GetMapping("/editUser")
    public String editUser(@RequestParam("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/editUser")
    public String editUser(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/allUser";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:/allUser";
    }
}
