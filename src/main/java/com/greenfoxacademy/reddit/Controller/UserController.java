package com.greenfoxacademy.reddit.Controller;

import com.greenfoxacademy.reddit.Model.Comment;
import com.greenfoxacademy.reddit.Model.Post;
import com.greenfoxacademy.reddit.Model.User;
import com.greenfoxacademy.reddit.Service.CommentServiceDbImpl;
import com.greenfoxacademy.reddit.Service.PostServiceDbImpl;
import com.greenfoxacademy.reddit.Service.UserServiceDbImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final CommentServiceDbImpl commentServiceDb;
    private final PostServiceDbImpl postServiceDb;
    private final UserServiceDbImpl userServiceDb;

    @Autowired
    public UserController(CommentServiceDbImpl commentServiceDb, PostServiceDbImpl postServiceDb, UserServiceDbImpl userServiceDb) {
        this.commentServiceDb = commentServiceDb;
        this.postServiceDb = postServiceDb;
        this.userServiceDb = userServiceDb;
    }

    @GetMapping("/account/{username}")
    public String getUserInfo(Model model, @PathVariable(value = "username") String username) {
        User user = userServiceDb.findByName(username);

        model.addAttribute("myposts", user.getPosts());
        model.addAttribute("postnum", user.getPosts().size());
        model.addAttribute("mycomments", user.getComments());
        model.addAttribute("commentnum", user.getComments().size());
        model.addAttribute("user", user);

        return "userinfo";
    }

    @GetMapping("/account/{username}/changepassword")
    public String getchangePassword(Model model,
                                    @PathVariable(value = "username") String username) {
        User user = userServiceDb.findByName(username);
        model.addAttribute("user", user);

        return "changepassword";
    }

    @PostMapping("/account/{username}/changepassword")
    public String postUserPassword(Model model, @PathVariable(value = "username") String username,
                                   HttpServletRequest request) {

        String result;
        User user = userServiceDb.findByName(username);

        String newPassword = request.getParameter("changepassword");
        String confirmPassword = request.getParameter("confirmpassword");

        if(newPassword != null && newPassword.equals(confirmPassword) && !newPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userServiceDb.save(user);
            result = "redirect:/account/" + username;
        } else {
            result = "cantchangeit";
        }
        model.addAttribute("user", user);

        return result;
    }


    @PostMapping("/account/{username}/post/{postid}/delete")
    public String deletePost(Model model, @PathVariable(value = "username") String username, @PathVariable(value = "postid") String postid) {
        User user = userServiceDb.findByName(username);
        Post post = postServiceDb.findOne(Long.parseLong(postid));
        List<Comment> comments = post.getComments();

        for(Comment comment : comments) {
            commentServiceDb.delete(comment.getId());
        }

        postServiceDb.delete(Long.parseLong(postid));

        model.addAttribute("user", user);
        model.addAttribute("post", post);

        return "redirect:/account/" + username;
    }

    @PostMapping("/account/{username}/comment/{commentid}/delete")
    public String deleteComment(Model model, @PathVariable(value = "username") String username, @PathVariable(value = "commentid") String commentid) {
        User user = userServiceDb.findByName(username);
        Comment comment = commentServiceDb.findOne(Long.parseLong(commentid));

        user.removeComment(comment);
        userServiceDb.save(user);
        commentServiceDb.delete(Long.parseLong(commentid));

        model.addAttribute("user", user);
        model.addAttribute("comment", comment);

        return "redirect:/account/" + username;
    }
}
