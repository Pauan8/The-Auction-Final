package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @Size(min=6, max=12)
    private String password;
    private String firstName;
    private String lastName;
    @Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Column(unique=true)
    private String email;

    private LocalDate dateOfBirth;

/*    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Bid> bidList;*/

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Auction> auctionList;

    private String address;
    private String zipCode;
    private String city;
    private String rating;

    public Users() {
    }

    public Users(String username,
                 String password,
                 String firstName,
                 String lastName,
                 String email,
                 LocalDate dateOfBirth,
                 String address,
                 String zipCode,
                 String city) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
    }

    public Users(String ADDRESS,
                 String CITY,
                 String DATE_OF_BIRTH,
                 String EMAIL,
                 String FIRST_NAME,
                 String LAST_NAME,
                 String PASSWORD,
                 String RATING,
                 String USERNAME,
                 String ZIP_CODE) {

        this.address = ADDRESS;
        this.city = CITY;
        this.email = EMAIL;
        this.firstName = FIRST_NAME;
        this.dateOfBirth = LocalDate.parse(DATE_OF_BIRTH);
        this.lastName = LAST_NAME;
        this.password = PASSWORD;
        this.rating = RATING;
        this.username = USERNAME;
        this.zipCode = ZIP_CODE;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {

        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }

    /*
    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }

     */

    public List<Auction> getAuctionList() {
        return this.auctionList;
    }

    public void setAuctionList(List<Auction> AuctionList) {
        this.auctionList = auctionList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
