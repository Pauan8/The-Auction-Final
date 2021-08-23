package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


public interface BidRepository extends CrudRepository<Bid,Long> {
}
