-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: labsd2
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `user` int DEFAULT NULL,
  `restaurant` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `usera_fk_idx` (`user`),
  KEY `admin_restaurant_id_fk` (`restaurant`),
  CONSTRAINT `admin_restaurant_id_fk` FOREIGN KEY (`restaurant`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `usera_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin1',19,22),(2,'admin2',35,24),(3,'admin3',72,25),(4,'admin4',74,80),(5,'admin5',76,NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `menu` int DEFAULT NULL,
  `orderr` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `menu_fk_idx` (`menu`),
  KEY `order_fk_idx` (`orderr`),
  CONSTRAINT `menu_fk` FOREIGN KEY (`menu`) REFERENCES `menu` (`id`),
  CONSTRAINT `order_fk` FOREIGN KEY (`orderr`) REFERENCES `orderr` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (65,49,64,1),(66,53,64,2),(68,49,67,1),(69,53,67,2),(71,49,70,1),(84,82,83,1),(86,82,85,6),(88,82,87,3),(92,82,91,1),(93,90,91,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `user` int DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `userc_fk_idx` (`user`),
  CONSTRAINT `userc_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (2,'aa','aaa',1,21),(4,'bbb','bbb',3,19),(9,'bb','bb',8,18),(12,'bbc','bbc',11,18),(15,'bbccc','bbccc',14,18),(18,'ddd','ddd',17,22);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` enum('BREAKFAST','LUNCH','SOUP','DESSERT') DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'SOUP','supa de idk',NULL,5),(2,'LUNCH','cartofi',NULL,3),(48,'SOUP','aa','aa',5),(50,'BREAKFAST','EGS','egs',3),(52,'DESSERT','pie','apple pie',4),(54,'DESSERT','icecream','ice',7),(56,'DESSERT','icecream','ice',7),(58,'BREAKFAST','baconnnn','prok',3),(81,'LUNCH','pizza','blat',33),(89,'DESSERT','cake','cakee',25);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (94);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `restaurant` int DEFAULT NULL,
  `food` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `restaurant_fk_idx` (`restaurant`),
  KEY `food_fk_idx` (`food`),
  CONSTRAINT `food_fk` FOREIGN KEY (`food`) REFERENCES `food` (`id`),
  CONSTRAINT `restaurant_fk` FOREIGN KEY (`restaurant`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (49,22,48),(51,24,50),(53,22,52),(54,24,1),(55,24,2),(57,22,56),(59,22,58),(82,80,81),(90,80,89);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderr`
--

DROP TABLE IF EXISTS `orderr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderr` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` enum('PENDING','ACCEPTED','IN_DELIVERY','DELIVERED','DECLINED') DEFAULT NULL,
  `customer` int DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `restaurant` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `order_customer_id_fk` (`customer`),
  KEY `orderr_restaurant_id_fk` (`restaurant`),
  CONSTRAINT `order_customer_id_fk` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `orderr_restaurant_id_fk` FOREIGN KEY (`restaurant`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderr`
--

LOCK TABLES `orderr` WRITE;
/*!40000 ALTER TABLE `orderr` DISABLE KEYS */;
INSERT INTO `orderr` VALUES (64,'DECLINED',2,NULL,NULL,22),(67,'DECLINED',2,'11-04-2022',13,22),(70,'DELIVERED',2,'12-04-2022',5,22),(83,'DELIVERED',4,'12-04-2022',33,80),(85,'DECLINED',9,'12-04-2022',198,80),(87,'DELIVERED',9,'12-04-2022',99,80),(91,'DELIVERED',9,'12-04-2022',58,80);
/*!40000 ALTER TABLE `orderr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `deliveryzone` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (22,'aa','aa','aa'),(24,'bb','bb','bb'),(25,'cc','cc','cc'),(80,'restaurant4','bm','hv');
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `type` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'aaa','$s0$e0801$0YoV7eI4bkVAZYtfLaSflg==$ekC0QuWs2djMdTPtV9qUsr+FNcUumRnFSlH74DHHrpU=',0),(3,'bbb','$s0$e0801$KSeTJxWqp7jWzM4/o+NfwA==$dvVJMAWpeMZ0mKpx/ZDONRrhoziQIgkEj1jsMLd5lYE=',0),(8,'bb','$s0$e0801$TgfcZ6TPJCn+C/GH9NqWHg==$qkw4Eh/VijBJadwq7riD2ETYoNXLx9O5EH9rD66EpNc=',0),(11,'bbc','$s0$e0801$BcQB2LjOniraoN/KfLRf1A==$L5PzwE1pWwNLZYYGLEmijrMmYSajClGCpi6/rmm4dGU=',0),(14,'bbccc','$s0$e0801$Us08z+kL1xOaTRthEYwReA==$taAbDrxWQ6t0z6PnAAQgCgnZAkan+x4YuG0N+dW1Kcw=',0),(17,'ddd','$s0$e0801$DWwgJIu63t/xu8mggKibfA==$5sN7E4FDuunOLvjhf1jUg6xiwLeYeNfAmTz2ACnsdAQ=',0),(19,'admin1','$s0$e0801$3SA/CrM+hWCzvimp8Q8Ywg==$EgPG+hKj+7x3aKA91u8AEAXYh/MqwALBIAuFsssbjJA=',1),(35,'admin2','$s0$e0801$D8Bs0FXhwujpJ62jvKr+Sg==$P4OS8TVwkCZGHPo0EW4m+X7HLHqgwXU3V0YKkXTYzSE=',1),(72,'admin3','$s0$e0801$d72vxggSF/TT+VLZE5CPTQ==$ut7gAtmcZZBpRyPw7ZzmnuJVAqBnQGoXmRfSTStcpks=',1),(74,'admin4','$s0$e0801$kPO1jNAHxw1qL9F1LEO/vg==$LpmSESLlysXcMXq0RMqWpH5wd3zWlZ+IwArH/N1c/tM=',1),(76,'admin5','$s0$e0801$NWvtOSaxJ0okE2H9FyJmOA==$NDPJ3x+VWQFKTskiTCthcFmxU1Fg/CD33MAUHSsyfJE=',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-12 21:52:40
