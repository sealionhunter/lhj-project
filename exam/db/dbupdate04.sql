grant all privileges on exam.* to 'exam'@'localhost' identified by 'exam_20130221-0316';

use exam;

ALTER TABLE `exam` ADD COLUMN `reApplyStart` DATETIME NULL, ADD COLUMN `reApplyEnd` DATETIME NULL, ADD COLUMN `admissionPrintStart` DATETIME NULL, ADD COLUMN `admissionPrintEnd` DATETIME NULL;
UPDATE `exam` SET 
    `reApplyStart` = '2013/03/07 9:00:00',
    `reApplyEnd` = '2013/03/07 16:00:00',
    `admissionPrintStart` = '2013/03/12 0:00:01',
    `admissionPrintEnd` = '2013/03/14 23:59:59'
WHERE id=1;

DROP TABLE IF EXISTS `admission`;
DROP TABLE IF EXISTS `seat`;
DROP TABLE IF EXISTS `roomOffice`; 
DROP TABLE IF EXISTS `room`; 
CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `code` char(4) NOT NULL,
  `name` varchar(32) NOT NULL,
  `position` varchar(128) NOT NULL,
  `seats` int(11) NOT NULL DEFAULT '0',
  `remainSeats` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ;


CREATE TABLE `roomOffice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomId` int(11) NOT NULL,
  `officeId` int(11) NOT NULL,
  `assignSeats` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `room_of_office_idx` (`officeId`),
  KEY `room_of_office_idx2` (`roomId`),
  CONSTRAINT `FK_room_of_office` FOREIGN KEY (`officeId`) REFERENCES `office` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_room_of_office2` FOREIGN KEY (`roomId`) REFERENCES `room` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;


CREATE TABLE `seat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` char(3) NOT NULL,
  `roomId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId_UNIQUE` (`userId`),
  UNIQUE KEY `seat_room_UNIQUE` (`roomId`,`code`),
  KEY `FK_seat_room_idx` (`roomId`),
  KEY `FK_seat_user_idx` (`userId`),
  CONSTRAINT `FK_seat_room` FOREIGN KEY (`roomId`) REFERENCES `room` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_seat_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


CREATE TABLE `admission` (
  `userId` int(11) NOT NULL,
  `code` char(10) NOT NULL,
  `printFlg` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`userId`),
  KEY `FK_admission_user_idx` (`userId`),
  CONSTRAINT `FK_admission_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

