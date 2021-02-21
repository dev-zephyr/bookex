package com.zephyr.bookex.web;

import com.zephyr.bookex.domain.config.auth.LoginUser;
import com.zephyr.bookex.domain.config.auth.dto.SessionUser;
import com.zephyr.bookex.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null) {
            model.addAttribute("userName", user.getName());
            System.out.println("-----------------------------------------");
            System.out.println(user.getName());
            System.out.println(user.getEmail());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable("id") Long id, Model model) {

        model.addAttribute("post", postsService.findById(id));

        return "posts-update";
    }

}
