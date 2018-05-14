package ru.job4j.CarStoreBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.CarStoreBoot.domain.User;
import ru.job4j.CarStoreBoot.service.UserService;

import java.util.List;


@Controller
public class Users {
    @Autowired
    UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @GetMapping("/users")
    public ModelAndView getUsers() {
        ModelAndView model = new ModelAndView("users");
        model.addObject("users", userService.findAll());
        return model;
    }
}
