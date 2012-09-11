if exists (select * from sys.objects where name = 'OrderDetail' and type = 'u') 
    drop table OrderDetail;
if exists (select * from sys.objects where name = 'OrderPay' and type = 'u') 
    drop table OrderPay;
if exists (select * from sys.objects where name = 'Orders' and type = 'u') 
    drop table Orders;
if exists (select * from sys.objects where name = 'Food' and type = 'u') 
    drop table Food;
if exists (select * from sys.objects where name = 'DinningTable' and type = 'u') 
    drop table DinningTable;
if exists (select * from sys.objects where name = 'Hotel' and type = 'u') 
    drop table Hotel;
if exists (select * from sys.objects where name = 'UserInfo' and type = 'u') 
    drop table UserInfo;

create table DinningTable (uuid nvarchar(255) not null, area nvarchar(256) not null, category nvarchar(256) not null, description nvarchar(1024) null, hid nvarchar(256) not null, imgUrl nvarchar(512) null, maxService int not null, name nvarchar(256) not null, state nvarchar(256) not null, primary key (uuid));
create table Food (uuid nvarchar(255) not null, category nvarchar(256) null, description nvarchar(1024) null, hid nvarchar(256) not null, imgUrl nvarchar(512) null, name nvarchar(256) not null, price numeric(13,2) not null, subCategory nvarchar(256) not null, unit nvarchar(1024) null, primary key (uuid));
create table Hotel (uuid nvarchar(255) not null, address nvarchar(256) not null, description nvarchar(1024) null, foodCategory nvarchar(255) null, imgUrl nvarchar(512) null, maxService int not null, name nvarchar(256) not null, serviceFrom nvarchar(255) null, serviceTo nvarchar(255) null, telNum nvarchar(64) not null, primary key (uuid));
create table OrderDetail (uuid nvarchar(255) not null, foodId nvarchar(256) not null, oId nvarchar(255) null, unit int null, primary key (uuid));
create table OrderPay (uuid nvarchar(255) not null, description nvarchar(1024) null, oId nvarchar(255) null, oprice numeric(12,2) not null, rprice numeric(12,2) not null, state nvarchar(255) not null, primary key (uuid));
create table Orders (uuid nvarchar(255) not null, address nvarchar(256) not null, category nvarchar(256) not null, ccount int not null, cid nvarchar(256) null, cName nvarchar(256) null, description nvarchar(1024) null, hid nvarchar(256) null, oprice numeric(12,2) not null, rprice numeric(12,2) not null, state nvarchar(256) not null, telNum nvarchar(64) not null, tid nvarchar(256) null, primary key (uuid));
create table UserInfo (uid nvarchar(64) not null unique, address nvarchar(512) null, birthday datetime2 null, email nvarchar(64) null, hid nvarchar(255) null, isAdmin tinyint null, password nvarchar(64) not null, sex nvarchar(2) not null, mobile nvarchar(16) null, uname nvarchar(128) not null, validTo datetime2 null, primary key (uid));
