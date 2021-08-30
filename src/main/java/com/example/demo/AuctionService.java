package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Auction> filter(String[] arr, List<Auction> auctions){

        for (String selection : arr) {
            if (!selection.equals("0")) {
                for(Auction auc : auctionRepository.findAuctionByAgeSpan(selection)){
                    auctions.add(auc);
                }
            }
        }

        return auctions;
    }
}
