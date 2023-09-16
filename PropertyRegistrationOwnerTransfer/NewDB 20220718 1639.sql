-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.30-MariaDB


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema propertyschema
--

CREATE DATABASE IF NOT EXISTS propertyschema;
USE propertyschema;

--
-- Definition of table `attendencelog`
--

DROP TABLE IF EXISTS `attendencelog`;
CREATE TABLE `attendencelog` (
  `idattendencelog` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `eid` varchar(45) NOT NULL,
  `imagepath` varchar(45) NOT NULL,
  `currenttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idattendencelog`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendencelog`
--

/*!40000 ALTER TABLE `attendencelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendencelog` ENABLE KEYS */;


--
-- Definition of table `documents`
--

DROP TABLE IF EXISTS `documents`;
CREATE TABLE `documents` (
  `did` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `docName` longtext NOT NULL,
  `userid` varchar(45) NOT NULL,
  `currenttimedate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `signstatus` varchar(45) NOT NULL DEFAULT 'NO',
  `aeskey` varchar(512) NOT NULL,
  `propid` varchar(45) NOT NULL,
  `surveyno` varchar(45) NOT NULL,
  `proptype` varchar(45) NOT NULL,
  `proparea` varchar(45) NOT NULL,
  `addr` varchar(45) NOT NULL,
  `preownername` varchar(45) NOT NULL,
  `preowneraddr` varchar(45) NOT NULL,
  `status` varchar(15) NOT NULL,
  `registrar` varchar(45) NOT NULL,
  `sale` varchar(45) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `documents`
--

/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
INSERT INTO `documents` (`did`,`docName`,`userid`,`currenttimedate`,`signstatus`,`aeskey`,`propid`,`surveyno`,`proptype`,`proparea`,`addr`,`preownername`,`preowneraddr`,`status`,`registrar`,`sale`) VALUES 
 (1,'2_ahmed2017.pdf.pdf','3','2020-08-31 20:07:18','NO','jDseFZg1ze3lS+z6FtSWzdsW7ensEkeYL9dSQSab3ia0Usa9SNtXa2ugRZ8/PWLCV73PgxT8UWTv\r\njir8ZE7HbQ==','1234','54654','House','400','AMt','User1','AMt','in-draft','Admin','N');
/*!40000 ALTER TABLE `documents` ENABLE KEYS */;


--
-- Definition of table `proprequests`
--

DROP TABLE IF EXISTS `proprequests`;
CREATE TABLE `proprequests` (
  `reqid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `did` varchar(45) NOT NULL,
  `reqto` varchar(45) NOT NULL,
  `reqfrom` varchar(45) NOT NULL,
  `uname` varchar(45) DEFAULT NULL,
  `adhar` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `proptype` varchar(45) DEFAULT NULL,
  `stat` varchar(45) DEFAULT 'N',
  PRIMARY KEY (`reqid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proprequests`
--

/*!40000 ALTER TABLE `proprequests` DISABLE KEYS */;
/*!40000 ALTER TABLE `proprequests` ENABLE KEYS */;


--
-- Definition of table `sharerequestdoc`
--

DROP TABLE IF EXISTS `sharerequestdoc`;
CREATE TABLE `sharerequestdoc` (
  `sid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `did` varchar(45) NOT NULL,
  `uid` varchar(45) NOT NULL,
  `currenttimedate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) NOT NULL DEFAULT 'NO',
  `senduserid` varchar(45) NOT NULL,
  `aeskey` longtext NOT NULL,
  `filename` longtext NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sharerequestdoc`
--

/*!40000 ALTER TABLE `sharerequestdoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `sharerequestdoc` ENABLE KEYS */;


--
-- Definition of table `sharerequestonemaildoc`
--

DROP TABLE IF EXISTS `sharerequestonemaildoc`;
CREATE TABLE `sharerequestonemaildoc` (
  `sid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `did` varchar(45) NOT NULL,
  `uid` varchar(45) NOT NULL,
  `senduseremail` varchar(45) NOT NULL,
  `aeskey` longtext NOT NULL,
  `filename` longtext NOT NULL,
  `otp` varchar(45) NOT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sharerequestonemaildoc`
--

/*!40000 ALTER TABLE `sharerequestonemaildoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `sharerequestonemaildoc` ENABLE KEYS */;


--
-- Definition of table `signrequestdoc`
--

DROP TABLE IF EXISTS `signrequestdoc`;
CREATE TABLE `signrequestdoc` (
  `rid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `did` varchar(45) NOT NULL,
  `uid` varchar(45) NOT NULL,
  `currenttimedate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expirydate` varchar(45) NOT NULL DEFAULT '0',
  `msg` varchar(45) NOT NULL DEFAULT '0',
  `status` varchar(45) NOT NULL,
  `ruid` varchar(45) NOT NULL DEFAULT '0',
  `aeskey` varchar(100) NOT NULL DEFAULT '0',
  `filename` varchar(100) NOT NULL DEFAULT '0',
  `propid` varchar(45) NOT NULL,
  `dateofreg` datetime NOT NULL,
  `surveyno` varchar(45) NOT NULL,
  `newownername` varchar(45) NOT NULL,
  `newownermob` varchar(200) NOT NULL,
  `newowneradhar` varchar(45) NOT NULL,
  `newowneremail` varchar(45) NOT NULL,
  `preownername` varchar(45) NOT NULL,
  `preowneradhar` varchar(45) NOT NULL,
  `propaddrs` varchar(45) NOT NULL,
  `marketval` int(10) unsigned NOT NULL,
  `sellval` int(10) unsigned NOT NULL,
  `stampduty` int(10) unsigned NOT NULL,
  `regfee` int(10) unsigned NOT NULL,
  `totalcost` int(10) unsigned NOT NULL,
  `payment` varchar(45) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `signrequestdoc`
--

/*!40000 ALTER TABLE `signrequestdoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `signrequestdoc` ENABLE KEYS */;


--
-- Definition of table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
CREATE TABLE `useraccount` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `emailid` varchar(45) NOT NULL,
  `mobile` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL DEFAULT '2' COMMENT '1: ADmin, 2: USer',
  `fname` varchar(45) NOT NULL,
  `panno` varchar(45) NOT NULL,
  `adharno` varchar(45) NOT NULL,
  `image` longtext NOT NULL,
  `pass` varchar(45) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `useraccount`
--

/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` (`uid`,`emailid`,`mobile`,`role`,`fname`,`panno`,`adharno`,`image`,`pass`) VALUES 
 (1,'admin@gmail.com','1111111111','1','Admin','DFS46567','2342345543212345','2342345543212345.png','1234'),
 (2,'user1@gmail.com','2222222222','2','User1','FSG453','8474849302987456','8474849302987456.png','1234'),
 (3,'user2@gmail.com','3333333333','2','User2','GSH3432','9098345543321112','9098345543321112.png','1234'),
 (6,'user3@gmail.com','4564564664','2','User Three','dsfsd453','342234789009','342234789009.png','1234');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;


--
-- Definition of table `verifreq`
--

DROP TABLE IF EXISTS `verifreq`;
CREATE TABLE `verifreq` (
  `vid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `docName` longtext,
  `userid` varchar(45) DEFAULT NULL,
  `currenttimedate` datetime DEFAULT CURRENT_TIMESTAMP,
  `signstatus` varchar(45) DEFAULT 'NO',
  `aeskey` varchar(512) DEFAULT NULL,
  `propid` varchar(45) DEFAULT NULL,
  `surveyno` varchar(45) DEFAULT NULL,
  `proptype` varchar(45) DEFAULT NULL,
  `proparea` varchar(45) DEFAULT NULL,
  `addr` varchar(45) DEFAULT NULL,
  `preownername` varchar(45) DEFAULT NULL,
  `preowneraddr` varchar(45) DEFAULT NULL,
  `status` varchar(15) DEFAULT 'in-draft',
  `registrar` varchar(45) DEFAULT NULL,
  `filename` longtext,
  `expirydate` varchar(45) DEFAULT NULL,
  `ruid` varchar(45) DEFAULT NULL,
  `msg` varchar(45) DEFAULT NULL,
  `did` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`vid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `verifreq`
--

/*!40000 ALTER TABLE `verifreq` DISABLE KEYS */;
INSERT INTO `verifreq` (`vid`,`docName`,`userid`,`currenttimedate`,`signstatus`,`aeskey`,`propid`,`surveyno`,`proptype`,`proparea`,`addr`,`preownername`,`preowneraddr`,`status`,`registrar`,`filename`,`expirydate`,`ruid`,`msg`,`did`) VALUES 
 (1,'2_2_ahmed2017.pdf.pdf','2','2020-08-31 20:07:30','YES','JbqlT6qkKIkIvOolK8qcKIXe/0JKB6MO8prJZBWXdTOkPc0B3byJfJLnkcW6j/LvaQwIYpbW2Dln\r\nyi6udE4Jxg==','1234','54654','House','400','AMt','KJM','AMT','verified','Admin','2_2_ahmed2017.pdf.pdf','2020-09-03','1','','1');
/*!40000 ALTER TABLE `verifreq` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
