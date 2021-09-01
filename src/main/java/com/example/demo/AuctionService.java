package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
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
        Bid highestBid = auction.getBidList().stream().max(Comparator.comparing(Bid::getAmount)).orElseThrow(NoSuchElementException::new);
        return highestBid.getAmount();
    }

    public List<Auction> searchFilter(String[] searchArray) {
        List<Auction> auctions = new ArrayList<>();


        if (searchArray != null) {
            if (searchArray.length == 1 && searchArray[0].equals("null")){
                System.out.println("den är null");
                auctions = auctionRepository.findAll();
            } else {
                for (String selection : searchArray) {
                        String wordTemp = "%" + selection + "%";
                        System.out.println(wordTemp);
                        for (Auction searchAuction : auctionRepository.findByPartialKeyword(wordTemp)) {
                            auctions.add(searchAuction);
                        }
                }
            }

        } else {
            auctions = auctionRepository.findAll();
        }

        return auctions;
    }

    public List<Auction> filterByType(String type, String[] typeArray) {
        List<Auction> auctions = new ArrayList<>();
        if(typeArray != null){
            if (!typeArray[0].equals("0")) {
                for (String selection : typeArray) {
                    if (type.equals("age")) {
                        for (Auction auc : auctionRepository.findAuctionByAgeSpanIgnoreCase(selection)) {
                            auctions.add(auc);
                        }
                    } else if (type.equals("city")) {
                        for (Auction auc : auctionRepository.findAuctionBySalesAreaIgnoreCase(selection)) {
                            auctions.add(auc);
                        }
                    } else if (type.equals("category")) {
                        for (Auction auc : auctionRepository.findAuctionByKeyWordsIgnoreCase(selection)) {
                            auctions.add(auc);
                        }
                    }
                }
            } else {
                auctions = auctionRepository.findAll();
            }
            }  else   {
                auctions = auctionRepository.findAll();
            }
        return auctions;
    }

    public List<Auction> filter(List<Auction> arr0, List<Auction> arr1, List<Auction> arr2, List<Auction> arr3, List<Auction> auctions) {

        for (Auction auc : arr0) {
            for (Auction auc1 : arr1) {
                if (auc == auc1) {
                    for (Auction auc2 : arr2) {
                        if (auc == auc2) {
                            for (Auction auc3 : arr3) {
                                if (auc == auc3) {
                                    auctions.add(auc);
                                }
                            }
                        }
                    }
                }
            }
        }

        return auctions;
    }
}
