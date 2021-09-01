package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuctionController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    AuctionService auctionService;

    @Autowired
    BidRepository bidRepository;

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


    @GetMapping("/filter")
    public String search(@RequestParam(required = false, defaultValue = "0") String searchText,
                         @RequestParam(required = false, defaultValue = "0") String[] age,
                         @RequestParam(required = false, defaultValue = "0") String[] city,
                         @RequestParam(required = false, defaultValue = "0") String[] category,
                         @RequestParam(required = false, defaultValue = "0") Boolean isRedirected,
                         Model model, HttpSession session) {

        List<Auction> auctions = new ArrayList<>();
        String s;
        String[] searchWordArray;

        if (searchText.equals("0")) {
            s = (String) session.getAttribute("searchText");

        } else {
            s = session.getAttribute("searchText") + " " + searchText;
        }
        session.setAttribute("searchText", s );

        if(isRedirected != true){
            session.setAttribute("category", category);
            session.setAttribute("age", age);
            session.setAttribute("city", city);
            System.out.println("göt detta");

        }


        if (session.getAttribute("searchText") != null) {
            searchWordArray = ((String) session.getAttribute("searchText")).split(" ");
        } else {
            searchWordArray = null;
        }

        String[] searchWords;
        if(searchWordArray != null){
            searchWords = Arrays.stream(searchWordArray).distinct().toArray(String[]::new);
        } else {
            searchWords = null;
        }

        List<Auction> search = auctionService.searchFilter(searchWords);
        List<Auction> ages = auctionService.filterByType("age", (String[])session.getAttribute("age"));
        List<Auction> cities = auctionService.filterByType("city", (String[])session.getAttribute("city"));
        List<Auction> categories = auctionService.filterByType("category", (String[])session.getAttribute("category"));

        auctions = auctionService.filter(search, ages, cities, categories, auctions);

        System.out.println("cities " + cities.size() + " categories: " + categories.size() + " ages: " + ages.size());
        model.addAttribute("auctions", auctions);
        session.setAttribute("searchWords", searchWords);
        return "index";
    }

    @GetMapping("/filter/remove")
    public String search(@RequestParam(required = false, defaultValue = "0") String searchText, HttpSession session, RedirectAttributes redirectAttributes){
        String newSearchWords= "";
        for(String word : ((String)session.getAttribute("searchText")).split(" ")){
            if(!word.equals(searchText)){
                if(!word.equals("null")) {
                    newSearchWords += word;
                }
            }
        }
        if(newSearchWords.length() == 0){
            newSearchWords = null;
        }
        session.setAttribute("searchText", newSearchWords);
        redirectAttributes.addAttribute("isRedirected", true);
        return "redirect:/filter";
    }



    @GetMapping("/search")
    public String search(@RequestParam String searchText, Model model) {
        String[] searchWordArray = searchText.split(" ");
        String searchWord = "%";

        for (String word : searchWordArray) {
            searchWord += word + "%";
        }
        System.out.println(searchWord);
        List<Auction> auctions = auctionRepository.findByPartialKeyword(searchWord);
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
        if(session.getAttribute("users") == null) {
            return "redirect:/";
        }
        session.setAttribute("users",null);

        return "redirect:/";
    }
}


