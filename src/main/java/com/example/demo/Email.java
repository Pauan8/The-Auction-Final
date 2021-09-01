package com.example.demo;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class Email {

    public void sendEmailAuctionEnd(Auction auction)
            throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Users uploader = auction.getUsers();
        Bid highestBid = auction.getTopBidObject();
        Users winner = highestBid.getUser();

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("the.auction.test@gmail.com",
                                                  "AWAcademy123");
            }
        });

        // FOR WINNER
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("the.auction.test@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO,
                          InternetAddress.parse(winner.getEmail()));

        msg.setSubject("The auction - Du har vunnit objektet: " + auction.getTitle());
        msg.setContent("Grattis! Du är det vinnande budet för objektet: \n"
                       + "<a href='http://auction-env-2.eba-hgds3swk.eu-west-2.elasticbeanstalk.com/detail/"
                       + auction.getId()
                       + "'>" + auction.getTitle() + "</a>"
                       + "\n\n\n Du kan kontakta säljaren nedanför:\n\n"
                       + "Namn: "
                       + uploader.getFirstName()
                       + " "
                       + uploader.getLastName()
                       + "\n"
                       + "Email: "
                       + uploader.getEmail()
                       + "\n Belopp att swisha: "
                       + auction.getTopBid()
                       + "\n Till tfn-nummer: +46704948308",
                       "text/html");
        msg.setSentDate(new Date());
        Transport.send(msg);

        // FOR SELLER
        msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("the.auction.test@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO,
                          InternetAddress.parse(uploader.getEmail()));

        msg.setSubject("The auction - Du har en avslutad auktion!");
        msg.setContent("Grattis! Du har en köpare för objektet: \n"
                       + "<a href='http://auction-env-2.eba-hgds3swk.eu-west-2.elasticbeanstalk.com/detail/"
                       + auction.getId()
                       + "'>"
                       + auction.getTitle()
                       + "</a>"
                       + "\n\n\n Du kan kontakta köparen nedanför:\n\n"
                       + "Namn: "
                       + winner.getFirstName()
                       + " "
                       + winner.getLastName()
                       + "\n"
                       + "Email: "
                       + winner.getEmail()
                       + "\n Belopp att mottaga: "
                       + auction.getTopBid()
                       + "\n Köparens tfn-nummer: +46704948308",
                       "text/html");
        msg.setSentDate(new Date());
        Transport.send(msg);
    }

}
