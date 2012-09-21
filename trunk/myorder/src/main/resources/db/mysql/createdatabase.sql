
    drop table OrderDetail;
    drop table OrderPay;
    drop table Orders;
    drop table Food;
    drop table DinningTable;
    drop table Hotel;
    drop table UserInfo;
create table DinningTable (uuid varchar(255) not null, area varchar(256) not null, category varchar(256) not null, description varchar(1024) null, hid varchar(256) not null, imgUrl varchar(512) null, maxService int not null, name varchar(256) not null, state varchar(256) not null, primary key (uuid));
create table Food (uuid varchar(255) not null, category varchar(256) null, description varchar(1024) null, hid varchar(256) not null, imgUrl varchar(512) null, name varchar(256) not null, price numeric(13,2) not null, subCategory varchar(256) not null, unit varchar(1024) null, primary key (uuid));
create table Hotel (uuid varchar(255) not null, address varchar(256) null, description varchar(1024) null, foodCategory varchar(255) null, imgUrl varchar(512) null, maxService int null, name varchar(256) not null, serviceFrom varchar(255) null, serviceTo varchar(255) null, telNum varchar(64) null, primary key (uuid));
create table OrderDetail (uuid varchar(255) not null, foodId varchar(256) not null, oId varchar(255) null, unit int null, primary key (uuid));
create table OrderPay (uuid varchar(255) not null, description varchar(1024) null, oId varchar(255) null, oprice numeric(12,2) not null, rprice numeric(12,2) not null, state varchar(255) not null, primary key (uuid));
create table Orders (uuid varchar(255) not null, address varchar(256) not null, category varchar(256) not null, ccount int not null, cid varchar(256) null, cName varchar(256) null, description varchar(1024) null, hid varchar(256) null, oprice numeric(12,2) not null, rprice numeric(12,2) not null, state varchar(256) not null, telNum varchar(64) not null, tid varchar(256) null, primary key (uuid));
create table UserInfo (uid varchar(64) not null unique, address varchar(512) null, birthday datetime null, email varchar(64) null, hid varchar(255) null, isAdmin tinyint null, password varchar(64) not null, sex varchar(2) not null, mobile varchar(16) null, uname varchar(128) not null, validTo varchar(256) not null, primary key (uid));
