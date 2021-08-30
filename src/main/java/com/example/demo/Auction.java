package com.example.demo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.time.format.DateTimeFormatter;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<Bid> bidList;
    @OneToOne
    private Invoice invoice;

    private int startPrice;
    private int reservationPrice;
    //private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private String title;
    private String description;
    private String keyWords; //TODO List
    private String ageSpan;
    private String salesArea;

    private boolean finished=false;

    @ManyToOne
    private Users users;

    private String pictureAddress;

    public Auction() {
    }

    public Auction(int startPrice, int reservationPrice, LocalDateTime endDateTime, String title, String description, String keyWords, String ageSpan, String salesArea) {
        this.startPrice = startPrice;
        this.reservationPrice = reservationPrice;
        this.endDateTime = endDateTime;
        this.title = title;
        this.description = description;
        this.keyWords = keyWords;
        this.ageSpan = ageSpan;
        this.salesArea = salesArea;
    }

    public Auction(String DESCRIPTION,String END_DATE_TIME, boolean FINISHED, String KEY_WORDS,String PICTURE_ADDRESS,int RESERVATION_PRICE,int START_PRICE,
                   String TITLE,Long INVOICE_ID,Users user, String AGE_SPAN, String SALES_AREA){

        this.description = DESCRIPTION;
        this.endDateTime = LocalDateTime.parse(END_DATE_TIME);
        this.finished = FINISHED;
        this.keyWords = KEY_WORDS;
        this.pictureAddress = PICTURE_ADDRESS;
        this.reservationPrice = RESERVATION_PRICE;
        this.startPrice = START_PRICE;
        this.title = TITLE;
        this.users = user;
        this.invoice = null;
        this.ageSpan = AGE_SPAN;
        this.salesArea = SALES_AREA;
    }
    

    public String getSalesArea() {
        return salesArea;
    }

    public void setSalesArea(String salesArea) {
        this.salesArea = salesArea;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getAgeSpan() {
        return ageSpan;
    }

    public void setAgeSpan(String ageSpan) {
        this.ageSpan = ageSpan;
    }

    public void addBid(Bid bid){
        bidList.add(bid);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getReservationPrice() {
        return reservationPrice;
    }

    public void setReservationPrice(int reservationPrice) {
        this.reservationPrice = reservationPrice;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = LocalDateTime.parse(endDateTime);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String printEndDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.endDateTime.format(formatter);
    }
    /*
    public int getNumberOfBids(){

        return bidList.
    }
     */

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }

    public String getShortDescription(){
        if(description.length() > 100){
            return description.substring(0,95)+"...";
        }
         return description;
    }
}
