package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AuctionApplication {

    @Autowired
    OurService ourService;

    public static void main(String[] args) {
        SpringApplication.run(AuctionApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AuctionRepository auctionRepository,
                                  UsersRepository usersRepository, BidRepository bidRepository) {
        return (args) -> {
            Users user = new Users("Kungsgatan 3", "Stockholm", "1999-10-10",
                                   "mailafilip@gmail.com", "Johan", "Svensson", "123456",
                                   null, "Johan",
                                   "123 12");

            Users user2 =
                    new Users("Kungsgatan 2", "Stockholm", "1998-10-10",
                              "yihanc92@live.se",
                              "Adam", "Svensson", "123456", null, "Adam", "123 13");
            usersRepository.save(user);
            usersRepository.save(user2);

            Auction auction = new Auction(
                    "Randig mössa i mjuk ekologisk bomull. Mössan har vår klassiska rand och är fodrad med mjuk och värmande fleece i återvunnen polyester.",
                    "2021-09-25T15:11:12", false, "accessoarer", "7325856151854.jpg", 75,
                    50,
                    "Randig mössa med fleecefoder vinröd", null, user, "one", "lund");

            Auction auction2 = new Auction(
                    "Svep in den lilla, med vår mjuka babyfilt i mjuk ekologisk bomull. Ena sidan har ett tryck med lekande djur, och den andra är randig. Mått: 84 x 84 cm",
                    "2021-09-25T19:11:12", false, "accessoarer", "7325856168135_1.jpg",
                    150,
                    50, "Filt med lekande djur baby grön",
                    null, user, "one", "stockholm");

            Auction auction3 = new Auction(
                    "Enfärgade byxor i 100% ekologisk bomull. Byxorna har mjuk mudd i midjan och vid benslut som kan vikas ned när barnet växer.",
                    "2021-09-05T10:11:12", false, "byxor-shorts", "7325856240473_1.jpg",
                    150,
                    50, "Enfärgade byxor baby gråmelange",
                    null, user, "six", "göteborg");

            Auction auction4 = new Auction(
                    "Gör ditt barn redo för regninga dagar i den här vind- och vattentät regnjacka! Jackan har en avtagbar huva som fästs med tryckknappar och insidan av kragan har ett mjukt fleecefoder. Jackan har reflexer fram- och bak, och stängs med tryckknappar. Storlek 74/80 har ingen huva.",
                    "2021-09-30T16:11:12", false, "regnkläder", "7325856097541.jpg", 150,
                    50,
                    "Regnjacka galon gammelrosa", null, user, "six", "post");

            Auction auction5 = new Auction(
                    "Enfärgad t-shirt i 100% ekologisk bomull. Perfekt att ha vid alla tillfällen! Plagget tillverkas i fyra olika färger, och varje färg har sitt unika tryck och matchar snyggt med hela kollektionen.",
                    "2021-09-03T13:44:12", false, "tröjor-t-shirts", "7325856186511.jpg",
                    150,
                    50,
                    "T-shirt med tryck beige", null, user2, "twelve", "malmö");


            auctionRepository.save(auction);
            auctionRepository.save(auction2);
            auctionRepository.save(auction3);
            auctionRepository.save(auction4);
            auctionRepository.save(auction5);

            ourService.checkEnd();


        };
    }

}
