package ru.job4j.CarStoreBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.CarStoreBoot.domain.Role;
import ru.job4j.CarStoreBoot.domain.User;
import ru.job4j.CarStoreBoot.service.RoleService;
import ru.job4j.CarStoreBoot.service.UserService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Controller
public class ActionsOnUser {
    private final static String USER_EXIST = "Пользователь с таким логином уже существует";
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/addUser")
    public ModelAndView viewAddUser() {
        ModelAndView model = new ModelAndView("addUser");
        return model;
    }

    @PostMapping("/addUser")
    public ModelAndView addUser(@ModelAttribute User user) {
        Optional<User> userOptional = userService.findByLogin(user.getLogin());
        ModelAndView model;
        if (userOptional.isPresent()) {
            model = new ModelAndView("/addUser");
            model.addObject("userExist", USER_EXIST);
        } else {
            model = new ModelAndView("/login");
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            Role role = roleService.findByName("ROLE_USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            userService.saveUser(user);
        }
        return model;
    }

    @GetMapping("/deleteUser")
    public ModelAndView deleteUser(@RequestParam String userLogin) {
        ModelAndView model = new ModelAndView("deleteUser");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdmin = false;
        Collection<Role> roles = (Collection<Role>) auth.getAuthorities();
        for (Role role : roles) {
            hasAdmin = role.getAuthority().equals("ROLE_ADMIN");
            if(hasAdmin) {
                break;
            }
        }
        if (hasAdmin) {
            model.addObject("username", userLogin);
            Optional<User> optional = userService.findByLogin(userLogin);
            if ( optional.isPresent()) {
                userService.deleteUser(optional.get());
            }
        } else {
            model.addObject("notAdmin", "нет прав для удаления");
        }
        return model;
    }
}
