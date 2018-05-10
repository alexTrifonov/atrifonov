package ru.job4j.CarStoreBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.CarStoreBoot.domain.User;
import ru.job4j.CarStoreBoot.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service for user.
 * @author atrifonov
 * @version 1.
 * @since 26.04.2018.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User saveUser(User user) {
        user = userRepository.save(user);
        return user;
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
