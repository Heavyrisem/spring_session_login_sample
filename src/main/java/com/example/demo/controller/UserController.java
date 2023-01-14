package com.example.demo.controller;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.LoginUserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String registerUser(@Validated CreateUserDto createUserDto) {
        log.info("register: {}", createUserDto);
        User user = userService.createUser(createUserDto);
//        session.setAttribute("user", user);

        return "redirect:/register";
    }

    @PostMapping("/login")
    public String loginUser(@Validated LoginUserDto loginUserDto, HttpServletRequest request) {
        log.info("login: {}", loginUserDto);
        User user = userService.verifyUser(loginUserDto);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        log.info("/login after session: {}", session.getAttribute("user"));
//        ModelAndView modelAndView = new ModelAndView("redirect:/index.html");
//        modelAndView.addObject("name", user.getName());

        return "redirect:/home";
    }

    @PostMapping("/test")
    public String test() {
        return "Hello World";
    }
}
