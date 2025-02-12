package ru.nusratullin.bootcrud.ProjectBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nusratullin.bootcrud.ProjectBoot.dao.UserDao;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;


import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        return user;
    }
}