package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class OurService {
    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    UsersRepository usersRepository;

    public void checkEnd() throws MessagingException, IOException, InterruptedException {

        Email email = new Email();
        while (true) {
            List<Auction> auctionList =
                    auctionRepository.findAllByEndDateTimeLessThan(
                            LocalDateTime.now());

            for (Auction a : auctionList) {
                a.setFinished(true);
                auctionRepository.save(a);
                if (a.getTopBidObject() != null) {
                    email.sendEmailAuctionEnd(a);
                }
            }
            System.out.println("Checked all auctions and set if they are finished");
            for (Users u: usersRepository.findAll()
                 ) {
                System.out.println(u.toString());
            }
            TimeUnit.MINUTES.sleep(1);
        }
    }
}
