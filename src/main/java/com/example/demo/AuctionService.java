package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuctionService {

    @Autowired
    AuctionRepository auctionRepository;

    public boolean isBidHighEnough(Bid bid, Auction auction){
        if(bid.getAmount() < auction.getStartPrice()){
            return false;
        }
        return bid.getAmount() > getTopBid(auction);
    }

    public int getTopBid(Auction auction){

        if(auction.getBidList().size() == 0){
            return 0;
        }
        Bid highestBid = auction.getBidList().stream().max(Comparator.comparing(Bid::getAmount)).orElseThrow(NoSuchElementException::new);
        return highestBid.getAmount();
    }

    public List<Auction> filter(List<Auction> arr, List<Auction> arr2, List<Auction> arr3){
        List<Auction> auctions = new ArrayList<>();
        List<Auction> newarr = new ArrayList<>();

        for(Auction auc : arr) {
                for (Auction auc2 : arr2) {
                    if (auc == auc2) {
                        newarr.add(auc);
                        System.out.println("filter1");
                    }
                }
            }

        if(newarr.size() != 0) {
            for (Auction auc : newarr) {
                    for (Auction auc3 : arr3) {
                        if (auc == auc3) {
                            auctions.add(auc);
                            System.out.println("filter2");
                        }
                    }
                }
        } else {
            auctions = newarr;
        }

        return auctions;
    }
}
