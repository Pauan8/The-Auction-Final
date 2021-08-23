package com.example.demo;

import javax.persistence.*;


@Entity
public class SalesItem {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String keyWords;
    @OneToOne
    private Auction auction;
    @ManyToOne
    private User user;

    public SalesItem(String title,
                     String description,
                     String keyWords,
                     User user,
                     Auction auction) {
        this.title = title;
        this.description = description;
        this.keyWords = keyWords;
        this.user = user;
        this.auction = auction;
    }

    public SalesItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
