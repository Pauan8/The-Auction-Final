package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.NoSuchElementException;

@Service
public class AuctionService {


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
}
