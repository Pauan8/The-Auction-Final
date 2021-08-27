package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface AuctionRepository extends CrudRepository<Auction,Long> {
    List<Auction> findAll();

    @Query(value = "SELECT * FROM AUCTION WHERE KEY_WORDS LIKE ?1", nativeQuery = true)
    List<Auction> findByPartialKeyword(String keywordPattern);

    List<Auction> findAuctionByKeyWords(String keyWord);

    List<Auction> findAllByEndDateTimeLessThan(LocalDateTime datetime);

    List<Auction> findAllByFinishedFalse();

}
