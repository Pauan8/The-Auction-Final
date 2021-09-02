package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BidRepository extends CrudRepository<Bid,Long> {
    List<Bid> findAllByUsersId(Long id);
}
