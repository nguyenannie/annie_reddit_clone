package com.greenfoxacademy.reddit.Controller;

import com.greenfoxacademy.reddit.models.entities.Comment;
import com.greenfoxacademy.reddit.models.entities.Post;
import com.greenfoxacademy.reddit.models.entities.RedditUser;
import com.greenfoxacademy.reddit.Service.CommentServiceDbImpl;
import com.greenfoxacademy.reddit.Service.PostServiceDbImpl;
import com.greenfoxacademy.reddit.Service.RedditUserServiceDbImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    private final CommentServiceDbImpl commentServiceDb;
    private final PostServiceDbImpl postServiceDb;
    private final RedditUserServiceDbImpl userServiceDb;

    @Autowired
    public CommentController(CommentServiceDbImpl commentServiceDb, PostServiceDbImpl postServiceDb, RedditUserServiceDbImpl userServiceDb) {
        this.commentServiceDb = commentServiceDb;
        this.postServiceDb = postServiceDb;
        this.userServiceDb = userServiceDb;
    }

    @PostMapping("/comment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String postComment(Model model, HttpServletRequest request,
                              @RequestParam(value = "username") String username,
                              @RequestParam(value = "postid") String id) {

        Post post = postServiceDb.findOne(Long.parseLong(id));
        RedditUser user = userServiceDb.findByName(username);

        String commentContent = request.getParameter("addcomment");
        Comment newComment = new Comment();

        newComment.setContent(commentContent);
        newComment.setPost(post);
        newComment.setUser(user);

        user.addComment(newComment);
        post.addComment(newComment);
        post.setCommentsNum(post.getComments().size());

        userServiceDb.save(user);
        postServiceDb.save(post);
        commentServiceDb.save(newComment);

        model.addAttribute("user", user);
        model.addAttribute("post", post);
        return "redirect:/post?username=" + username + "&postid=" + id;
    }

}
