package ru.nusratullin.bootcrud.ProjectBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nusratullin.bootcrud.ProjectBoot.dao.RoleDao;
import ru.nusratullin.bootcrud.ProjectBoot.model.Role;

import java.util.Optional;

@Component
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public void save(Role roleUser) {
        roleDao.save(roleUser);
    }
}
