package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AuctionController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @GetMapping("/")
    public String home(HttpSession session){
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute User user, HttpSession session){
        userRepository.save(user);
        session.setAttribute("user",user);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(){
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String searchText){

        return "index";
    }

    @GetMapping("/detail")
    public String detail(){
        return "detail";
    }

    @GetMapping("/upload")
    public String upload(Model model){
        Auction auction = new Auction();
        model.addAttribute("auction", auction);
        return "upload";
    }

    @PostMapping("/upload")
    public String postUpload(@ModelAttribute Auction auction, HttpSession session){
        auction.setUser((User)session.getAttribute("user"));
        auctionRepository.save(auction);
        return "redirect:/";
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
