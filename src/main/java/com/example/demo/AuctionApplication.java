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
        Users user = new Users("Kungsgatan 3", "Stockholm", "1999-10-10", "test@test.com", "Johan", "Svensson", "123", null, "Johan",
                               "123 12");

        Users user2 = new Users("Kungsgatan 2", "Stockholm", "1998-10-10", "test2@test.com", "Adam", "Svensson", "123", null, "Adam", "123 13");
        usersRepository.save(user);
        usersRepository.save(user2);



    }

}
