package com.example.demo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Bid bidId;
    private SalesItem salesItem;
    private int startPrice;
    private int reservationPrice;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Auction() {
    }

    public Auction(Bid bidId, SalesItem salesItem, int startPrice, int reservationPrice, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.bidId = bidId;
        this.salesItem = salesItem;
        this.startPrice = startPrice;
        this.reservationPrice = reservationPrice;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bid getBidId() {
        return bidId;
    }

    public void setBidId(Bid bidId) {
        this.bidId = bidId;
    }

    public SalesItem getSalesItem() {
        return salesItem;
    }

    public void setSalesItem(SalesItem salesItem) {
        this.salesItem = salesItem;
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
