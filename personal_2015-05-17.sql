# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.24)
# Database: personal
# Generation Time: 2015-05-17 09:45:29 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table emailqueue
# ------------------------------------------------------------

DROP TABLE IF EXISTS `emailqueue`;

CREATE TABLE `emailqueue` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `from_email_address` varchar(255) DEFAULT NULL,
  `to_email_address` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT '',
  `body` text,
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `emailqueue` WRITE;
/*!40000 ALTER TABLE `emailqueue` DISABLE KEYS */;

INSERT INTO `emailqueue` (`id`, `from_email_address`, `to_email_address`, `subject`, `body`, `status`)
VALUES
	(1,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(2,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(3,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(4,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(5,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(6,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(7,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(8,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(9,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(10,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(11,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(12,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(13,'from@abcd.com','to1@abcd.com','hi','hello!!!\n',1),
	(14,'from@abcd.com','to1@abcd.com','hi','hello!\n',1);

/*!40000 ALTER TABLE `emailqueue` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
