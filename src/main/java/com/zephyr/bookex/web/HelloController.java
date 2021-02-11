package com.zephyr.bookex.web;

import com.zephyr.bookex.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String helloGET() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name
                                   , @RequestParam("amount") int amount) {

        return new HelloResponseDto(name, amount);
    }
}
