package ru.nusratullin.bootcrud.ProjectBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.nusratullin.bootcrud.ProjectBoot.service.UserServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private LoginSuccessHandler loginSuccessHandler;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public SecurityConfig(LoginSuccessHandler loginSuccessHandler, @Lazy UserServiceImpl userServiceImpl) {
        this.loginSuccessHandler = loginSuccessHandler;
        this.userServiceImpl = userServiceImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Отключаем CSRF защиту
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").hasAuthority("ADMIN") // Доступ только для ADMIN
                        .requestMatchers("/user/**").hasAnyAuthority("ADMIN", "USER") // Доступ для ADMIN и USER
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .formLogin(form -> form
                        .successHandler(loginSuccessHandler) // Обработчик успешной аутентификации
                        .permitAll() // Разрешаем доступ к форме логина всем
                )
                .logout(logout -> logout
                        .permitAll() // Разрешаем доступ к логауту всем
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userServiceImpl); // Устанавливаем UserDetailsService
        authProvider.setPasswordEncoder(bCryptPasswordEncoder()); // Устанавливаем кодировщик паролей
        return authProvider;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        return authenticationProvider();
    }
}
