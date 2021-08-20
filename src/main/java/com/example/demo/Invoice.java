package com.example.demo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Bid bidId;
    private int shipping;

    public Invoice() {
    }

    public Invoice(Bid bidId, int shipping) {
        this.bidId = bidId;
        this.shipping = shipping;
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

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }
}
