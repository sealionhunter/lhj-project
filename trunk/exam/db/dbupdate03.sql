use exam;

CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` char(4) NOT NULL,
  `name` varchar(16) NOT NULL,
  `position` varchar(128) NOT NULL,
  `seats` int(11) NOT NULL DEFAULT '0',
  `officeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `room_office_idx` (`officeId`),
  CONSTRAINT `FK_room_office` FOREIGN KEY (`officeId`) REFERENCES `office` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;


CREATE TABLE `seat` (
  `id` int(11) NOT NULL,
  `code` varchar(3) DEFAULT NULL,
  `roomId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_seat_user` (`userId`),
  KEY `FK_seat_room_idx` (`roomId`),
  KEY `FK_seat_user_idx` (`userId`),
  CONSTRAINT `FK_seat_room` FOREIGN KEY (`roomId`) REFERENCES `room` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_seat_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
