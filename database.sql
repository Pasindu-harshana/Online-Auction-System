CREATE DATABASE IF NOT EXISTS auction;

USE auction;

CREATE TABLE IF NOT EXISTS registation(
UID VARCHAR(7),
name VARCHAR(45) NOT NULL,
password VARCHAR(45) NOT NULL,
mobileNo INT(10) NOT NULL,
PRIMARY KEY(UID)
);

CREATE TABLE IF NOT EXISTS seller(
sellerID VARCHAR(7),
sellername VARCHAR(45) NOT NULL,
sellerpass VARCHAR(45) NOT NULL,
mobileno INT(10) NOT NULL, 
PRIMARY KEY(sellerID)
);

CREATE TABLE IF NOT EXISTS item(
itemno VARCHAR(8),
itemname VARCHAR(45),
itemdiscription VARCHAR(45),
itemprice DOUBLE, 
itemImage MEDIUMBLOB, 
sellerID VARCHAR(7),
PRIMARY KEY(itemno),
FOREIGN KEY(sellerID) REFERENCES seller(sellerID)
);

CREATE TABLE IF NOT EXISTS pacebids(
biddingamount DOUBLE,
userID VARCHAR(7),
ItemID VARCHAR(8),
PRIMARY KEY(biddingamount, userID, ItemID),
FOREIGN KEY(userID) REFERENCES registation(UID),
FOREIGN KEY(sellerID) REFERENCES seller(sellerID)
);
