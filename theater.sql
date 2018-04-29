-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.11-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for theater
DROP DATABASE IF EXISTS `theater`;
CREATE DATABASE IF NOT EXISTS `theater` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `theater`;

-- Dumping structure for table theater.airdates
DROP TABLE IF EXISTS `airdates`;
CREATE TABLE IF NOT EXISTS `airdates` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_airdate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table theater.airdates: ~3 rows (approximately)
DELETE FROM `airdates`;
/*!40000 ALTER TABLE `airdates` DISABLE KEYS */;
INSERT INTO `airdates` (`a_id`, `a_airdate`) VALUES
	(1, '2018-06-06 10:00:00'),
	(2, '2018-06-10 10:00:00'),
	(3, '2018-06-11 10:00:00');
/*!40000 ALTER TABLE `airdates` ENABLE KEYS */;

-- Dumping structure for table theater.events
DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `e_id` int(11) NOT NULL AUTO_INCREMENT,
  `e_name` varchar(100) DEFAULT NULL,
  `e_base_price` decimal(10,0) DEFAULT NULL,
  `e_event_rating` enum('LOW','MID','HIGH') DEFAULT NULL,
  `e_accessed_by_name` bigint(20) DEFAULT '0',
  `e_prices_queried` bigint(20) DEFAULT '0',
  `e_ticket_was_bought` bigint(20) DEFAULT '0',
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table theater.events: ~3 rows (approximately)
DELETE FROM `events`;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` (`e_id`, `e_name`, `e_base_price`, `e_event_rating`, `e_accessed_by_name`, `e_prices_queried`, `e_ticket_was_bought`) VALUES
	(1, 'event1name', 101, 'HIGH', 0, 0, 0),
	(2, 'event2name', 100, 'LOW', 0, 0, 0),
	(3, 'event3name', 100, 'HIGH', 0, 0, 0);
/*!40000 ALTER TABLE `events` ENABLE KEYS */;

-- Dumping structure for table theater.events_has_airdates
DROP TABLE IF EXISTS `events_has_airdates`;
CREATE TABLE IF NOT EXISTS `events_has_airdates` (
  `events_e_id` int(11) NOT NULL,
  `airdates_a_id` int(11) NOT NULL,
  PRIMARY KEY (`events_e_id`,`airdates_a_id`),
  KEY `fk_events_has_airdates_airdates1_idx` (`airdates_a_id`),
  KEY `fk_events_has_airdates_events_idx` (`events_e_id`),
  CONSTRAINT `fk_events_has_airdates_airdates1` FOREIGN KEY (`airdates_a_id`) REFERENCES `airdates` (`a_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_events_has_airdates_events` FOREIGN KEY (`events_e_id`) REFERENCES `events` (`e_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table theater.events_has_airdates: ~4 rows (approximately)
DELETE FROM `events_has_airdates`;
/*!40000 ALTER TABLE `events_has_airdates` DISABLE KEYS */;
INSERT INTO `events_has_airdates` (`events_e_id`, `airdates_a_id`) VALUES
	(1, 1),
	(1, 2),
	(2, 2),
	(3, 3);
/*!40000 ALTER TABLE `events_has_airdates` ENABLE KEYS */;

-- Dumping structure for table theater.tickets
DROP TABLE IF EXISTS `tickets`;
CREATE TABLE IF NOT EXISTS `tickets` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_seat` bigint(20) DEFAULT NULL,
  `events_e_id` int(11) NOT NULL,
  `users_u_id` int(11) DEFAULT NULL,
  `airdates_a_id` int(11) NOT NULL,
  PRIMARY KEY (`t_id`),
  KEY `fk_tickets_events1_idx` (`events_e_id`),
  KEY `fk_tickets_users1_idx` (`users_u_id`),
  KEY `fk_tickets_airdates1_idx` (`airdates_a_id`),
  CONSTRAINT `fk_tickets_airdates1` FOREIGN KEY (`airdates_a_id`) REFERENCES `airdates` (`a_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_events1` FOREIGN KEY (`events_e_id`) REFERENCES `events` (`e_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_users1` FOREIGN KEY (`users_u_id`) REFERENCES `users` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table theater.tickets: ~10 rows (approximately)
DELETE FROM `tickets`;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` (`t_id`, `t_seat`, `events_e_id`, `users_u_id`, `airdates_a_id`) VALUES
	(1, 1, 1, NULL, 1),
	(2, 2, 1, NULL, 1),
	(3, 3, 1, NULL, 1),
	(4, 1, 2, NULL, 2),
	(5, 2, 2, NULL, 2),
	(6, 3, 2, NULL, 2),
	(7, 1, 3, NULL, 3),
	(8, 2, 3, NULL, 3),
	(9, 3, 3, NULL, 3),
	(10, 4, 3, NULL, 1);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;

-- Dumping structure for table theater.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_firstname` varchar(45) DEFAULT NULL,
  `u_password` varchar(64) DEFAULT NULL,
  `u_lastname` varchar(45) DEFAULT NULL,
  `u_email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- Dumping data for table theater.users: ~6 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`u_id`, `u_firstname`, `u_password`, `u_lastname`, `u_email`) VALUES
	(1, 'user1firstName', 'user1password', 'user1lastName', 'user1email'),
	(2, 'user2firstName', 'user2password', 'user2lastName', 'user2email'),
	(3, 'user3firstName', 'user3password', 'user3lastName', 'user3email'),
	(4, 'user4firstName', 'user4password', 'user4lastName', 'user4email'),
	(5, 'user5firstName', 'user5password', 'user5lastName', 'user5email'),
	(6, 'user6firstName', 'user6password', 'user6lastName', 'user6email');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
