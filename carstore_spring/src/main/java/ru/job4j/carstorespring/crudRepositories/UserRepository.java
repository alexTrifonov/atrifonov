package ru.job4j.carstorespring.crudRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carstorespring.models.User;

import java.util.List;

/**
 * Repository for User.
 * @author atrifonov.
 * @version 1.
 * @since 04.04.2018.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByLogin(String login);
}
