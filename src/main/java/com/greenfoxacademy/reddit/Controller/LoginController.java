package com.greenfoxacademy.reddit.Controller;

import com.greenfoxacademy.reddit.Model.User;
import com.greenfoxacademy.reddit.Service.UserServiceDbImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private final UserServiceDbImpl userServiceDb;

    @Autowired
    public LoginController(UserServiceDbImpl userServiceDb) {
        this.userServiceDb = userServiceDb;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(Model model, HttpServletRequest request, HttpServletResponse response) {
        String usernameinput = request.getParameter("username");

        User user = userServiceDb.findByName(usernameinput);

        model.addAttribute("user", user);
        return "redirect:/home/" + usernameinput;
    }

}

//        if(userServiceDb.exists(id) && userServiceDb.findByName(usernameinput).getPassword().equals(userpasswordinput)) {
//            result = "redirect:/home/" + usernameinput;
//        } else {
//            result = "cannotdoit";
//        }

//        try {
//            Object flash = request.getSession().getAttribute("flash");
//            model.addAttribute("flash", flash);
//
//            request.getSession().removeAttribute("flash");
//        } catch (Exception ex) {
//
//        }
