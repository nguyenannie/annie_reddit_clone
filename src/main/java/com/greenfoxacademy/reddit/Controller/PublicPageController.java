package com.greenfoxacademy.reddit.Controller;

import com.greenfoxacademy.reddit.Model.Post;
import com.greenfoxacademy.reddit.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PublicPageController {
    private final
    PostService postService;

    @Autowired
    public PublicPageController(PostService postService) {
        this.postService = postService;
    }

    @ModelAttribute("posts")
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("/reddit")
    public String getPublicPage(Model model) {
        return "public";
    }

    @GetMapping("/reddit/post/{id}")
    public String getPublicPostDetail(Model model, @PathVariable(value = "id") String id) {
        Post post = postService.findOne(Long.parseLong(id));
        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        return "publicpostdetail";
    }
}
