package com.example.demo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Auction auction;
    @ManyToOne
    private Users users;
    private int amount;
    private LocalDateTime bidDateTime;

    public Bid() {
    }

    public Bid(Auction auction, Users users, int amount, LocalDateTime bidDateTime) {
        this.auction = auction;
        this.users = users;
        this.amount = amount;
        this.bidDateTime = bidDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Auction getAuction() {
        return this.auction;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getBidDateTime() {
        return bidDateTime;
    }

    public void setBidDateTime(LocalDateTime bidDateTime) {
        this.bidDateTime = bidDateTime;
    }



}
