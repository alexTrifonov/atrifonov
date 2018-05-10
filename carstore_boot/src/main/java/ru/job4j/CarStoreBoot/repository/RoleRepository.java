package ru.job4j.CarStoreBoot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.CarStoreBoot.domain.Role;

/**
 * Repository for Role.
 * @author atrifonov.
 * @version 1.
 * @since 26.04.2018.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
