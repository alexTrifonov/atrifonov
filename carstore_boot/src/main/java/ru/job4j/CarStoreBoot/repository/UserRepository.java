package ru.job4j.CarStoreBoot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.CarStoreBoot.domain.User;

import java.util.Optional;

/**
 * Repository for User.
 * @author atrifonov.
 * @version 1.
 * @since 26.04.2018.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}
