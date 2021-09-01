package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface AuctionRepository extends CrudRepository<Auction,Long> {
    List<Auction> findAll();

    @Query(value = "SELECT * FROM AUCTION WHERE TITLE LIKE ?1", nativeQuery = true)
    List<Auction> findByPartialKeyword(String searchWordPattern);

    List<Auction> findAuctionByKeyWordsIgnoreCase(String keyWord);

    List<Auction> findAllByEndDateTimeLessThan(LocalDateTime datetime);

    List<Auction> findAllByFinishedFalse();

    List<Auction> findAllByUsersId(Long id);

    List<Auction> findAuctionByAgeSpanIgnoreCase(String ageSpan);

    List<Auction> findAuctionBySalesAreaIgnoreCase(String salesArea);

    @Query(value = "SELECT * FROM AUCTION INNER JOIN BID ON  AUCTION.ID = BID.AUCTION_ID INNER JOIN USERS ON USERS.ID = BID.USERS_ID WHERE USERS.ID = ?1", nativeQuery = true)
    List<Auction> findAuctionByBidder(Long id);

}
