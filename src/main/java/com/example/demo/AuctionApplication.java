package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuctionApplication implements CommandLineRunner {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AuctionRepository auctionRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuctionApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Users user = new Users("Kungsgatan 3", "Stockholm", "1999-10-10", "test@test.com", "Johan", "Svensson", "123456", null, "Johan",
                               "123 12");

        Users user2 = new Users("Kungsgatan 2", "Stockholm", "1998-10-10", "test2@test.com", "Adam", "Svensson", "123456", null, "Adam", "123 13");
        usersRepository.save(user);
        usersRepository.save(user2);

        Auction auction = new Auction("Rosa med ett hål för huvudet Rosa med ett hål för huvudet Rosa med ett hål för huvudet Rosa med ett hål för huvudet Rosa med ett hål för huvudet Rosa med ett hål för huvudet Rosa med ett hål för huvudet Rosa med ett hål för huvudet", "2021-08-30T10:11:12", false, "Bodies", "74827b6a-92b6-4a13-8a62-9491e4db68d1.jpg", 150, 50,
                                      "rosa tröja", null, user, "one");

        Auction auction2 = new Auction("Blå med ett hål för huvudet", "2021-08-30T10:11:12", false, "Byxor-shorts", "74827b6a-92b6-4a13-8a62-9491e4db68d1.jpg", 150, 50, "blå tröja",
                                      null, user, "one");

        Auction auction3 = new Auction("Röd med ett hål för huvudet", "2021-08-30T10:11:12", false, "Bodies", "74827b6a-92b6-4a13-8a62-9491e4db68d1.jpg", 150, 50, "röd tröja",
                                      null, user, "six");

        Auction auction4 = new Auction("Lila med ett hål för huvudet", "2021-08-30T10:11:12", false, "Tröjor-t-shirts", "74827b6a-92b6-4a13-8a62-9491e4db68d1.jpg", 150, 50,
                                       "lila tröja", null, user, "twelve");

        Auction auction5 = new Auction("AVSLUTAD AUKTION", "2021-08-30T10:11:12", true, "Ytterkläder", "74827b6a-92b6-4a13-8a62-9491e4db68d1.jpg", 150, 50,
                                       "lila tröja", null, user2, "six");

        auctionRepository.save(auction);
        auctionRepository.save(auction2);
        auctionRepository.save(auction3);
        auctionRepository.save(auction4);
        auctionRepository.save(auction5);



    }

}
