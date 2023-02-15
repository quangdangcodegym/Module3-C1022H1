-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: c10_qlykhachhang
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(145) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Dien thoai'),(2,'May Tinh Bang'),(3,'Phone'),(4,'Phone 1');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdat` date DEFAULT NULL,
  `address` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idcustomertype` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idcustomertype_idx` (`idcustomertype`),
  CONSTRAINT `fk_idcustomertype` FOREIGN KEY (`idcustomertype`) REFERENCES `customer_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (5,'Thien Phuc','2023-12-12','8 Hoang Hoa Tham','2.png',1),(7,'Maite Dec','1983-02-27','Maite Dec','2.png',2),(8,'Trevor H','1989-09-21','Ea consesss','2.png',3),(9,'Vielka P','2014-06-15','Vielka P','1.png',2),(10,'Quang12','2023-12-12','28 NTP','aa.png',2);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_type`
--

DROP TABLE IF EXISTS `customer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_type` (
  `id` int NOT NULL,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_type`
--

LOCK TABLES `customer_type` WRITE;
/*!40000 ALTER TABLE `customer_type` DISABLE KEYS */;
INSERT INTO `customer_type` VALUES (1,'VIP'),(2,'SUPPER VIP'),(3,'NORMAL');
/*!40000 ALTER TABLE `customer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(245) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'28 NTP','3456789','Quang',12),(2,'29 NTP','234567','Hoang',11),(3,'29 NTP','1234','qqq',1111),(4,'gubi@mailinator.com','0775882558','Van Qua',0),(5,'Proident voluptatem','+1 (172) 261-6644','Gretchen Romero',0),(7,'\"Eaque voluptate obca\"','\"+1 (264) 291-3549\"','\"Nhan Hoang\"',0),(8,'\"Eaque voluptate obca\"','\"+1 (264) 291-3549\"','\"Tinh Yen\"',0),(9,'\"Eaque voluptate obca\"','\"+1 (264) 291-3549\"','\"Tinh Yen1\"',0),(13,'\"Id laborum ipsum ne\"','\"+1 (532) 667-3555\"','\"Minh Bui\"',0),(14,'\"Qui aliquam nisi aut\"','\"+1 (553) 149-4266\"','\"Jared Estrada\"',4000),(15,'\"Ut ex nostrud id lab\"','\"+1 (318) 939-6315\"','\"Gregory Mccray\"',8000),(16,'\"Ut ex nostrud id lab\"','\"+1 (318) 939-6315\"','\"Thu Thuy\"',8000),(18,'\"Aperiam sed porro to\"','\"+1 (654) 884-4314\"','\"Drew Scott\"',8000),(20,'\"Ut ex nostrud id lab\"','\"+1 (318) 939-6315\"','\"Trong Nghia\"',8000),(21,'\"Temporibus libero cu\"','\"+1 (107) 167-2252\"','\"Thu Le\"',12000),(24,'Excepturi consequunt','+1 (193) 453-3367','Holly Roy',6000),(25,'\"Ut ex nostrud id lab\"','\"+1 (318) 939-6315\"','\"Thu Thuy123\"',8000),(26,'\"Ut ex nostrud id lab\"','\"+1 (318) 939-6315\"','\"Thu Thuy124\"',8000),(29,'\"Ut ex nostrud id lab\"','\"+1 (318) 939-6315\"','\"Thu Thuy125\"',8000),(30,'\"Ut ex nostrud id lab\"','\"+1 (318) 939-6315\"','\"Thu Thuy125\"',8000),(33,'\"23 NTP\"','\"1212345678\"','\"Quang Quang\"',8000);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id_order` bigint NOT NULL,
  `id_product` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id_order`,`id_product`),
  KEY `fk_id_product_idx` (`id_product`),
  CONSTRAINT `fk_id_order` FOREIGN KEY (`id_order`) REFERENCES `order` (`id`),
  CONSTRAINT `fk_id_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (4,2,1),(4,3,1),(4,4,1),(5,3,1),(7,2,2),(7,3,1),(8,2,2),(8,3,1),(9,2,2),(9,3,1),(13,2,3),(13,3,3),(13,4,3),(14,2,1),(14,3,1),(15,2,2),(15,3,2),(16,2,2),(16,3,2),(18,2,2),(18,3,2),(20,2,2),(20,3,2),(21,2,3),(21,3,3),(24,2,2),(25,2,2),(25,3,2),(26,2,2),(26,3,2),(29,2,2),(29,3,2),(30,2,2),(30,3,2),(33,2,2),(33,3,2);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(245) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(245) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_id_idx` (`category_id`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'Iphone 11',2000,'kk','iphone7plus.png','2022-12-12',1),(3,'Iphone 12',2000,'kk','iphone-13.jpg','2022-12-12',1),(4,'Iphone 13',2000,'kk','iphone6.jpg','2022-12-12',1),(5,'Iphone 14',2000,'kk','iphone-13.jpg','2022-12-12',1),(6,'Iphone 11',2000,'kk','iphone7plus.png','2022-12-12',1),(7,'Iphone 12',2000,'kk','iphone-13.jpg','2022-12-12',1),(8,'Iphone 13',2000,'kk','iphone6.jpg','2022-12-12',1),(9,'Iphone 14',2000,'kk','iphone6.jpg','2022-12-12',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(245) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(245) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'quangdang',1,'Quang Dang','123123');
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

-- Dump completed on 2023-02-15 16:27:50
