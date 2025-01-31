package ru.nusratullin.bootcrud.ProjectBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nusratullin.bootcrud.ProjectBoot.dao.UserDao;
import ru.nusratullin.bootcrud.ProjectBoot.model.User;


import java.util.List;

@Service
public class UserServiceIml implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Transactional
    @Override

    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Transactional
    @Override
    public void edit(User user) {
        userDao.edit(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }
}
