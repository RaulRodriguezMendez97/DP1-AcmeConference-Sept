

start transaction;


create database `Acme-Conference`;

use `Acme-Conference`

create user 'acme-user'@'%' 
	identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' 
	identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';


grant select, insert, update, delete 
	on `Acme-Conference`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Conference`.* to 'acme-manager'@'%';



-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Conference
-- ------------------------------------------------------
-- Server version	5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `schedule` datetime DEFAULT NULL,
  `speaker` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `conference` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qy8knnuriq3w5yo8a6plf6vq7` (`conference`),
  CONSTRAINT `FK_qy8knnuriq3w5yo8a6plf6vq7` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_attachments`
--

DROP TABLE IF EXISTS `activity_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_attachments` (
  `activity` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_attachments`
--

LOCK TABLES `activity_attachments` WRITE;
/*!40000 ALTER TABLE `activity_attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_awymvli3olnnumqow6wf060pa` (`email`),
  KEY `FK_i7xei45auwq1f6vu25985riuh` (`user_account`),
  CONSTRAINT `FK_i7xei45auwq1f6vu25985riuh` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jj3mmcc0vjobqibj67dvuwihk` (`email`),
  KEY `FK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (13,0,'SEVILLA','cristian@hotmail.com','','Cristian','+34 620510520','https://i.blogs.es/dcc721/url1/450_1000.jpg','Lorca',12);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_grm3merlhi91rac0mu26swyhf` (`email`),
  KEY `FK_rjptsoy3q9dcfysbnmy1g0g31` (`user_account`),
  CONSTRAINT `FK_rjptsoy3q9dcfysbnmy1g0g31` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `camara_ready`
--

DROP TABLE IF EXISTS `camara_ready`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `camara_ready` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url_document` varchar(255) DEFAULT NULL,
  `author` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2d54bsy0aeasmxv42kb5jra6y` (`author`),
  CONSTRAINT `FK_2d54bsy0aeasmxv42kb5jra6y` FOREIGN KEY (`author`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camara_ready`
--

LOCK TABLES `camara_ready` WRITE;
/*!40000 ALTER TABLE `camara_ready` DISABLE KEYS */;
/*!40000 ALTER TABLE `camara_ready` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference`
--

DROP TABLE IF EXISTS `conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conference` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `acronym` varchar(255) DEFAULT NULL,
  `camera_deadline` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `fee` double NOT NULL,
  `final_mode` int(11) NOT NULL,
  `notificacion_deadline` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `submission_deadline` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `venue` varchar(255) DEFAULT NULL,
  `admin` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5sorv2p110r63tcr5nh0g3l2k` (`admin`),
  CONSTRAINT `FK_5sorv2p110r63tcr5nh0g3l2k` FOREIGN KEY (`admin`) REFERENCES `administrator` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference`
--

LOCK TABLES `conference` WRITE;
/*!40000 ALTER TABLE `conference` DISABLE KEYS */;
/*!40000 ALTER TABLE `conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit_card` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cw` int(11) NOT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) NOT NULL,
  `expiration_year` int(11) NOT NULL,
  `hold_name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `author` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2ncxn32sn2quehl710urqs0on` (`number`),
  KEY `FK_hajbs5u2lm384exthal2yur84` (`author`),
  CONSTRAINT `FK_hajbs5u2lm384exthal2yur84` FOREIGN KEY (`author`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customizable_system`
--

DROP TABLE IF EXISTS `customizable_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customizable_system` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `message_welcome_page` varchar(255) DEFAULT NULL,
  `name_system` varchar(255) DEFAULT NULL,
  `spanish_message_welcome_page` varchar(255) DEFAULT NULL,
  `telephone_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customizable_system`
--

LOCK TABLES `customizable_system` WRITE;
/*!40000 ALTER TABLE `customizable_system` DISABLE KEYS */;
INSERT INTO `customizable_system` VALUES (19,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','Welcome to Acme Conference! Your scientific event manager.','Acme Conference','¡Bienvenidos a Acme Conference! Su gestor de eventos científicos.','+34');
/*!40000 ALTER TABLE `customizable_system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customizable_system_brand_name_credit_card`
--

DROP TABLE IF EXISTS `customizable_system_brand_name_credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customizable_system_brand_name_credit_card` (
  `customizable_system` int(11) NOT NULL,
  `brand_name_credit_card` varchar(255) DEFAULT NULL,
  KEY `FK_rn7ft03w47kudhdtitu2pmo6l` (`customizable_system`),
  CONSTRAINT `FK_rn7ft03w47kudhdtitu2pmo6l` FOREIGN KEY (`customizable_system`) REFERENCES `customizable_system` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customizable_system_brand_name_credit_card`
--

LOCK TABLES `customizable_system_brand_name_credit_card` WRITE;
/*!40000 ALTER TABLE `customizable_system_brand_name_credit_card` DISABLE KEYS */;
INSERT INTO `customizable_system_brand_name_credit_card` VALUES (19,'VISA'),(19,'MASTER'),(19,'VINNERS'),(19,'AMEX');
/*!40000 ALTER TABLE `customizable_system_brand_name_credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `email_receiver` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `receiver` int(11) DEFAULT NULL,
  `sender` int(11) NOT NULL,
  `topic` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9e5snvu7d6i2dypiwctuvm8fs` (`topic`),
  CONSTRAINT `FK_9e5snvu7d6i2dypiwctuvm8fs` FOREIGN KEY (`topic`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_box`
--

DROP TABLE IF EXISTS `message_box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_box` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hulx6jong3y07iw8yjkqwnhsq` (`actor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_box`
--

LOCK TABLES `message_box` WRITE;
/*!40000 ALTER TABLE `message_box` DISABLE KEYS */;
INSERT INTO `message_box` VALUES (20,0,13);
/*!40000 ALTER TABLE `message_box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_box_messages`
--

DROP TABLE IF EXISTS `message_box_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_box_messages` (
  `message_box` int(11) NOT NULL,
  `messages` int(11) NOT NULL,
  KEY `FK_4ydibw5107qpqjwmw37t3cx5c` (`messages`),
  KEY `FK_i9fsy1u5e0dyn977c4uhdupur` (`message_box`),
  CONSTRAINT `FK_i9fsy1u5e0dyn977c4uhdupur` FOREIGN KEY (`message_box`) REFERENCES `message_box` (`id`),
  CONSTRAINT `FK_4ydibw5107qpqjwmw37t3cx5c` FOREIGN KEY (`messages`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_box_messages`
--

LOCK TABLES `message_box_messages` WRITE;
/*!40000 ALTER TABLE `message_box_messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_box_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `panel`
--

DROP TABLE IF EXISTS `panel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `panel` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `schedule` datetime DEFAULT NULL,
  `speaker` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `conference` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g9jn8oaylcvixfdbmtesav3c3` (`conference`),
  CONSTRAINT `FK_g9jn8oaylcvixfdbmtesav3c3` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `panel`
--

LOCK TABLES `panel` WRITE;
/*!40000 ALTER TABLE `panel` DISABLE KEYS */;
/*!40000 ALTER TABLE `panel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url_document` varchar(255) DEFAULT NULL,
  `author` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_nrdreyrakpww3o68im6ox46w5` (`author`),
  CONSTRAINT `FK_nrdreyrakpww3o68im6ox46w5` FOREIGN KEY (`author`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper_co_authors`
--

DROP TABLE IF EXISTS `paper_co_authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper_co_authors` (
  `paper` int(11) NOT NULL,
  `co_authors` int(11) NOT NULL,
  KEY `FK_gf7rqf63610e0cl2uryk50eqi` (`co_authors`),
  CONSTRAINT `FK_gf7rqf63610e0cl2uryk50eqi` FOREIGN KEY (`co_authors`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper_co_authors`
--

LOCK TABLES `paper_co_authors` WRITE;
/*!40000 ALTER TABLE `paper_co_authors` DISABLE KEYS */;
/*!40000 ALTER TABLE `paper_co_authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `url_picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presentation`
--

DROP TABLE IF EXISTS `presentation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presentation` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `schedule` datetime DEFAULT NULL,
  `speaker` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `conference` int(11) NOT NULL,
  `camara_ready` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_98e1uasrledlxwbq0bfytpe20` (`camara_ready`),
  KEY `FK_fu56gussuaw8r8865h3x4cvq0` (`conference`),
  CONSTRAINT `FK_fu56gussuaw8r8865h3x4cvq0` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_98e1uasrledlxwbq0bfytpe20` FOREIGN KEY (`camara_ready`) REFERENCES `camara_ready` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presentation`
--

LOCK TABLES `presentation` WRITE;
/*!40000 ALTER TABLE `presentation` DISABLE KEYS */;
/*!40000 ALTER TABLE `presentation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `conference` int(11) NOT NULL,
  `credit_card` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lc3odbpd5lgo7qc3w33ugwafj` (`conference`),
  KEY `FK_o81sqbmy1blyga0cbtlhbho4d` (`credit_card`),
  CONSTRAINT `FK_o81sqbmy1blyga0cbtlhbho4d` FOREIGN KEY (`credit_card`) REFERENCES `credit_card` (`id`),
  CONSTRAINT `FK_lc3odbpd5lgo7qc3w33ugwafj` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `decision` int(11) NOT NULL,
  `eadability_score` int(11) NOT NULL,
  `originality_score` int(11) NOT NULL,
  `quality_score` int(11) NOT NULL,
  `reviwer` int(11) NOT NULL,
  `submission` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o46pjxb44f0uq38kymgumvyl3` (`reviwer`),
  KEY `FK_a0lt5jqh9b7s1gw3q77nywxxn` (`submission`),
  CONSTRAINT `FK_a0lt5jqh9b7s1gw3q77nywxxn` FOREIGN KEY (`submission`) REFERENCES `submission` (`id`),
  CONSTRAINT `FK_o46pjxb44f0uq38kymgumvyl3` FOREIGN KEY (`reviwer`) REFERENCES `reviwer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviwed`
--

DROP TABLE IF EXISTS `reviwed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviwed` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url_document` varchar(255) DEFAULT NULL,
  `author` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rwrxh67dpparp2qyeb8lqgetw` (`author`),
  CONSTRAINT `FK_rwrxh67dpparp2qyeb8lqgetw` FOREIGN KEY (`author`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviwed`
--

LOCK TABLES `reviwed` WRITE;
/*!40000 ALTER TABLE `reviwed` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviwed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviwer`
--

DROP TABLE IF EXISTS `reviwer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviwer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r2c37ombg6ddklg5rr2rgdw32` (`email`),
  KEY `FK_cj6d2wupijpletb94ketipu1u` (`user_account`),
  CONSTRAINT `FK_cj6d2wupijpletb94ketipu1u` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviwer`
--

LOCK TABLES `reviwer` WRITE;
/*!40000 ALTER TABLE `reviwer` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviwer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviwer_key_words`
--

DROP TABLE IF EXISTS `reviwer_key_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviwer_key_words` (
  `reviwer` int(11) NOT NULL,
  `key_words` varchar(255) DEFAULT NULL,
  KEY `FK_1ywiuouhc53ej620oyua564gg` (`reviwer`),
  CONSTRAINT `FK_1ywiuouhc53ej620oyua564gg` FOREIGN KEY (`reviwer`) REFERENCES `reviwer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviwer_key_words`
--

LOCK TABLES `reviwer_key_words` WRITE;
/*!40000 ALTER TABLE `reviwer_key_words` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviwer_key_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `tutorial` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6tcpk427cxiaiglbxvybc9fjh` (`tutorial`),
  CONSTRAINT `FK_6tcpk427cxiaiglbxvybc9fjh` FOREIGN KEY (`tutorial`) REFERENCES `tutorial` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section_pictures`
--

DROP TABLE IF EXISTS `section_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section_pictures` (
  `section` int(11) NOT NULL,
  `pictures` int(11) NOT NULL,
  UNIQUE KEY `UK_bccvc8p84f0nih86o3w1lv7lq` (`pictures`),
  KEY `FK_fpgvw49vb6tytfbfcghj3o8sv` (`section`),
  CONSTRAINT `FK_fpgvw49vb6tytfbfcghj3o8sv` FOREIGN KEY (`section`) REFERENCES `section` (`id`),
  CONSTRAINT `FK_bccvc8p84f0nih86o3w1lv7lq` FOREIGN KEY (`pictures`) REFERENCES `picture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section_pictures`
--

LOCK TABLES `section_pictures` WRITE;
/*!40000 ALTER TABLE `section_pictures` DISABLE KEYS */;
/*!40000 ALTER TABLE `section_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission`
--

DROP TABLE IF EXISTS `submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `status` int(11) NOT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `author` int(11) NOT NULL,
  `camara_ready` int(11) DEFAULT NULL,
  `conference` int(11) NOT NULL,
  `reviwed` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_md8gax3in9o8v1xt7kk9gssa` (`reviwed`),
  UNIQUE KEY `UK_9ayhftkow8judgm0cblwdb9mi` (`ticker`),
  KEY `FK_ssk77t9sokwi9utdru9hvodul` (`author`),
  KEY `FK_fpnmgh4nqe5pgqax8kc4mk0sp` (`camara_ready`),
  KEY `FK_1vynnfw6cw1l40c8e342st672` (`conference`),
  CONSTRAINT `FK_md8gax3in9o8v1xt7kk9gssa` FOREIGN KEY (`reviwed`) REFERENCES `reviwed` (`id`),
  CONSTRAINT `FK_1vynnfw6cw1l40c8e342st672` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_fpnmgh4nqe5pgqax8kc4mk0sp` FOREIGN KEY (`camara_ready`) REFERENCES `camara_ready` (`id`),
  CONSTRAINT `FK_ssk77t9sokwi9utdru9hvodul` FOREIGN KEY (`author`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission`
--

LOCK TABLES `submission` WRITE;
/*!40000 ALTER TABLE `submission` DISABLE KEYS */;
/*!40000 ALTER TABLE `submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission_reviwers`
--

DROP TABLE IF EXISTS `submission_reviwers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission_reviwers` (
  `submission` int(11) NOT NULL,
  `reviwers` int(11) NOT NULL,
  KEY `FK_f14w40qa6hd4lha7x5vfn8ffj` (`reviwers`),
  KEY `FK_8ocna6l4wftmqy6k44v4we8m0` (`submission`),
  CONSTRAINT `FK_8ocna6l4wftmqy6k44v4we8m0` FOREIGN KEY (`submission`) REFERENCES `submission` (`id`),
  CONSTRAINT `FK_f14w40qa6hd4lha7x5vfn8ffj` FOREIGN KEY (`reviwers`) REFERENCES `reviwer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission_reviwers`
--

LOCK TABLES `submission_reviwers` WRITE;
/*!40000 ALTER TABLE `submission_reviwers` DISABLE KEYS */;
/*!40000 ALTER TABLE `submission_reviwers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `spanish_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (14,0,'REGISTRATION','REGISTRO'),(15,0,'INQUIRY','INVESTIGACION'),(16,0,'REBUTTAL','REFUTACION'),(17,0,'TOPICS','TEMAS'),(18,0,'OTHER','OTROS');
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutorial`
--

DROP TABLE IF EXISTS `tutorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutorial` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `schedule` datetime DEFAULT NULL,
  `speaker` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `conference` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gd8wor872gtoqt1siaw2k4din` (`conference`),
  CONSTRAINT `FK_gd8wor872gtoqt1siaw2k4din` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutorial`
--

LOCK TABLES `tutorial` WRITE;
/*!40000 ALTER TABLE `tutorial` DISABLE KEYS */;
/*!40000 ALTER TABLE `tutorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (12,0,'21232f297a57a5a743894a0e4a801fc3','admin');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (12,'ADMIN');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 18:33:37
