package com.greenfoxacademy.reddit;

import com.greenfoxacademy.reddit.Service.CommentServiceDbImpl;
import com.greenfoxacademy.reddit.Service.PostServiceDbImpl;
import com.greenfoxacademy.reddit.Service.RoleServiceDbImpl;
import com.greenfoxacademy.reddit.Service.RedditUserServiceDbImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedditApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedditApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CommentServiceDbImpl commentServiceDb,
                                  PostServiceDbImpl postServiceDb, RedditUserServiceDbImpl userServiceDb, RoleServiceDbImpl roleServiceDb) {
        return (String... args) -> {


        };
    }
}
