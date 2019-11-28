-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              10.4.8-MariaDB - mariadb.org binary distribution
-- S.O. server:                  Win64
-- HeidiSQL Versione:            10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dump della struttura del database smartdays_db
CREATE DATABASE IF NOT EXISTS `smartdays_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `smartdays_db`;

-- Dump della struttura di tabella smartdays_db.data
CREATE TABLE IF NOT EXISTS `data` (
  `user` varchar(255) NOT NULL,
  `kmsSavedADay` double DEFAULT NULL,
  `gramsOfCO2SavedADay` double DEFAULT NULL,
  `kmsSavedAYear` double DEFAULT NULL,
  `gramsOfCO2SavedAYear` double DEFAULT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dump dei dati della tabella smartdays_db.data: ~3 rows (circa)
DELETE FROM `data`;
/*!40000 ALTER TABLE `data` DISABLE KEYS */;
INSERT INTO `data` (`user`, `kmsSavedADay`, `gramsOfCO2SavedADay`, `kmsSavedAYear`, `gramsOfCO2SavedAYear`) VALUES
	('CR7MI', 48, 3600, 240, 18000),
	('CS5MI', 13.5, 1863, 445.5, 61479),
	('PAO1MI', 16, 1904, 320, 38080);
/*!40000 ALTER TABLE `data` ENABLE KEYS */;

-- Dump della struttura di tabella smartdays_db.users
CREATE TABLE IF NOT EXISTS `users` (
  `user` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `divisionOrFunction` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `fuelType` varchar(255) DEFAULT NULL,
  `gramsOfCO2` double DEFAULT NULL,
  `smartDays` int(11) DEFAULT NULL,
  `kmsSaved` double DEFAULT NULL,
  `timeSaved` int(11) DEFAULT NULL,
  `consent` int(11) DEFAULT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dump dei dati della tabella smartdays_db.users: ~3 rows (circa)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user`, `name`, `surname`, `email`, `divisionOrFunction`, `location`, `fuelType`, `gramsOfCO2`, `smartDays`, `kmsSaved`, `timeSaved`, `consent`) VALUES
	('CR7MI', 'CRISTIANO', 'RONALDO', 'cristiano.ronaldo@bosch.com', 'Quality', 'Milano', 'Electric - Gasoline', 75, 5, 48, 65, 1),
	('CS5MI', 'SIMONE', 'COSTENARO', 'simone.costenaro@it.bosch.com', 'AA', 'Torino', 'Gasoline', 138, 33, 13.5, 35, 1),
	('PAO1MI', 'OMAR', 'PANEBIANCO', 'fixed-term.omar.panebianco@it.bosch.com', 'AA', 'Torino', 'Diesel', 119, 20, 16, 50, 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
