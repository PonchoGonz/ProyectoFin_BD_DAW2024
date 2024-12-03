CREATE DATABASE  IF NOT EXISTS `UBA_agencia_artistas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `UBA_agencia_artistas`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: UBA_agencia_artistas
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `EXHIBICION`
--

DROP TABLE IF EXISTS `EXHIBICION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EXHIBICION` (
  `EXP_ID` int NOT NULL,
  `PINT_ID` int NOT NULL,
  PRIMARY KEY (`EXP_ID`,`PINT_ID`),
  KEY `PINT_ID` (`PINT_ID`),
  CONSTRAINT `EXHIBICION_ibfk_1` FOREIGN KEY (`EXP_ID`) REFERENCES `EXPOSICION` (`EXP_ID`),
  CONSTRAINT `EXHIBICION_ibfk_2` FOREIGN KEY (`PINT_ID`) REFERENCES `PINTURA` (`PINT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXHIBICION`
--

LOCK TABLES `EXHIBICION` WRITE;
/*!40000 ALTER TABLE `EXHIBICION` DISABLE KEYS */;
INSERT INTO `EXHIBICION` VALUES (1,1);
/*!40000 ALTER TABLE `EXHIBICION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EXPOSICION`
--

DROP TABLE IF EXISTS `EXPOSICION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EXPOSICION` (
  `EXP_ID` int NOT NULL AUTO_INCREMENT,
  `EXP_NOMBRE` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `EXP_FECHA_INI` date DEFAULT NULL,
  `EXP_FECHA_FIN` date DEFAULT NULL,
  `GAL_ID` int DEFAULT NULL,
  PRIMARY KEY (`EXP_ID`),
  KEY `GAL_ID` (`GAL_ID`),
  CONSTRAINT `EXPOSICION_ibfk_1` FOREIGN KEY (`GAL_ID`) REFERENCES `GALERIA` (`GAL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXPOSICION`
--

LOCK TABLES `EXPOSICION` WRITE;
/*!40000 ALTER TABLE `EXPOSICION` DISABLE KEYS */;
INSERT INTO `EXPOSICION` VALUES (1,'Exposición de Arte Contemporáneo','2023-01-01','2023-02-01',1);
/*!40000 ALTER TABLE `EXPOSICION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GALERIA`
--

DROP TABLE IF EXISTS `GALERIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GALERIA` (
  `GAL_ID` int NOT NULL AUTO_INCREMENT,
  `GAL_NOMBRE` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `GAL_DIRECCION` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `GAL_TEL` varchar(15) COLLATE utf8mb4_bin DEFAULT NULL,
  `GAL_EMAIL` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `GAL_WEB` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`GAL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GALERIA`
--

LOCK TABLES `GALERIA` WRITE;
/*!40000 ALTER TABLE `GALERIA` DISABLE KEYS */;
INSERT INTO `GALERIA` VALUES (1,'Galería de Arte Moderno','123 Calle Falsa, Ciudad','987654321','contacto@galeria.com','www.galeria.com');
/*!40000 ALTER TABLE `GALERIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PINTOR`
--

DROP TABLE IF EXISTS `PINTOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PINTOR` (
  `PIN_ID` int NOT NULL AUTO_INCREMENT,
  `PIN_NOMBRE` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `PIN_CIUDAD` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `PIN_FECHA_NAC` date DEFAULT NULL,
  `PIN_TEL` varchar(15) COLLATE utf8mb4_bin DEFAULT NULL,
  `PIN_EMAIL` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`PIN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PINTOR`
--

LOCK TABLES `PINTOR` WRITE;
/*!40000 ALTER TABLE `PINTOR` DISABLE KEYS */;
INSERT INTO `PINTOR` VALUES (1,'Pablo Picasso','Málaga','1881-10-25','123456789','picasso@art.com'),(2,'Vincent van Gogh','Zundert','1853-03-30','987654321','vangogh@art.com'),(3,'Frida Kahlo','Coyoacán','1907-07-06','456789123','frida@art.com'),(4,'Claude Monet','Paris','1840-11-14','789123456','monet@art.com'),(5,'Alfonso','Paris','2024-02-12','789123456','ponchis@art.com');
/*!40000 ALTER TABLE `PINTOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PINTURA`
--

DROP TABLE IF EXISTS `PINTURA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PINTURA` (
  `PINT_ID` int NOT NULL AUTO_INCREMENT,
  `PINT_NOMBRE` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `PINT_FECHA` date DEFAULT NULL,
  `PINT_VALOR` decimal(10,2) DEFAULT NULL,
  `TEC_ID` int DEFAULT NULL,
  `PIN_ID` int DEFAULT NULL,
  PRIMARY KEY (`PINT_ID`),
  KEY `TEC_ID` (`TEC_ID`),
  KEY `PIN_ID` (`PIN_ID`),
  CONSTRAINT `PINTURA_ibfk_1` FOREIGN KEY (`TEC_ID`) REFERENCES `TECNICA` (`TEC_ID`),
  CONSTRAINT `PINTURA_ibfk_2` FOREIGN KEY (`PIN_ID`) REFERENCES `PINTOR` (`PIN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PINTURA`
--

LOCK TABLES `PINTURA` WRITE;
/*!40000 ALTER TABLE `PINTURA` DISABLE KEYS */;
INSERT INTO `PINTURA` VALUES (1,'Les Demoiselles d\'Avignon','2024-12-02',1000000.00,4,1),(2,'Starry Night','1889-06-01',5000000.00,1,2),(3,'The Two Fridas','1939-01-01',3000000.00,3,3),(12,'YaFunciona','2024-12-02',1239876.00,3,2);
/*!40000 ALTER TABLE `PINTURA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TECNICA`
--

DROP TABLE IF EXISTS `TECNICA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TECNICA` (
  `TEC_ID` int NOT NULL AUTO_INCREMENT,
  `TEC_TIPO` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`TEC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TECNICA`
--

LOCK TABLES `TECNICA` WRITE;
/*!40000 ALTER TABLE `TECNICA` DISABLE KEYS */;
INSERT INTO `TECNICA` VALUES (1,'Óleo'),(2,'Acuarela'),(3,'Carboncillo'),(4,'Acrílico');
/*!40000 ALTER TABLE `TECNICA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USUARIO` (
  `USU_ID` int NOT NULL AUTO_INCREMENT,
  `USU_NOMBRE` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `USU_CONTRASENIA` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`USU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO`
--

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
INSERT INTO `USUARIO` VALUES (1,'campusjalpa','campusjalpa');
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-02 23:11:37
