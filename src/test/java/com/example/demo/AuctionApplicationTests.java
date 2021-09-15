package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class AuctionApplicationTests {
    @Autowired
    AuctionService auctionService;

    @Test
    void testFilter() throws Exception {
        List<Auction> auctions = auctionService.filterByType("age", new String[]{"six"});
        Assertions.assertEquals(2, auctions.size());
        List<Auction> auctions2 = auctionService.filterByType("category", new String[]{"accessoarer"});
        Assertions.assertEquals(2, auctions2.size());
    }

}
