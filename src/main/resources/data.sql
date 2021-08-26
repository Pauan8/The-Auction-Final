INSERT INTO USERS (ADDRESS, CITY, DATE_OF_BIRTH, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD,
RATING, USERNAME, ZIP_CODE)
VALUES ('Kungsgatan 3', 'Stockholm', '1999-10-10', 'test@test.com', 'Johan', 'Svensson', '123', null, 'Johan',
        '123 12');

INSERT INTO USERS (ADDRESS, CITY, DATE_OF_BIRTH, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD,
RATING, USERNAME, ZIP_CODE)
VALUES ('Kungsgatan 2', 'Stockholm', '1998-10-10', 'test2@test.com', 'Adam', 'Svensson', '123', null, 'Adam', '123 13');

INSERT INTO Auction (DESCRIPTION, END_DATE_TIME, FINISHED, KEY_WORDS, PICTURE_ADDRESS, RESERVATION_PRICE, START_PRICE,
                     TITLE, INVOICE_ID, USERS_ID)
VALUES ('Rosa med ett hål för huvudet', '2021-08-30 10:11:12', 0, ';kläder;', '74827b6a-92b6-4a13-8a62-9491e4db68d1.jpg', 150, 50,
        'Rosa tröja', null, 1);

INSERT INTO Auction (DESCRIPTION, END_DATE_TIME, FINISHED, KEY_WORDS, PICTURE_ADDRESS, RESERVATION_PRICE, START_PRICE,
                     TITLE, INVOICE_ID, USERS_ID)
VALUES ('Blå med ett hål för huvudet', '2021-08-30 10:11:12', 0, ';hemelektronik;kläder;', '74827b6a-92b6-4a13-8a62-9491e4db68d1.jpg', 150, 50, 'Blå tröja',
        null, 1);

INSERT INTO Auction (DESCRIPTION, END_DATE_TIME, FINISHED, KEY_WORDS, PICTURE_ADDRESS, RESERVATION_PRICE, START_PRICE,
                     TITLE, INVOICE_ID, USERS_ID)
VALUES ('Röd med ett hål för huvudet', '2021-08-30 10:11:12', 0, ';hemelektronik;', '74827b6a-92b6-4a13-8a62-9491e4db68d1.jpg', 150, 50, 'Röd tröja',
        null, 1);

INSERT INTO Auction (DESCRIPTION, END_DATE_TIME, FINISHED, KEY_WORDS, PICTURE_ADDRESS, RESERVATION_PRICE, START_PRICE,
                     TITLE, INVOICE_ID, USERS_ID)
VALUES ('Lila med ett hål för huvudet', '2021-08-30 10:11:12', 0, ';kläder;', '74827b6a-92b6-4a13-8a62-9491e4db68d1.jpg', 150, 50,
        'Lila tröja', null, 2);
