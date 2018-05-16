package com.greenfoxacademy.reddit.Controller;

import com.greenfoxacademy.reddit.models.entities.Post;
import com.greenfoxacademy.reddit.models.entities.RedditUser;
import com.greenfoxacademy.reddit.models.entities.Vote;
import com.greenfoxacademy.reddit.Service.CommentServiceDbImpl;
import com.greenfoxacademy.reddit.Service.PostServiceDbImpl;
import com.greenfoxacademy.reddit.Service.RedditUserServiceDbImpl;
import com.greenfoxacademy.reddit.Service.VoteService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    private final CommentServiceDbImpl commentServiceDb;
    private final PostServiceDbImpl postServiceDb;
    private final RedditUserServiceDbImpl userServiceDb;
    private final VoteService voteService;

    @Autowired
    public PostController(CommentServiceDbImpl commentServiceDb,
                          PostServiceDbImpl postServiceDb,
                          RedditUserServiceDbImpl userServiceDb, VoteService voteService) {
        this.commentServiceDb = commentServiceDb;
        this.postServiceDb = postServiceDb;
        this.userServiceDb = userServiceDb;
        this.voteService = voteService;
    }

    @PostMapping("/upvote")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String postUpvote(Model model, HttpServletRequest request,
                             @RequestParam(value = "postid") String id,
                             @RequestParam(value = "username") String username) {
        Post post = postServiceDb.findOne(Long.parseLong(id));
        RedditUser user = userServiceDb.findByName(username);
        Vote vote = voteService.findByPostAndUser(post, user);
        if (vote == null) {
            Vote upvote = new Vote(user, post, 1);
            voteService.save(upvote);
        } else if (vote.getVote() == -1) {
            vote.setVote(1);
            voteService.save(vote);
        } else {
            post.getVotes().remove(vote);
            voteService.delete(vote);
        }
        post.setScore(post.getVotes().stream().mapToInt(Vote::getVote).sum());
        postServiceDb.save(post);
        model.addAttribute("voteService", voteService);
        return "redirect:?username=" + username;
    }

    @PostMapping("/downvote")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String postDownvote(Model model, HttpServletRequest request,
                               @RequestParam(value = "postid") String id,
                               @RequestParam(value = "username") String username) {
        Post post = postServiceDb.findOne(Long.parseLong(id));
        RedditUser user = userServiceDb.findByName(username);
        Vote vote = voteService.findByPostAndUser(post, user);
        if (vote == null) {
            Vote downvote = new Vote(user, post, -1);
            voteService.save(downvote);
        } else if (vote.getVote() == 1) {
            vote.setVote(-1);
            voteService.save(vote);
        } else {
            post.getVotes().remove(vote);
            voteService.delete(vote);
        }
        post.setScore(post.getVotes().stream().mapToInt(Vote::getVote).sum());
        postServiceDb.save(post);
        model.addAttribute("voteService", voteService);
        return "redirect:?username=" + username;
    }

    @GetMapping("/createpost")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getAdd(Model model, HttpServletRequest request,
                         @RequestParam(value = "username") String username) {
        RedditUser user = userServiceDb.findByName(username);
        model.addAttribute("user", user);

        return "createpost";
    }

    @PostMapping("/createpost")
    public String postAdd(Model model, HttpServletRequest request,
                          @RequestParam(value = "username") String username) {
        RedditUser user = userServiceDb.findByName(username);
        model.addAttribute("user", user);

        String title = request.getParameter("addtitle");
        String content = request.getParameter("addcontent");
        String link = request.getParameter("addLink");
        String imageUrl = request.getParameter("addImageUrl");
        String videoUrl = request.getParameter("addVideoUrl");
        String videoThumbnail = "/images/video-icon.png";

        if (videoUrl != null && videoUrl.contains("youtube")) {
            String videoId = videoUrl.split("\\?v=")[1];
            videoUrl = "https://www.youtube.com/embed/" + videoId;
            videoThumbnail = "https://img.youtube.com/vi/" + videoId + "/0.jpg";
        }
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setContent(content);
        newPost.setImageUrl(imageUrl);
        newPost.setLink(link);
        newPost.setVideoUrl(videoUrl);
        newPost.setVideoThumbnail(videoThumbnail);
        user.addPost(newPost);
        newPost.setUser(user);

        userServiceDb.save(user);
        postServiceDb.save(newPost);

        return "redirect:?username=" + username;
    }

    @GetMapping("/post")
    public String getPersonalPostDetail(Model model, @RequestParam(value = "username", required = false) String username,
                                @RequestParam(value = "postid") String id) {

        RedditUser user = userServiceDb.findByName(username);
        model.addAttribute("user", user);

        Post post = postServiceDb.findOne(Long.parseLong(id));
        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        model.addAttribute("voteService", voteService);
        return "postdetail";
    }

}
