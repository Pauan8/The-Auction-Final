package com.example.demo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Bid> bidList;
    @OneToOne
    private SalesItem salesItem;
    @OneToOne
    private Invoice invoice;

    private int startPrice;
    private int reservationPrice;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Auction() {
    }

    public Auction(SalesItem salesItem,
                   int startPrice,
                   int reservationPrice,
                   LocalDateTime startDateTime,
                   LocalDateTime endDateTime,
                   Invoice invoice) {
        this.salesItem = salesItem;
        this.startPrice = startPrice;
        this.reservationPrice = reservationPrice;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.invoice = invoice;
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
