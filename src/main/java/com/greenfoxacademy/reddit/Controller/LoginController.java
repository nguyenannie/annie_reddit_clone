package com.greenfoxacademy.reddit.Controller;

import com.greenfoxacademy.reddit.models.RedditUser;
import com.greenfoxacademy.reddit.Service.RedditUserServiceDbImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private final RedditUserServiceDbImpl userServiceDb;

    @Autowired
    public LoginController(RedditUserServiceDbImpl userServiceDb) {
        this.userServiceDb = userServiceDb;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(Model model, HttpServletRequest request, HttpServletResponse response) {
        String usernameinput = request.getParameter("username");

        RedditUser user = userServiceDb.findByName(usernameinput);

        model.addAttribute("user", user);
        return "redirect:/?username=" + usernameinput;
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
