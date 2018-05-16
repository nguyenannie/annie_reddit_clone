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

@Controller
public class SubRedditController {

    @Autowired
    private SubRedditService subRedditService;
    @Autowired
    private RedditUserService redditUserService;

    @GetMapping("/subreddit")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getSubReddit(Model model, @RequestParam(value = "username") String username) {
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
        return "redirect:/subreddit?username=" + username;
    }
}
