package com.mine.service;

import com.mine.entity.Role;
import com.mine.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleImpl implements IRole{
    RoleRepository roleRepository;
    public RoleImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    @Override
    public Role findRoleById(int id) {
        return roleRepository.findRoleById(id).orElse(null);
    }
}
