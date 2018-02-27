package com.greenfoxacademy.reddit.Controller;

import com.greenfoxacademy.reddit.Model.Role;
import com.greenfoxacademy.reddit.Model.User;
import com.greenfoxacademy.reddit.Service.RoleService;
import com.greenfoxacademy.reddit.Service.RoleServiceDbImpl;
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
    private final RoleServiceDbImpl roleService;

    @Autowired
    public RegisterController(UserServiceDbImpl userServiceDb, RoleServiceDbImpl roleService) {
        this.userServiceDb = userServiceDb;
        this.roleService = roleService;
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

        if(userServiceDb.exists(username)) {
            model.addAttribute("error", "Username already existed. Please choose another one!");
            result = "/register";
        } else if(!password.equals(confirmpassword)) {
            model.addAttribute("error", "Please check your password confirm!");
            result = "/register";
        } else {
            Role role = new Role("ROLE_USER");
            roleService.save(role);

            User newUser = new User();
            newUser.setPassword(password);
            newUser.setName(username);
            newUser.setRole(role);

            userServiceDb.save(newUser);
            model.addAttribute("error", "");
            result = "redirect:/login";
        }
        return result;
    }

    @GetMapping(value = "/reddit/welcome")
    public String getReddit() {
        return "welcome";
    }
}
