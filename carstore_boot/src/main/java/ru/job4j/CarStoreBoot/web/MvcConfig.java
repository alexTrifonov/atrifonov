package ru.job4j.CarStoreBoot.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/addCar").setViewName("addCar");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/start").setViewName("start");
    }
}
