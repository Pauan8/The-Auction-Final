package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


public interface AuctionRepository extends CrudRepository<Auction,Long> {


}
