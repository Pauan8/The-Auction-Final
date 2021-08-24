package com.example.demo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<Bid> bidList;
    @OneToOne
    private Invoice invoice;

    private int startPrice;
    private int reservationPrice;
    //private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private String title;
    private String description;
    private String keyWords;

    private boolean finished=false;

    @ManyToOne
    private User user;

    private String pictureAddress;

    public Auction() {
    }

    public Auction(int startPrice, int reservationPrice, LocalDateTime endDateTime, String title, String description, String keyWords) {
        this.startPrice = startPrice;
        this.reservationPrice = reservationPrice;
        this.endDateTime = endDateTime;
        this.title = title;
        this.description = description;
        this.keyWords = keyWords;
    }
    public void addBid(Bid bid){
        bidList.add(bid);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getReservationPrice() {
        return reservationPrice;
    }

    public void setReservationPrice(int reservationPrice) {
        this.reservationPrice = reservationPrice;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = LocalDateTime.parse(endDateTime);
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

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }
}
