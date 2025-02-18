package ru.nusratullin.bootcrud.ProjectBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nusratullin.bootcrud.ProjectBoot.dao.RoleDao;
import ru.nusratullin.bootcrud.ProjectBoot.dao.UserDao;
import ru.nusratullin.bootcrud.ProjectBoot.model.Role;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;

import java.util.*;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleDao roleRepository) {
        this.userDao = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleDao = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> showAllUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        Optional<User> user = userDao.findById(id);
        return user.orElse(new User());
    }


//    @Override
//    @Transactional
//    public void save(User user) {
//        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        } else {
//            // Если пароль не передан, оставляем старый пароль
//            User existingUser = userDao.findById(user.getId())
//                    .orElseThrow(() -> new RuntimeException("User not found with id: " + user.getId()));
//            user.setPassword(bCryptPasswordEncoder.encode(existingUser.getPassword()));
//        }
//
//        Set<Role> roles = new HashSet<>();
//        for (Role role : user.getRoles()) {
//            Role existingRole = roleDao.findById(role.getId())
//                    .orElseThrow(() -> new RuntimeException("Role not found with id: " + role.getId()));
//            roles.add(existingRole);
//        }
//        user.setRoles(roles);
//
//        userDao.save(user);
//    }

    @Override
    @Transactional
    public void save(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            // Если пароль передан, шифруем его
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else {
            // Если пароль не передан, оставляем старый пароль без изменений
            User existingUser = userDao.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + user.getId()));
            user.setPassword(existingUser.getPassword()); // Сохраняем старый пароль без шифрования
        }

        // Обновляем роли
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            Role existingRole = roleDao.findById(role.getId())
                    .orElseThrow(() -> new RuntimeException("Role not found with id: " + role.getId()));
            roles.add(existingRole);
        }
        user.setRoles(roles);

        // Сохраняем пользователя
        userDao.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
