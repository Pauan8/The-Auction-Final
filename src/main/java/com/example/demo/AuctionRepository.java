package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AuctionRepository extends CrudRepository<Auction,Long> {
    List<Auction> findAll();

}
