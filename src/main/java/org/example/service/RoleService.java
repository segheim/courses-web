package org.example.service;

import org.example.entity.Role;
import org.example.repository.RoleRepository;

public class RoleService implements Service<Role>{

    private final RoleRepository roleRepository;

    public RoleService() {
        this.roleRepository = new RoleRepository();
    }

    public Role add(Role role) {
        return roleRepository.create(role);
    }

    public Role update(Role role) {
        Role role1 = roleRepository.getById(role.getId());
        role1.setRoleName(role.getRoleName());
        return roleRepository.update(role1);
    }

    public boolean remove(Long id) {
        return roleRepository.deleteById(id);
    }

    public Role findById(Long id) {
        return roleRepository.getById(id);
    }
}
