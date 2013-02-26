use exam;

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `administratorId` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ;

INSERT INTO `exam`.`admin`(`administratorId`, `password`) VALUES ('administrator', 'examadmin');