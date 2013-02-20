drop database IF EXISTS exam;
create database exam default character set UTF8;
use exam;

CREATE TABLE `apply` (
  `userid` int(11) NOT NULL,
  `officeid` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `reason` varchar(512) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `udpateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`userid`,`officeid`)
);


CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
);



CREATE TABLE `depart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  `cityId` int(11) DEFAULT NULL,
  `departcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `depart_cite` (`cityId`),
  CONSTRAINT `depart_cite` FOREIGN KEY (`cityId`) REFERENCES `city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


CREATE TABLE `master` (
  `category` int(11) NOT NULL,
  `code` varchar(5) NOT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`category`,`code`)
);

CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `applyBeginDate` datetime DEFAULT NULL,
  `applyDeadDate` datetime DEFAULT NULL,
  `examDate` date NOT NULL,
  `examTime` varchar(16) NOT NULL,
  `examPosition` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `office` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `code` varchar(4) NOT NULL,
  `major` varchar(32) DEFAULT NULL,
  `degree` varchar(16) DEFAULT NULL,
  `recruits` int(11) NOT NULL DEFAULT '0',
  `limitAge` int(11) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `departId` int(11) NOT NULL,
  `examId` int(11) DEFAULT NULL,
  `applyYear` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dept_office_idx` (`departId`),
  CONSTRAINT `dept_office` FOREIGN KEY (`departId`) REFERENCES `depart` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);



CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `idCardNo` varchar(45) NOT NULL,
  `password` varchar(128) NOT NULL,
  `sex` int(11) DEFAULT NULL,
  `nationCode` int(11) DEFAULT NULL,
  `homeTown` varchar(128) DEFAULT NULL,
  `birthdayYear` int(11) NOT NULL,
  `birthdayMonth` int(11) NOT NULL,
  `politicalCode` int(11) DEFAULT NULL,
  `Married` int(11) DEFAULT NULL,
  `degree` varchar(32) DEFAULT NULL,
  `graduateSchool` varchar(128) DEFAULT NULL,
  `major` varchar(32) DEFAULT NULL,
  `computerSkill` varchar(128) DEFAULT NULL,
  `languageSkill` varchar(512) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `graduateYear` int(11) DEFAULT NULL,
  `graduateMonth` int(11) DEFAULT NULL,
  `workyears` int(11) DEFAULT NULL,
  `identity` varchar(32) DEFAULT NULL,
  `trainingExp` varchar(512) DEFAULT NULL,
  `workExp` varchar(512) DEFAULT NULL,
  `socialRel` varchar(512) DEFAULT NULL,
  `photo` longblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idCardNo_UNIQUE` (`idCardNo`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ;

