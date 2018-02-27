package com.greenfoxacademy.reddit.Controller;

import com.greenfoxacademy.reddit.Model.Post;
import com.greenfoxacademy.reddit.Model.User;
import com.greenfoxacademy.reddit.Service.CommentServiceDbImpl;
import com.greenfoxacademy.reddit.Service.PostServiceDbImpl;
import com.greenfoxacademy.reddit.Service.UserServiceDbImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    private final CommentServiceDbImpl commentServiceDb;
    private final PostServiceDbImpl postServiceDb;
    private final UserServiceDbImpl userServiceDb;

    @Autowired
    public PostController(CommentServiceDbImpl commentServiceDb,
                          PostServiceDbImpl postServiceDb,
                          UserServiceDbImpl userServiceDb) {
        this.commentServiceDb = commentServiceDb;
        this.postServiceDb = postServiceDb;
        this.userServiceDb = userServiceDb;
    }

    @PostMapping("/home/upvote/{username}/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String postUpvote(Model model, HttpServletRequest request,
                             @PathVariable(value = "id") String id,
                             @PathVariable(value = "username") String username) {
        Post post = postServiceDb.findOne(Long.parseLong(id));

        int score = post.getScore();
        score += 1;
        post.setScore(score);

        postServiceDb.save(post);

        return "redirect:/home/" + username;
    }

    @PostMapping("/home/downvote/{username}/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String postDownvote(Model model, HttpServletRequest request,
                               @PathVariable(value = "id") String id,
                               @PathVariable(value = "username") String username) {
        Post post = postServiceDb.findOne(Long.parseLong(id));

        int score = post.getScore();
        score -= 1;
        post.setScore(score);

        postServiceDb.save(post);

        return "redirect:/home/" + username;
    }

    @GetMapping("/home/{username}/createpost")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getAdd(Model model, HttpServletRequest request,
                         @PathVariable(value = "username") String username) {
        User user = userServiceDb.findByName(username);
        model.addAttribute("user", user);

        return "createpost";
    }

    @PostMapping("/home/{username}/createpost")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public String postAdd(Model model, HttpServletRequest request,
                          @PathVariable(value = "username") String username) {
        User user = userServiceDb.findByName(username);
        model.addAttribute("user", user);

        String title = request.getParameter("addtitle");
        String content = request.getParameter("addcontent");

        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setContent(content);
        user.addPost(newPost);
        newPost.setUser(user);

        userServiceDb.save(user);
        postServiceDb.save(newPost);

        return "redirect:/home/" + username;
    }

    @GetMapping("/{username}/post/{postid}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getPersonalPostDetail(Model model, HttpServletRequest request,
                                @PathVariable(value = "username") String username,
                                @PathVariable(value = "postid") String id) {

        User user = userServiceDb.findByName(username);
        model.addAttribute("user", user);

        Post post = postServiceDb.findOne(Long.parseLong(id));
        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        return "postdetail";
    }

    @GetMapping("/post/{postid}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getPublicPostDetail(Model model, @PathVariable(value = "postid") String id) {

        Post post = postServiceDb.findOne(Long.parseLong(id));
        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        return "postdetail";
    }
}
