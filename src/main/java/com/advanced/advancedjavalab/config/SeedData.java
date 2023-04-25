package com.advanced.advancedjavalab.config;

import com.advanced.advancedjavalab.models.Account;
import com.advanced.advancedjavalab.models.Authority;
import com.advanced.advancedjavalab.models.Post;
import com.advanced.advancedjavalab.repositories.AccountRepository;
import com.advanced.advancedjavalab.repositories.AuthorityRepository;
import com.advanced.advancedjavalab.services.AccountService;
import com.advanced.advancedjavalab.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if(posts.size() == 0) {

            Authority user1 = new Authority();
            user1.setName("ROLE_USER");
            authorityRepository.save(user1);

            Authority user2 = new Authority();
            user2.setName("ROLE_ADMIN");
            authorityRepository.save(user2);

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("user");
            account1.setLastName("user");
            account1.setEmail("user.user@domain.com");
            account1.setPassword("password");

            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1 :: add);
            account1.setAuthorities(authorities1);

            account2.setFirstName("admin");
            account2.setLastName("admin");
            account2.setEmail("admin.admin@domain.com");
            account2.setPassword("password");

            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2 :: add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2 :: add);
            account2.setAuthorities(authorities2);

            accountService.save(account1);
            accountService.save(account2);

            Post post1 = new Post();
            post1.setTitle("title of post1");
            post1.setBody("This is the body of post1");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("title of post2");
            post2.setBody("This is the body of post2");
            post2.setAccount(account2);
            postService.save(post1);
            postService.save(post2);
        }
    }
}
