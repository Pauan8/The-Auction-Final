package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuctionController {


    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/login")
    public String login(){
        return "index";
    }

    @GetMapping("/detail")
    public String detail(){
        return "detail";
    }

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping("/upload")
    public String postUpload(){
        return "upload";
    }


    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @PostMapping("/profile")
    public String profilePost(){
        return "profile";
    }

    @PostMapping("/bid")
    public String postBid(){
        return "detail";
    }


}
