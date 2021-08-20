package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<Auction,Long> {
}
