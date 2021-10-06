package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuctionService {

    @Autowired
    AuctionRepository auctionRepository;

    public boolean isBidHighEnough(Bid bid, Auction auction) {
        if (bid.getAmount() < auction.getStartPrice()) {
            return false;
        }
        return bid.getAmount() > getTopBid(auction);
    }

    public int getTopBid(Auction auction) {
        if (auction.getBidList().size() == 0) {
            return 0;
        }
        Bid highestBid = auction.getBidList()
            .stream()
            .max(Comparator.comparing(Bid::getAmount))
            .orElseThrow(NoSuchElementException::new);

        return highestBid.getAmount();
    }
    public List<Auction> searchFilter(String[] searchArray) {
        List<Auction> auctions = new ArrayList<>();
        if (searchArray == null) {
            auctions.addAll(auctionRepository.findAll());
        } else {
            for(String word : searchArray){
                auctions.addAll(auctionRepository.findByPartialKeyword("%" + word + "%"));
            }
        }

        return auctions;
    }

    public List<Auction> filterByType(String type, String[] typeArray) {
        List<Auction> auctions = new ArrayList<>();
        List<List<Auction>> auctionList;
           auctionList = Arrays.stream(typeArray).map(selection -> {
               switch (type) {
                   case "age":
                       return auctionRepository.findAuctionByAgeSpanIgnoreCase(selection);
                   case "city":
                       return auctionRepository.findAuctionBySalesAreaIgnoreCase(selection);
                   case "category":
                       return auctionRepository.findAuctionByKeyWordsIgnoreCase(selection);
                   default: return null;
               }
           }).collect(Collectors.toList());

            auctionList.forEach(list -> auctions.addAll(list));

        return typeArray[0].equals("0") ? auctionRepository.findAll() : auctions;
    }

    public List<Auction> filter(String[] searchWordArray, String[] age, String[] city, String[] category) {
        List<List<Auction>> arrays = new ArrayList<>();
        arrays.add(searchFilter(searchWordArray));
        arrays.add(filterByType("age", age));
        arrays.add(filterByType("city", city));
        arrays.add(filterByType("category", category));


        return arrays.stream()
            .reduce((arr0, arr1) -> arr0.stream()
                .filter(auction -> arr1.contains(auction))
                .collect(Collectors.toList()))
            .stream()
            .collect(Collectors.toList())
            .get(0);
    }

    public String[] searchArrayBuilder(HttpSession session, String input){
        String searchWords;
        searchWords = input.equals("0")
            ? validateInput(session.getAttribute("searchText"))
            : session.getAttribute("searchText") + " " + input;

        session.setAttribute("searchText", validateInput(searchWords));

        return session.getAttribute("searchText") != null
            ? ((String) session.getAttribute("searchText")).split(" ")
            : null;
    }

    public String validateInput(Object input){
        return input == null ? null : input.toString().toLowerCase();
    }

}
