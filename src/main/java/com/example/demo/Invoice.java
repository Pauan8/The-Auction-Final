package com.example.demo;

import javax.persistence.*;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Users users;
    @OneToOne
    private Auction auction;
    private int shipping;

    public Invoice() {
    }

    public Invoice( int shipping) {
        this.shipping = shipping;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }
}
