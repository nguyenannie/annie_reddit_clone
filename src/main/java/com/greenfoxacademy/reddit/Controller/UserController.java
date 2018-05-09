package com.greenfoxacademy.reddit.Controller;

import com.greenfoxacademy.reddit.models.Comment;
import com.greenfoxacademy.reddit.models.Post;
import com.greenfoxacademy.reddit.models.RedditUser;
import com.greenfoxacademy.reddit.Service.CommentServiceDbImpl;
import com.greenfoxacademy.reddit.Service.PostServiceDbImpl;
import com.greenfoxacademy.reddit.Service.RedditUserServiceDbImpl;
import com.greenfoxacademy.reddit.models.forms.PostForm;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
public class UserController {

    private final CommentServiceDbImpl commentServiceDb;
    private final PostServiceDbImpl postServiceDb;
    private final RedditUserServiceDbImpl userServiceDb;

    @Autowired
    public UserController(CommentServiceDbImpl commentServiceDb, PostServiceDbImpl postServiceDb, RedditUserServiceDbImpl userServiceDb) {
        this.commentServiceDb = commentServiceDb;
        this.postServiceDb = postServiceDb;
        this.userServiceDb = userServiceDb;
    }

    @GetMapping("/mypost")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getPosts(Model model, @RequestParam(value = "username") String username) {
        RedditUser user = userServiceDb.findByName(username);

        model.addAttribute("myposts", user.getPosts());
        model.addAttribute("postnum", user.getPosts().size());
        model.addAttribute("mycomments", user.getComments());
        model.addAttribute("commentnum", user.getComments().size());
        model.addAttribute("user", user);
        model.addAttribute("postForm", new PostForm("", ""));

        return "mypost";
    }

    @GetMapping("/mycomment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getComments(Model model, @RequestParam(value = "username") String username) {
        RedditUser user = userServiceDb.findByName(username);

        model.addAttribute("myposts", user.getPosts());
        model.addAttribute("postnum", user.getPosts().size());
        model.addAttribute("mycomments", user.getComments());
        model.addAttribute("commentnum", user.getComments().size());
        model.addAttribute("user", user);
        return "mycomment";
    }

    @PostMapping("/editComment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String editComment(@ModelAttribute(value = "postForm") PostForm postForm, Model model,
                              @RequestParam(value = "username") String username,
                              @RequestParam(value = "commentid") String commentid) {
        RedditUser user = userServiceDb.findByName(username);
        Comment comment = commentServiceDb.findOne(Long.parseLong(commentid));
        comment.setContent(postForm.getContent());
        commentServiceDb.save(comment);
        model.addAttribute("comment", comment);
        model.addAttribute("user", user);
        return "redirect:/mycomment?username=" + username;
    }

    @PostMapping("/deletePost")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String deletePost(Model model, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "postid") String postid) {
        RedditUser user = userServiceDb.findByName(username);
        Post post = postServiceDb.findOne(Long.parseLong(postid));
        List<Comment> comments = commentServiceDb.findByPostAndUser(post, user);

        for(Comment comment : comments) {
            user.removeComment(comment);
            commentServiceDb.delete(comment.getId());
        }
        user.removePost(post);
        postServiceDb.delete(Long.parseLong(postid));
        userServiceDb.save(user);

        model.addAttribute("user", user);
        model.addAttribute("post", post);

        return "redirect:/mypost?username=" + username;
    }

    @PostMapping("/editPost")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String editPost(@ModelAttribute(value = "postForm") PostForm postForm, Model model, @RequestParam(value = "username") String username, @RequestParam(value = "postid") String postid) {
        RedditUser user = userServiceDb.findByName(username);
        Post post = postServiceDb.findOne(Long.parseLong(postid));
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        postServiceDb.save(post);
        model.addAttribute("user", user);
        model.addAttribute("post", post);

        return "redirect:/mypost?username=" + username;
    }

    @PostMapping("/deleteComment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String deleteComment(Model model, @RequestParam(value = "username") String username, @RequestParam(value = "commentid") String commentid) {
        RedditUser user = userServiceDb.findByName(username);
        Comment comment = commentServiceDb.findOne(Long.parseLong(commentid));

        user.removeComment(comment);
        userServiceDb.save(user);
        commentServiceDb.delete(Long.parseLong(commentid));

        model.addAttribute("user", user);
        model.addAttribute("comment", comment);

        return "redirect:/mycomment?username=" + username;
    }

    @GetMapping("/changepassword")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getchangePassword(Model model,
                                    @RequestParam(value = "username") String username) {
        RedditUser user = userServiceDb.findByName(username);
        model.addAttribute("user", user);

        return "changepassword";
    }

    @PostMapping("/changepassword")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String postUserPassword(Model model, @RequestParam(value = "username") String username,
                                   HttpServletRequest request) {

        String result;
        RedditUser user = userServiceDb.findByName(username);

        String newPassword = request.getParameter("changepassword");
        String confirmPassword = request.getParameter("confirmpassword");

        if(newPassword != null && newPassword.equals(confirmPassword) && !newPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userServiceDb.save(user);
            result = "redirect:/mypost?username=" + username;
        } else {
            result = "cantchangeit";
        }
        model.addAttribute("user", user);

        return result;
    }

}
