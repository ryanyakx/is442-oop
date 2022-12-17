-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 07, 2022 at 10:29 AM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `is442t6`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--
DROP SCHEMA IF EXISTS `is442t6` ;

-- -----------------------------------------------------
-- Schema is212g8t6
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `is442t6` DEFAULT CHARACTER SET utf8 ;
USE `is442t6` ;


--
-- Table structure for table `User`
--


DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
  `employee_ID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `contact_Num` int(11) NOT NULL,     
  `password` varchar(60) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `is_admin` tinyint(4) NOT NULL,
  PRIMARY KEY (`employee_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------



--
-- Table structure for table `membership`
--

DROP TABLE IF EXISTS `membership`;
CREATE TABLE IF NOT EXISTS `membership` (
  `membership_ID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `place_of_interest` varchar(50) NOT NULL,
  `max_pass_per_loan` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`membership_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------
--
-- Table structure for table `corporatepass`
--

DROP TABLE IF EXISTS `corporate_pass`;
CREATE TABLE IF NOT EXISTS `corporate_pass` (
  `card_Number` varchar(50) NOT NULL,
  `membership_ID` int(11) NOT NULL,
  `card_Type` varchar(15) NOT NULL,
  `replacement_Fee` decimal(13,2) NOT NULL ,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`card_Number`),
  KEY `corporate_pass_fk` (`membership_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------


-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
CREATE TABLE IF NOT EXISTS `loan` (
  `loan_ID` int(11) NOT NULL,
  `employee_ID` int(11) NOT NULL,
  `card_Number` varchar(50) NOT NULL,
  `date_Creation` datetime NOT NULL,
  `date_Visit` datetime NOT NULL,
  `date_Expected_Return` datetime NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`loan_ID`),
  KEY `employee_loan_fk` (`employee_ID`),
  KEY `employee_cpass_fk` (`card_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------


--
-- Table structure for table `constant`
--


DROP TABLE IF EXISTS `constant`;
CREATE TABLE IF NOT EXISTS `constant`(
	`id` int NOT NULL,
    `max_loan_per_month` int NOT NULL,
    primary key(`id`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- Table structure for table `waitinglist`
--

DROP TABLE IF EXISTS `waiting_list`;
CREATE TABLE IF NOT EXISTS `waiting_list` (
  `waiting_ID` int(11) NOT NULL,
  `employee_ID` int(11) NOT NULL,
  `card_Number` varchar(50) NOT NULL,
  PRIMARY KEY (`waiting_ID`),
  KEY `wait_employee_fk` (`employee_ID`),
  KEY `wait_cpass_fk` (`card_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


ALTER TABLE `loan`
  ADD CONSTRAINT `employee_loan_fk` FOREIGN KEY (`employee_ID`) REFERENCES `User` (`employee_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `employee_cpass_fk` FOREIGN KEY (`card_Number`) REFERENCES `coporate_pass` (`card_Number`) ON DELETE RESTRICT ON UPDATE RESTRICT;
  

ALTER TABLE `waiting_list`
  ADD CONSTRAINT `wait_employee_fk` FOREIGN KEY (`employee_ID`) REFERENCES `User` (`employee_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `wait_cpass_fk` FOREIGN KEY (`card_Number`) REFERENCES `coporate_pass` (`card_Number`) ON DELETE RESTRICT ON UPDATE RESTRICT;
  
ALTER TABLE `corporate_pass`
  ADD CONSTRAINT `corporate_pass_fk` FOREIGN KEY (`membership_ID`) REFERENCES `membership` (`member_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;





