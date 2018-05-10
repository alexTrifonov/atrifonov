package ru.job4j.CarStoreBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.CarStoreBoot.domain.Role;
import ru.job4j.CarStoreBoot.repository.RoleRepository;

/**
 * Service for role.
 * @author atrifonov
 * @version 1.
 * @since 26.04.2018.
 */
@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
