/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE IF NOT EXISTS `smartdays_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `smartdays_db`;

CREATE TABLE IF NOT EXISTS `data` (
  `user` varchar(255) NOT NULL,
  `kmsSavedADay` double DEFAULT NULL,
  `gramsOfCO2SavedADay` double DEFAULT NULL,
  `kmsSavedAYear` double DEFAULT NULL,
  `gramsOfCO2SavedAYear` double DEFAULT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DELETE FROM `data`;
/*!40000 ALTER TABLE `data` DISABLE KEYS */;
INSERT INTO `data` (`user`, `kmsSavedADay`, `gramsOfCO2SavedADay`, `kmsSavedAYear`, `gramsOfCO2SavedAYear`) VALUES
	('EDF9MI', 27, 3321, 945, 116235),
	('PAO1MI', 17, 2023, 340, 40460);
/*!40000 ALTER TABLE `data` ENABLE KEYS */;

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

DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user`, `name`, `surname`, `email`, `divisionOrFunction`, `location`, `fuelType`, `gramsOfCO2`, `smartDays`, `kmsSaved`, `timeSaved`, `consent`) VALUES
	('EDF9MI', 'ELISA', 'DELLAFERRERA', 'elisa.dellaferrera@it.bosch.com', 'AA', 'Torino', 'Diesel', 123, 35, 27, 35, 1),
	('PAO1MI', 'OMAR', 'PANEBIANCO', 'fixed-term.omar.panebianco@it.bosch.com', 'AA', 'Torino', 'Diesel', 119, 20, 17, 50, 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
