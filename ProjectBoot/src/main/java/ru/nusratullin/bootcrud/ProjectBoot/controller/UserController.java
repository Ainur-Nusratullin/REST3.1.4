package ru.nusratullin.bootcrud.ProjectBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;
import ru.nusratullin.bootcrud.ProjectBoot.service.UserService;

import java.util.Collection;

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
        ////
        // Получаем роли пользователя
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // Проверяем, есть ли у пользователя роль ADMIN
        boolean isAdmin = authorities.stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        // Добавляем информацию о ролях в модель
        model.addAttribute("isAdmin", isAdmin);
        ////
        return "home";
    }
}
