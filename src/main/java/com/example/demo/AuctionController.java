package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

@Controller
public class AuctionController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    AuctionService auctionService;

    @GetMapping("/")
    public String home(HttpSession session, Model model) {

        List<Auction> auctions = auctionRepository.findAllByFinishedFalse();
        model.addAttribute("auctions", auctions);
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        Users users = new Users();
        model.addAttribute("users", users);
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute Users users,
                               BindingResult result,
                               HttpSession session) throws Exception {
        try {
            if (result.hasErrors()) {
                return "register";
            }
            usersRepository.save(users);
            session.setAttribute("users", users);
            return "redirect:/";
        } catch (org.springframework.dao.DataIntegrityViolationException jse) {
            System.out.println(jse);
            return "register";
        }
    }

    @GetMapping("/kategori")
    public String category(@RequestParam String category, Model model) {

        if (category.equals("Alla")) {
            List<Auction> auctions = auctionRepository.findAll();
            model.addAttribute("auctions", auctions);
            return "index";
        }

        List<Auction> auctions =
                auctionRepository.findAuctionByKeyWordsIgnoreCase(category);
        model.addAttribute("auctions", auctions);
        return "index";
    }

    @GetMapping("/filter")
    public String search(@RequestParam(required = false, defaultValue = "0") String[] age,
                         @RequestParam(required = false, defaultValue = "0") String[] city,
                         @RequestParam(required = false, defaultValue = "0") String[] category,
                         Model model) {
        List<Auction> auctions = new ArrayList<>();
        List<Auction> ages = new ArrayList<>();
        List<Auction> cities = new ArrayList<>();
        List<Auction> categories = new ArrayList<>();

        if (!age[0].equals("0")) {
            for (String selection : age) {
                for (Auction auc : auctionRepository.findAuctionByAgeSpanIgnoreCase(
                        selection)) {
                    ages.add(auc);
                }
            }
        } else {
            ages = auctionRepository.findAll();
        }

        if (!city[0].equals("0")) {
            for (String selection : city) {
                for (Auction auc : auctionRepository.findAuctionBySalesAreaIgnoreCase(
                        selection)) {
                    cities.add(auc);
                }
            }
        } else {
            cities = auctionRepository.findAll();
        }

        if (!category[0].equals("0")) {
            for (String selection : category) {
                for (Auction auc : auctionRepository.findAuctionByKeyWordsIgnoreCase(
                        selection)) {
                    categories.add(auc);
                }
            }
        } else {
            categories = auctionRepository.findAll();
        }

        auctions = auctionService.filter(ages, cities, categories, auctions);

        model.addAttribute("auctions", auctions);
        return "index";
    }


    @GetMapping("/search")
    public String search(@RequestParam String searchText, Model model) {
        String[] searchWordArray = searchText.split(" ");
        String searchWord = "%";

        for (int i = 0; i < searchWordArray.length; i++) {
            searchWord += searchWordArray[i] + "%";
        }
        System.out.println(searchWord);
        List<Auction> auctions =
                auctionRepository.findByPartialKeywordIgnoreCase(searchWord);
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
    public String postUpload(@ModelAttribute Auction auction,
                             @RequestParam("image") MultipartFile multipartFile,
                             HttpSession session) throws IOException {

        String fileName =
                UUID.randomUUID().toString() + "." + multipartFile.getOriginalFilename()
                                                                  .split("\\.")[1];
        auction.setPictureAddress(fileName);

        UploadObject.upload(fileName, multipartFile);
        auction.setTitle(auction.getTitle().toLowerCase());
        auction.setUsers((Users) session.getAttribute("users"));
        auctionRepository.save(auction);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {

        List<Auction> auctions = auctionRepository.findAllByUsersId(
                ((Users) session.getAttribute("users")).getId());
        model.addAttribute("auctions", auctions);
        return "profile";
    }

    @PostMapping("/passwordChange")
    public String profilePost(HttpSession session,
                              @RequestParam(required = false) String password,
                              @RequestParam(required = false) String password2,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String email2) {

        Users user = (Users) session.getAttribute("users");
        if (password.equals(password2)) {
            user.setPassword(password);
            usersRepository.save(user);
            return "redirect:/profile";

        }
        return "profile";
    }

    @PostMapping("/email")
    public String changeEmail(HttpSession session,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String email2) {

        Users user = (Users) session.getAttribute("users");

        if (email.equals(email2)) {
            user.setEmail(email);
            usersRepository.save(user);
            return "redirect:/profile";

        }
        return "profile";
    }

    @PostMapping("/profileLogut")
    public String profileLogut(HttpSession session) {
        session.setAttribute("users", null);
        return "redirect:/";
    }

    @PostMapping("/bid")
    public String postBid(@ModelAttribute Bid bid, HttpSession session, Model model,  HttpServletRequest request) {
        Users users = (Users) session.getAttribute("users");

        if (users == null) {
            return "redirect:/login";
        }

        Auction auction = bid.getAuction();

        System.out.println(auction.getUsers());
        System.out.println(bid.getUser());
        System.out.println(bid);
        System.out.println(users.getFirstName());

        if(auction.getUsers().getUsername().equals(users.getUsername())){
            String referer = request.getHeader("Referer");
            return "redirect:"+ referer;
        }

        if (auctionService.isBidHighEnough(bid, bid.getAuction())) {
            bid.setUser(users);
            LocalDateTime timeNow = LocalDateTime.now();
            bid.setBidDateTime(timeNow);
            auction.addBid(bid);
            auctionRepository.save(auction);
            model.addAttribute("highestBid", auctionService.getTopBid(auction));
        }

        return "redirect:/detail/" + auction.getId();
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {

        String referer = request.getHeader("Referer");
        System.out.println(referer);
        if (referer == null) {
            model.addAttribute("referer", "none");
        } else {
            model.addAttribute("referer", referer);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loggingIn(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            @RequestParam(required = false) String referer) {
        Users users = usersRepository.findByUsernameIgnoreCase(username);

        if (users == null) {
            return "redirect:/";
        }
        if (users.getPassword().equals(password)) {
            session.setAttribute("users", users);
            if (referer == null || referer.equals("none")) {
                return "redirect:/";
            } else {
                return "redirect:" + referer;
            }

        }

        return "login";
    }


    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        if (session.getAttribute("users") == null) {
            return "redirect:/";
        }
        session.setAttribute("users", null);

        return "redirect:/";
    }
}


