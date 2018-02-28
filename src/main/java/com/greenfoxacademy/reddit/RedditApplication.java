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
        System.out.println(System.getenv("DATABASE_URL"));
        SpringApplication.run(RedditApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CommentServiceDbImpl commentServiceDb,
                                  PostServiceDbImpl postServiceDb, RedditUserServiceDbImpl userServiceDb, RoleServiceDbImpl roleServiceDb) {
        return (String... args) -> {
//            Role role = new Role("ROLE_USER");
//            roleServiceDb.save(role);
//
//            RedditUser user = new RedditUser("user6","password");
//            user.setRole(role);
//            userServiceDb.save(user);
//
//            RedditUser user1 = new RedditUser("natsdof", "12345");
//            RedditUser user2 = new RedditUser("John", "iamnotfat");
//            RedditUser user3 = new RedditUser("BigA", "idonteatalot");
//            RedditUser user4 = new RedditUser("Mark", "iamsuperhandsome");
//            RedditUser user5 = new RedditUser("Belli", "lovedrawing");
//            RedditUser user6 = new RedditUser("fondetti", "idontknowwhoiam");
//            RedditUser user7 = new RedditUser("sugardaddy", "madeupperson");
//            RedditUser user8 = new RedditUser("catlover", "notkilllady");
//            RedditUser user9 = new RedditUser("hotboy090", "12345");
//            RedditUser user10 = new RedditUser("niceguy898", "donthatecat");
//            RedditUser user11 = new RedditUser("Annie", "12345");
//            RedditUser user12 = new RedditUser("Rubble", "iamnotfat");
//            RedditUser user13 = new RedditUser("Sonic", "idonteatalot");
//            RedditUser user14 = new RedditUser("Krisz", "iamsuperhandsome");
//            RedditUser user15 = new RedditUser("Bea", "lovedrawing");
//            RedditUser user16 = new RedditUser("Sally", "idontknowwhoiam");
//            RedditUser user17 = new RedditUser("Johnney", "madeupperson");
//            RedditUser user18 = new RedditUser("LadyKiller", "notkilllady");
//            RedditUser user19 = new RedditUser("handsomeboy_903", "12345");
//            RedditUser user20 = new RedditUser("CuteDog", "donthatecat");
//
//
//            Post post1 = new Post(user1, "She doesn't skip leg day", "Hot Body", 670);
//            Post post2 = new Post(user4, "On the journey to success", "Only keep it trill...", 1950);
//            Post post3 = new Post(user6, "The name of this ice-cream", "Nogger", 8000);
//            Post post4 = new Post(user8, "He took one look at my bio and ran with it",
//                    "Pretty straight forword, not looking for anything serious. " +
//                            "If you dont believe in climate change, and/or your are Trump supporter, swipe left", 14000);
//            Post post5 = new Post(user9, "Sadly #NoRepply", "Do you speak Russian? " +
//                    "No but i can sepak English if that is fine for you", 13678);
//            Post post6 = new Post(user18, "Cool dog actions", "Incredible", 12537);
//            Post post7 = new Post(user13, "Handsome guy debugs his wife's code", "Incredible", 15589);
//            Post post8 = new Post(user10, "If you were a vegetable", "If you were a vegetable," +
//                    "you would be a cute-cumber", 12789);
//            Post post9 = new Post(user17, "", "Incredible", 12537);
//            Post post10 = new Post(user11, "We can all relate to this one", "When she comes home...", 10000);
//            Post post11 = new Post(user14, "Not all cats hate water", "He willingly jumped into the bath tub :)", 13890);
//            Post post12 = new Post(user12, "Who's the fuck wrote this book????", "True Faith and Allegiance", 15000);
//            Post post13 = new Post(user10, "This uncut dorito", "i have more than i can imagine", 15000);
//            Post post14 = new Post(user19, "", "Incredible", 12537);
//            Post post15 = new Post(user15, "Bill Gates with the first version of " +
//                    "Microsoft Windows 1980s", "Historical Moment", 12537);
//            Post post16 = new Post(user16, "Cat is the best animal", "Cat gif", 500);
//            Post post17 = new Post(user2, "2018 hair trend", "weird hair", 150);
//            Post post18 = new Post(user5, "Tinder memes", "click to see more", 299);
//            Post post19 = new Post(user3, "Jumping over a parachute", "he was nearly dead", 189);
//            Post post20 = new Post(user7, "That was unexpected", "yeeeep", 357);
//
//
//            Comment comment1 = new Comment(user6, post1,"It's so weirdddd");
//            Comment comment2 = new Comment(user7, post11,"I wish you're gonna get better buddy. Too bad!");
//            Comment comment3 = new Comment(user8, post3,"Why's it funny? What's wrong with you people");
//            Comment comment4 = new Comment(user9, post12,"Wowwwww");
//            Comment comment5 = new Comment(user10, post5,"That's what she said");
//            Comment comment6 = new Comment(user18, post1,"You sucks");
//            Comment comment7 = new Comment(user16, post2,"lol");
//            Comment comment8 = new Comment(user11, post4,"hahahhaha");
//            Comment comment9 = new Comment(user20, post6,"Poor you");
//            Comment comment10 = new Comment(user13, post5,"That is not trueeeee!!!");
//
//            userServiceDb.save(user1);
//            userServiceDb.save(user2);
//            userServiceDb.save(user3);
//            userServiceDb.save(user4);
//            userServiceDb.save(user5);
//            userServiceDb.save(user6);
//            userServiceDb.save(user7);
//            userServiceDb.save(user8);
//            userServiceDb.save(user9);
//            userServiceDb.save(user10);
//            userServiceDb.save(user11);
//            userServiceDb.save(user12);
//            userServiceDb.save(user13);
//            userServiceDb.save(user14);
//            userServiceDb.save(user15);
//            userServiceDb.save(user16);
//            userServiceDb.save(user17);
//            userServiceDb.save(user18);
//            userServiceDb.save(user19);
//            userServiceDb.save(user20);
//
//            postServiceDb.save(post1);
//            postServiceDb.save(post2);
//            postServiceDb.save(post3);
//            postServiceDb.save(post4);
//            postServiceDb.save(post5);
//            postServiceDb.save(post6);
//            postServiceDb.save(post7);
//            postServiceDb.save(post8);
//            postServiceDb.save(post9);
//            postServiceDb.save(post10);
//            postServiceDb.save(post11);
//            postServiceDb.save(post12);
//            postServiceDb.save(post13);
//            postServiceDb.save(post14);
//            postServiceDb.save(post15);
//            postServiceDb.save(post16);
//            postServiceDb.save(post17);
//            postServiceDb.save(post18);
//            postServiceDb.save(post19);
//            postServiceDb.save(post20);
//
//            commentServiceDb.save(comment1);
//            commentServiceDb.save(comment2);
//            commentServiceDb.save(comment3);
//            commentServiceDb.save(comment4);
//            commentServiceDb.save(comment5);
//            commentServiceDb.save(comment6);
//            commentServiceDb.save(comment7);
//            commentServiceDb.save(comment8);
//            commentServiceDb.save(comment9);
//            commentServiceDb.save(comment10);

        };
    }
}

    /*
            RedditUser user1 = new RedditUser("natsdof", "12345");
            RedditUser user2 = new RedditUser("John", "iamnotfat");
            RedditUser user3 = new RedditUser("BigA", "idonteatalot");
            RedditUser user4 = new RedditUser("Mark", "iamsuperhandsome");
            RedditUser user5 = new RedditUser("Belli", "lovedrawing");
            RedditUser user6 = new RedditUser("fondetti", "idontknowwhoiam");
            RedditUser user7 = new RedditUser("sugardaddy", "madeupperson");
            RedditUser user8 = new RedditUser("catlover", "notkilllady");
            RedditUser user9 = new RedditUser("hotboy090", "12345");
            RedditUser user10 = new RedditUser("niceguy898", "donthatecat");
            RedditUser user11 = new RedditUser("Annie", "12345");
            RedditUser user12 = new RedditUser("Rubble", "iamnotfat");
            RedditUser user13 = new RedditUser("Sonic", "idonteatalot");
            RedditUser user14 = new RedditUser("Krisz", "iamsuperhandsome");
            RedditUser user15 = new RedditUser("Bea", "lovedrawing");
            RedditUser user16 = new RedditUser("Sally", "idontknowwhoiam");
            RedditUser user17 = new RedditUser("John", "madeupperson");
            RedditUser user18 = new RedditUser("LadyKiller", "notkilllady");
            RedditUser user19 = new RedditUser("handsomeboy_903", "12345");
            RedditUser user20 = new RedditUser("CuteDog", "donthatecat");


            Post post1 = new Post(user1, "She doesn't skip leg day", "Hot Body", 670);
            Post post2 = new Post(user4, "On the journey to success", "Only keep it trill...", 1950);
            Post post3 = new Post(user6, "The name of this ice-cream", "Nogger", 8000);
            Post post4 = new Post(user8, "He took one look at my bio and ran with it",
                    "Pretty straight forword, not looking for anything serious. " +
                            "If you dont believe in climate change, and/or your are Trump supporter, swipe left", 14000);
            Post post5 = new Post(user9, "Sadly #NoRepply", "Do you speak Russian? " +
                    "No but i can sepak English if that is fine for you", 13678);
            Post post7 = new Post(user13, "Handsome guy debugs his wife's code", "Incredible", 15589);
            Post post8 = new Post(user10, "If you were a vegetable", "If you were a vegetable," +
                    "you would be a cute-cumber", 12789);
            Post post9 = new Post(user17, "", "Incredible", 12537);
            Post post10 = new Post(user11, "We can all relate to this one", "When she comes home...", 10000);
            Post post11 = new Post(user14, "Not all cats hate water", "He willingly jumped into the bath tub :)", 13890);
            Post post12 = new Post(user12, "Who's the fuck wrote this book????", "True Faith and Allegiance", 15000);
            Post post13 = new Post(user10, "This uncut dorito", "i have more than i can imagine", 15000);
            Post post14 = new Post(user19, "", "Incredible", 12537);
            Post post15 = new Post(user15, "Bill Gates with the first version of " +
                    "Microsoft Windows 1980s", "Historical Moment", 12537);
            Post post16 = new Post(user16, "Cat is the best animal", "Cat gif", 500);
            Post post17 = new Post(user2, "2018 hair trend", "weird hair", 150);
            Post post18 = new Post(user5, "Tinder memes", "click to see more", 299);
            Post post19 = new Post(user3, "Jumping over a parachute", "he was nearly dead", 189);
            Post post20 = new Post(user7, "That was unexpected", "yeeeep", 357);
            Post post6 = new Post(user18, "Cool dog actions", "Incredible", 12537);


            Comment comment1 = new Comment(user6, post1,"It's so weirdddd");
            Comment comment2 = new Comment(user7, post11,"I wish you're gonna get better buddy. Too bad!");
            Comment comment3 = new Comment(user8, post3,"Why's it funny? What's wrong with you people");
            Comment comment4 = new Comment(user9, post12,"Wowwwww");
            Comment comment5 = new Comment(user10, post5,"That's what she said");
            Comment comment6 = new Comment(user18, post1,"You sucks");
            Comment comment7 = new Comment(user16, post2,"lol");
            Comment comment8 = new Comment(user11, post4,"hahahhaha");
            Comment comment9 = new Comment(user20, post6,"Poor you");
            Comment comment10 = new Comment(user13, post5,"That is not trueeeee!!!");

            userServiceDb.save(user1);
            userServiceDb.save(user2);
            userServiceDb.save(user3);
            userServiceDb.save(user4);
            userServiceDb.save(user5);
            userServiceDb.save(user6);
            userServiceDb.save(user7);
            userServiceDb.save(user8);
            userServiceDb.save(user9);
            userServiceDb.save(user10);
            userServiceDb.save(user11);
            userServiceDb.save(user12);
            userServiceDb.save(user13);
            userServiceDb.save(user14);
            userServiceDb.save(user15);
            userServiceDb.save(user16);
            userServiceDb.save(user17);
            userServiceDb.save(user18);
            userServiceDb.save(user19);
            userServiceDb.save(user20);

            postServiceDb.save(post1);
            postServiceDb.save(post2);
            postServiceDb.save(post3);
            postServiceDb.save(post4);
            postServiceDb.save(post5);
            postServiceDb.save(post7);
            postServiceDb.save(post8);
            postServiceDb.save(post9);
            postServiceDb.save(post10);
            postServiceDb.save(post11);
            postServiceDb.save(post12);
            postServiceDb.save(post13);
            postServiceDb.save(post14);
            postServiceDb.save(post15);
            postServiceDb.save(post16);
            postServiceDb.save(post17);
            postServiceDb.save(post18);
            postServiceDb.save(post19);
            postServiceDb.save(post20);
            postServiceDb.save(post6);

            commentServiceDb.save(comment1);
            commentServiceDb.save(comment2);
            commentServiceDb.save(comment3);
            commentServiceDb.save(comment4);
            commentServiceDb.save(comment5);
            commentServiceDb.save(comment6);
            commentServiceDb.save(comment7);
            commentServiceDb.save(comment8);
            commentServiceDb.save(comment9);
            commentServiceDb.save(comment10);

            RedditUser user1 = new RedditUser("Annie", "12345");
            RedditUser user2 = new RedditUser("Rubble", "iamnotfat");
            RedditUser user3 = new RedditUser("Sonic", "idonteatalot");
            RedditUser user4 = new RedditUser("Krisz", "iamsuperhandsome");
            RedditUser user5 = new RedditUser("Bea", "lovedrawing");
            RedditUser user6 = new RedditUser("Sally", "idontknowwhoiam");
            RedditUser user7 = new RedditUser("John", "madeupperson");
            RedditUser user8 = new RedditUser("LadyKiller", "notkilllady");
            RedditUser user9 = new RedditUser("handsomeboy_903", "12345");
            RedditUser user10 = new RedditUser("CuteDog", "donthatecat");

            Post post1 = new Post(user1, "Cat is the best animal", "Cat gif", 500);
            Post post2 = new Post(user2, "2018 hair trend", "weird hair", 150);
            Post post3 = new Post(user3, "Tinder memes", "click to see more", 299);
            Post post4 = new Post(user4, "Jumping over a parachute", "he was nearly dead", 189);
            Post post5 = new Post(user5, "That was unexpected", "yeeeep", 357);
            Post post6 = new Post(user4, "Handsome guy fixes his wife's code", "Incredible", 12537);

            Comment comment1 = new Comment(user6, post1,"You sucks");
            Comment comment2 = new Comment(user7, post2,"lol");
            Comment comment3 = new Comment(user8, post3,"hahahhaha");
            Comment comment4 = new Comment(user9, post4,"Poor you");
            Comment comment5 = new Comment(user10, post5,"That is not trueeeee!!!");

            userServiceDb.save(user1);
            userServiceDb.save(user2);
            userServiceDb.save(user3);
            userServiceDb.save(user4);
            userServiceDb.save(user5);
            userServiceDb.save(user6);
            userServiceDb.save(user7);
            userServiceDb.save(user8);
            userServiceDb.save(user9);
            userServiceDb.save(user10);

            postServiceDb.save(post1);
            postServiceDb.save(post2);
            postServiceDb.save(post3);
            postServiceDb.save(post4);
            postServiceDb.save(post5);
            postServiceDb.save(post6);

            commentServiceDb.save(comment1);
            commentServiceDb.save(comment2);
            commentServiceDb.save(comment3);
            commentServiceDb.save(comment4);
            commentServiceDb.save(comment5);
            */
