package com.example.demo.controller.web;

import com.example.demo.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

@Slf4j
@Controller
public class WebController {
    @RequestMapping("/home")
    public ModelAndView getHome(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index.html");
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (user != null) {
            modelAndView.addObject("name", user.getName());
            log.info("Session: {}", user);
//        Enumeration<String> tmp = session.getAttributeNames();
//        log.info("getHome: {}", tmp);
        } else {
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login.html");
    }

    @RequestMapping("/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("register.html");
    }
}
