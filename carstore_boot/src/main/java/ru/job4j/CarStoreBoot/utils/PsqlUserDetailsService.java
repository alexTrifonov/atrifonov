package ru.job4j.CarStoreBoot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.CarStoreBoot.domain.User;
import ru.job4j.CarStoreBoot.service.UserService;


@Service
public class PsqlUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails loadedUser;

        try {
            User user = userService.findByLogin(username).get();
            loadedUser = new org.springframework.security.core.userdetails.User(
                    user.getLogin(), user.getPassword(), user.getRoles()
            );
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
        return loadedUser;
    }
}
