package com.greenfoxacademy.reddit.Controller;

import com.greenfoxacademy.reddit.Model.User;
import com.greenfoxacademy.reddit.Service.UserServiceDbImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UserServiceDbImpl userServiceDb;

    @Autowired
    public RegisterController(UserServiceDbImpl userServiceDb) {
        this.userServiceDb = userServiceDb;
    }

    @GetMapping(value = "/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping(value = "/register")
    public String postRegister(Model model, HttpServletRequest request) {
        String result;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpassword");

        if(!userServiceDb.exists(username) && password.equals(confirmpassword)) {
            User newUser = new User();
            newUser.setPassword(password);
            newUser.setName(username);
            userServiceDb.save(newUser);
            result = "redirect:/login";
        } else {
            result = "cannotdoit";
        }
        return result;
    }

    @GetMapping(value = "/reddit/welcome")
    public String getReddit() {
        return "welcome";
    }
}
