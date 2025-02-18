package ru.nusratullin.bootcrud.ProjectBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nusratullin.bootcrud.ProjectBoot.dao.RoleDao;
import ru.nusratullin.bootcrud.ProjectBoot.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleRepository) {
        this.roleDao = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
