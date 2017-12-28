drop database lms;
create database lms;
GRANT ALL PRIVILEGES ON lms.* TO 'mysqluser'@'%' WITH GRANT OPTION;
use lms;
DROP table IF EXISTS Book;
DROP table IF EXISTS StockRecord;

create table Book (
  isbn varchar(20) PRIMARY KEY,
  title varchar(255)
);

create table StockRecord (
  accessionNo varchar(255) PRIMARY KEY,
  isbn varchar(20),
  status varchar(255),
  FOREIGN KEY (isbn) REFERENCES Book(isbn)
);