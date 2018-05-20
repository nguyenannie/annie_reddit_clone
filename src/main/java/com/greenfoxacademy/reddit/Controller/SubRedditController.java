package com.greenfoxacademy.reddit.Controller;

import com.greenfoxacademy.reddit.Service.RedditUserService;
import com.greenfoxacademy.reddit.Service.SubRedditService;
import com.greenfoxacademy.reddit.models.entities.RedditUser;
import com.greenfoxacademy.reddit.models.entities.SubReddit;
import com.greenfoxacademy.reddit.models.forms.SubRedditForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SubRedditController {

    @Autowired
    private SubRedditService subRedditService;
    @Autowired
    private RedditUserService redditUserService;

    @GetMapping("/createSubReddit")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getCreateSubReddit(Model model, @RequestParam(value = "username") String username) {
        model.addAttribute("subRedditForm", new SubRedditForm());
        model.addAttribute("subreddits", subRedditService.findAll());
        model.addAttribute("user", redditUserService.findByName(username));
        return "createSubReddit";
    }

    @PostMapping("/createSubReddit")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String createSubReddit(Model model, @RequestParam(value = "username") String username,
                                  @ModelAttribute(name = "subRedditForm")SubRedditForm subRedditForm) {
        RedditUser user = redditUserService.findByName(username);
        SubReddit subReddit = new SubReddit(user);
        subReddit.setName(subRedditForm.getName());
        subReddit.setDescription(subRedditForm.getDescription());
        subRedditService.save(subReddit);
        redditUserService.save(user);
        return "redirect:/createSubReddit?username=" + username;
    }

    @GetMapping("/subReddit")
    public String getSubReddit(Model model, @RequestParam(value = "username", required = false) String username,
                               @RequestParam(value = "subRedditName") String subRedditName) {
        RedditUser user = redditUserService.findByName(username);
        model.addAttribute("user", user);
        SubReddit subReddit = subRedditService.findByName(subRedditName);
        model.addAttribute("subReddit", subReddit);
        return "subReddit";
    }

    @GetMapping("/subReddits")
    public String getSubReddits(Model model, @RequestParam(value = "username", required = false) String username) {
        RedditUser user = redditUserService.findByName(username);
        model.addAttribute("user", user);
        model.addAttribute("subReddits", subRedditService.findAll());
        return "subReddits";
    }

}
