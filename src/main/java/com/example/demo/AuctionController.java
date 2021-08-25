package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuctionController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    AuctionService auctionService;

    @GetMapping("/")
    public String home(HttpSession session, Model model) {

        List<Auction> auctions = (List)auctionRepository.findAll();
        model.addAttribute("auctions", auctions);
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute User user, HttpSession session) {
        userRepository.save(user);
        session.setAttribute("user", user);
        return "redirect:/";
    }


    @GetMapping("/search")
    public String search(@RequestParam String searchText, Model model) {
        String[] keywordsArray = searchText.split(" ");
        String keyWord = "%;";
        for (String k : keywordsArray) {
            keyWord = keyWord + k + ";%";
        }
        // %;hemelektronik;%;kläder;%
        // ;hemelektronik;möbler;kläder;
        List <Auction> auctions = auctionRepository.findByPartialKeyword(keyWord);
        model.addAttribute("auctions", auctions);
        return "index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable(required = true) Long id) {
        Bid bid = new Bid();
        if (auctionRepository.findById(id).isPresent()) {
            bid.setAuction(auctionRepository.findById(id).get());
            Auction auction = auctionRepository.findById(id).get();
            model.addAttribute("highestBid", auctionService.getTopBid(auction));
            model.addAttribute("auction", auction);
        }

        model.addAttribute("bid", bid);
        return "detail";
    }

    @GetMapping("/upload")
    public String upload(Model model) {
        Auction auction = new Auction();
        model.addAttribute("auction", auction);
        return "upload";
    }

    @PostMapping("/upload")
    public String postUpload(@ModelAttribute Auction auction, @RequestParam("image") MultipartFile multipartFile, HttpSession session) throws IOException {

        String fileName = UUID.randomUUID().toString() + "." + multipartFile.getOriginalFilename().split("\\.")[1];
        auction.setPictureAddress(fileName);

        UploadObject.upload(fileName, multipartFile);

        auction.setUser((User) session.getAttribute("user"));
        auctionRepository.save(auction);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/profile")
    public String profilePost() {
        return "profile";
    }

    @PostMapping("/bid")
    public String postBid(@ModelAttribute Bid bid, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Auction auction = bid.getAuction();
        if (auctionService.isBidHighEnough(bid, bid.getAuction())) {
            bid.setUser(user);
            LocalDateTime timeNow = LocalDateTime.now();
            bid.setBidDateTime(timeNow);
            auction.addBid(bid);
            auctionRepository.save(auction);
            model.addAttribute("highestBid", auctionService.getTopBid(auction));
        }

        return "redirect:/detail/" + auction.getId();
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loggingIn(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return "login";
        }
        if (user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return "index";
        }
        return "login";
    }
}


  /*  @PostMapping("/users/login-user")
    @ResponseBody
    public ApiResponse<?> loginUser(@Valid @RequestBody User user){

        logger.info("Inside Login User");
        User userEmailExists = userService.findUserByEmail(user.getEmail());
        User userMobileExists = userService.findUserByMobile((user.getMobile()));
        String existingPassword =userEmailExists.getPassword();
        String currentPassword=user.getPassword();

        if (userEmailExists.getEmail().isEmpty()) {
            return new ApiResponse<>("\"Oops.! User email not found, please register.\"", com.bfarming.response.ResponseStatus.getValidResponseStatus(HttpStatus.OK));
        }else if(userMobileExists.getMobile().isEmpty()){
            return new ApiResponse<>("\"Oops.! User mobile not found, please register.\"", com.bfarming.response.ResponseStatus.getValidResponseStatus(HttpStatus.OK));
        }else if (bcryptGenerator.passwordDecoder(currentPassword,existingPassword)) {
            return new ApiResponse<>("\"Password Exists, logged-in\"");
        }else {
            return new ApiResponse<>("\"Password didn't match, please enter the correct password, logged-in\"");
        }
    }*/
