/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.7.11-log : Database - safelyun
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`safelyun` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `safelyun`;

/*Table structure for table `classes` */

DROP TABLE IF EXISTS `classes`;

CREATE TABLE `classes` (
  `classesId` int(11) NOT NULL AUTO_INCREMENT,
  `classesName` varchar(45) NOT NULL,
  `specialty_specialtyId` int(11) NOT NULL,
  PRIMARY KEY (`classesId`),
  UNIQUE KEY `classesName_UNIQUE` (`classesName`),
  KEY `fk_classes_specialty1_idx` (`specialty_specialtyId`),
  CONSTRAINT `fk_classes_specialty1` FOREIGN KEY (`specialty_specialtyId`) REFERENCES `specialty` (`specialtyId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1286 DEFAULT CHARSET=utf8;

/*Data for the table `classes` */

insert  into `classes`(`classesId`,`classesName`,`specialty_specialtyId`) values (111,'中文本S1班',11),(115,'中文本2班',11),(125,'广电本1班',12),(126,'广电本2班',12),(127,'广电本Z3班',12),(211,'英语本S1班',21),(215,'英语本2班',21),(216,'英语本3班',21),(217,'英语本Z4班',21),(225,'日语本1班',22),(311,'数学本S1班',31),(325,'计科本1班',32),(326,'计科本Z2班',32),(335,'信计本1班',33),(345,'软件本1班',34),(346,'软件本2班',34),(365,'物网本1班',36),(415,'电信本1班',41),(416,'电信本2班',41),(417,'电信本Z3班',41),(425,'物理本1班',42),(435,'机械本1班',43),(436,'机械本2班',43),(445,'电气本1班',44),(446,'电气本2班',44),(455,'车辆本1班',45),(515,'营销本1班',51),(516,'营销本Z2班',51),(517,'营销本Z3班',51),(535,'国贸本1班',53),(536,'国贸本2班',53),(545,'财管本1班',54),(546,'财管本2班',54),(555,'旅管本1班',55),(556,'旅管本2班',55),(615,'材料本1班',61),(616,'材料本2班',61),(621,'化学本S1班',62),(635,'应化本1班',63),(636,'应化本2班',63),(711,'体育本S1班',71),(712,'体育本S2班',71),(815,'生技本1班',81),(825,'动医本1班',82),(826,'动医本2班',82),(831,'生科本S1班',83),(845,'动科本1班',84),(915,'采矿本1班',91),(925,'测绘本1班',92),(926,'测绘本2班',92),(935,'地质本1班',93),(945,'土木本1班',94),(1011,'音乐本S1班',101),(1012,'音乐本S2班',101),(1021,'美术本S1班',102),(1025,'美术本2班',102),(1035,'产品本1班',103),(1036,'产品本2班',103),(1111,'心理本S1班',111),(1115,'心理本2班',111),(1121,'学前本S1班',112),(1122,'学前本S2班',112),(1123,'学前本S3班',112),(1225,'机械本M1班',122),(1245,'产品本M1班',124),(1246,'产品本M2班',124),(1255,'车辆本M1班',125),(1265,'财管本M1班',126),(1275,'物网本M1班',127),(1285,'学前本M1班',128);

/*Table structure for table `datadic_groups` */

DROP TABLE IF EXISTS `datadic_groups`;

CREATE TABLE `datadic_groups` (
  `group_code` varchar(10) NOT NULL,
  `group_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`group_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `datadic_groups` */

insert  into `datadic_groups`(`group_code`,`group_name`) values ('0','学院'),('01','文学与传媒学院'),('011','汉语言文学S'),('012','广播电视学Z'),('02','外国语学院'),('021','英语Z'),('022','日语'),('03','信息工程学院'),('031','数学与应用数学S'),('032','计算机科学与技术'),('033','信息与计算科学'),('034','软件工程'),('036','物联网工程'),('04','机电工程学院'),('041','电子信息工程'),('042','物理学（光电子技术方向）'),('043','机械设计制造及其自动化'),('044','电气工程及其自动化'),('045','车辆工程'),('05','经济与管理学院'),('051','市场营销'),('053','国际经济与贸易'),('054','财务管理'),('055','旅游管理'),('06','化学与材料学院'),('061','材料科学与工程'),('062','化学S'),('063','应用化学'),('07','体育系'),('071','体育教育S'),('08','生命科学学院'),('081','生物技术'),('082','动物医学'),('083','生物科学S'),('084','动物科学'),('09','资源工程学院'),('091','采矿工程'),('092','测绘工程'),('093','地质工程'),('094','土木工程'),('10','艺术系'),('101','音乐学S'),('102','美术学(师范类)S'),('103','产品设计'),('11','教育科学学院'),('111','心理学'),('112','学前教育S'),('12','奇迈学院'),('122','机械设计制造及其自动化M'),('124','产品设计M'),('125','车辆工程M'),('126','财务管理M'),('127','物联网工程M'),('128','学前教育M');

/*Table structure for table `datadic_items` */

DROP TABLE IF EXISTS `datadic_items`;

CREATE TABLE `datadic_items` (
  `item_code` varchar(10) NOT NULL,
  `item_name` varchar(45) DEFAULT NULL,
  `datadic_groups_group_code` varchar(3) NOT NULL,
  PRIMARY KEY (`item_code`),
  KEY `fk_datadic_items_datadic_groups1_idx` (`datadic_groups_group_code`),
  CONSTRAINT `fk_datadic_items_datadic_groups1` FOREIGN KEY (`datadic_groups_group_code`) REFERENCES `datadic_groups` (`group_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `datadic_items` */

insert  into `datadic_items`(`item_code`,`item_name`,`datadic_groups_group_code`) values ('01','文学与传媒学院','0'),('011','汉语言文学S','01'),('0111','中文本S1班','011'),('0115','中文本2班','011'),('012','广播电视学Z','01'),('0125','广电本1班','012'),('0126','广电本2班','012'),('0127','广电本Z3班','012'),('02','外国语学院','0'),('021','英语Z','02'),('0211','英语本S1班','021'),('0215','英语本2班','021'),('0216','英语本3班','021'),('0217','英语本Z4班','021'),('022','日语','02'),('0225','日语本1班','022'),('03','信息工程学院','0'),('031','数学与应用数学S','03'),('0311','数学本S1班','031'),('032','计算机科学与技术','03'),('0325','计科本1班','032'),('0326','计科本Z2班','032'),('033','信息与计算科学','03'),('0335','信计本1班','033'),('034','软件工程','03'),('0345','软件本1班','034'),('0346','软件本2班','034'),('036','物联网工程','03'),('0365','物网本1班','036'),('04','机电工程学院','0'),('041','电子信息工程','04'),('0415','电信本1班','041'),('0416','电信本2班','041'),('0417','电信本Z3班','041'),('042','物理学（光电子技术方向）','04'),('0425','物理本1班','042'),('043','机械设计制造及其自动化','04'),('0435','机械本1班','043'),('0436','机械本2班','043'),('044','电气工程及其自动化','04'),('0445','电气本1班','044'),('0446','电气本2班','044'),('045','车辆工程','04'),('0455','车辆本1班','045'),('05','经济与管理学院','0'),('051','市场营销','05'),('0515','营销本1班','051'),('0516','营销本Z2班','051'),('0517','营销本Z3班','051'),('053','国际经济与贸易','05'),('0535','国贸本1班','053'),('0536','国贸本2班','053'),('054','财务管理','05'),('0545','财管本1班','054'),('0546','财管本2班','054'),('055','旅游管理','05'),('0555','旅管本1班','055'),('0556','旅管本2班','055'),('06','化学与材料学院','0'),('061','材料科学与工程','06'),('0615','材料本1班','061'),('0616','材料本2班','061'),('062','化学S','06'),('0621','化学本S1班','062'),('063','应用化学','06'),('0635','应化本1班','063'),('0636','应化本2班','063'),('07','体育系','0'),('071','体育教育S','07'),('0711','体育本S1班','071'),('0712','体育本S2班','071'),('08','生命科学学院','0'),('081','生物技术','08'),('0815','生技本1班','081'),('082','动物医学','08'),('0825','动医本1班','082'),('0826','动医本2班','082'),('083','生物科学S','08'),('0831','生科本S1班','083'),('084','动物科学','08'),('0845','动科本1班','084'),('09','资源工程学院','0'),('091','采矿工程','09'),('0915','采矿本1班','091'),('092','测绘工程','09'),('0925','测绘本1班','092'),('0926','测绘本2班','092'),('093','地质工程','09'),('0935','地质本1班','093'),('094','土木工程','09'),('0945','土木本1班','094'),('10','艺术系','0'),('101','音乐学S','10'),('1011','音乐本S1班','101'),('1012','音乐本S2班','101'),('102','美术学(师范类)S','10'),('1021','美术本S1班','102'),('1025','美术本2班','102'),('103','产品设计','10'),('1035','产品本1班','103'),('1036','产品本2班','103'),('11','教育科学学院','0'),('111','心理学','11'),('1111','心理本S1班','111'),('1115','心理本2班','111'),('112','学前教育S','11'),('1121','学前本S1班','112'),('1122','学前本S2班','112'),('1123','学前本S3班','112'),('12','奇迈学院','0'),('122','机械设计制造及其自动化M','12'),('1225','机械本M1班','122'),('124','产品设计M','12'),('1245','产品本M1班','124'),('1246','产品本M2班','124'),('125','车辆工程M','12'),('1255','车辆本M1班','125'),('126','财务管理M','12'),('1265','财管本M1班','126'),('127','物联网工程M','12'),('1275','物网本M1班','127'),('128','学前教育M','12'),('1285','学前本M1班','128');

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `departmentId` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(45) NOT NULL,
  PRIMARY KEY (`departmentId`),
  UNIQUE KEY `departmentName_UNIQUE` (`departmentName`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`departmentId`,`departmentName`) values (7,'体育系'),(3,'信息工程学院'),(6,'化学与材料学院'),(2,'外国语学院'),(12,'奇迈学院'),(11,'教育科学学院'),(1,'文学与传媒学院'),(4,'机电工程学院'),(8,'生命科学学院'),(5,'经济与管理学院'),(10,'艺术系'),(9,'资源工程学院');

/*Table structure for table `examswitch` */

DROP TABLE IF EXISTS `examswitch`;

CREATE TABLE `examswitch` (
  `examswitchId` int(11) NOT NULL AUTO_INCREMENT,
  `switchOnOrOff` bit(1) DEFAULT NULL,
  PRIMARY KEY (`examswitchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `examswitch` (`examswitchId`,`switchOnOrOff`) VALUES (1,'0');

/*Data for the table `examswitch` */

/*Table structure for table `itempool` */

DROP TABLE IF EXISTS `itempool`;

CREATE TABLE `itempool` (
  `itempoolId` int(11) NOT NULL AUTO_INCREMENT,
  `itempoolQuestion` varchar(300) DEFAULT NULL,
  `A` varchar(200) DEFAULT NULL,
  `B` varchar(200) DEFAULT NULL,
  `C` varchar(200) DEFAULT NULL,
  `D` varchar(200) DEFAULT NULL,
  `itempoolCorrect` varchar(1) DEFAULT NULL,
  `itempoolChecked` bit(1) DEFAULT NULL,
  `uploader` varchar(200) NOT NULL,
  PRIMARY KEY (`itempoolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `itempool` */

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(100) DEFAULT NULL,
  `menuUrl` varchar(200) DEFAULT NULL,
  `parentMenuId` int(11) DEFAULT NULL,
  PRIMARY KEY (`menuId`),
  UNIQUE KEY `menuUrl_UNIQUE` (`menuUrl`),
  KEY `fk_menu_menu1_idx` (`parentMenuId`),
  CONSTRAINT `fk_menu_menu1` FOREIGN KEY (`parentMenuId`) REFERENCES `menu` (`menuId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`menuId`,`menuName`,`menuUrl`,`parentMenuId`) values (1,'学生信息','/admin/xsgl/xsgl.jsp',NULL),(2,'章节管理','/admin/zjgl/zjgl.jsp',NULL),(3,'题库管理','/admin/tkgl/tkgl.jsp',NULL),(4,'报表查看','/admin/bbgl/bbgl.jsp',NULL),(5,'应用监控','/admin/yyjk/yyjk.jsp',NULL),(6,'chapterManager','/chapter',3),(7,'sectionController','/sectionController',3),(8,'questionbankManager','/questionbank',2),(9,'reportManager','/report',4),(10,'reportPrintManager','/reportPrint',4),(11,'adminuserManager','/adminuser',1),(12,'switchController','/switchController',1),(13,'applicationMonitoring','/druid',5);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `roleName_UNIQUE` (`roleName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`roleId`,`roleName`) values (1,'administrators'),(2,'instructor'),(3,'运维管理员');

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `role_menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_roleId` int(11) NOT NULL,
  `menu_menuId` int(11) NOT NULL,
  PRIMARY KEY (`role_menu_id`),
  KEY `fk_role_has_menu_menu1_idx` (`menu_menuId`),
  KEY `fk_role_has_menu_role1_idx` (`role_roleId`),
  CONSTRAINT `fk_role_has_menu_menu1` FOREIGN KEY (`menu_menuId`) REFERENCES `menu` (`menuId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_menu_role1` FOREIGN KEY (`role_roleId`) REFERENCES `role` (`roleId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `role_menu` */

insert  into `role_menu`(`role_menu_id`,`role_roleId`,`menu_menuId`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,6),(6,1,7),(7,1,8),(8,1,11),(9,1,12),(10,1,9),(11,1,10),(12,3,1),(13,3,2),(14,3,3),(15,3,4),(16,3,5),(17,3,6),(18,3,7),(19,3,11),(20,3,8),(21,3,12),(22,3,9),(23,3,10),(24,3,13),(25,2,4),(26,2,9),(27,2,10);

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `scoreId` int(11) NOT NULL AUTO_INCREMENT,
  `scoreMark` float DEFAULT NULL,
  `scoreMakeupNum` int(11) DEFAULT NULL,
  `student_studentId` int(11) NOT NULL,
  PRIMARY KEY (`scoreId`),
  KEY `fk_score_student1_idx` (`student_studentId`),
  CONSTRAINT `fk_score_student1` FOREIGN KEY (`student_studentId`) REFERENCES `student` (`studentId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `score` */

/*Table structure for table `section` */

DROP TABLE IF EXISTS `section`;

CREATE TABLE `section` (
  `sectionId` int(11) NOT NULL AUTO_INCREMENT,
  `sectionName` varchar(100) DEFAULT NULL,
  `sectionChecked` bit(1) DEFAULT NULL,
  `sectionCode` int(11) DEFAULT NULL,
  `sectionUploader` varchar(45) NOT NULL,
  PRIMARY KEY (`sectionId`),
  UNIQUE KEY `sectionCode_UNIQUE` (`sectionCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `section` */

/*Table structure for table `semester` */

DROP TABLE IF EXISTS `semester`;

CREATE TABLE `semester` (
  `semesterId` int(11) NOT NULL AUTO_INCREMENT,
  `semesterName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`semesterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `semester` */

/*Table structure for table `specialty` */

DROP TABLE IF EXISTS `specialty`;

CREATE TABLE `specialty` (
  `specialtyId` int(11) NOT NULL AUTO_INCREMENT,
  `specialtyName` varchar(45) NOT NULL,
  `department_departmentId` int(11) NOT NULL,
  PRIMARY KEY (`specialtyId`),
  UNIQUE KEY `specialtyName_UNIQUE` (`specialtyName`),
  KEY `fk_specialty_department1_idx` (`department_departmentId`),
  CONSTRAINT `fk_specialty_department1` FOREIGN KEY (`department_departmentId`) REFERENCES `department` (`departmentId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

/*Data for the table `specialty` */

insert  into `specialty`(`specialtyId`,`specialtyName`,`department_departmentId`) values (11,'汉语言文学S',1),(12,'广播电视学Z',1),(21,'英语Z',2),(22,'日语',2),(31,'数学与应用数学S',3),(32,'计算机科学与技术',3),(33,'信息与计算科学',3),(34,'软件工程',3),(36,'物联网工程',3),(41,'电子信息工程',4),(42,'物理学（光电子技术方向）',4),(43,'机械设计制造及其自动化',4),(44,'电气工程及其自动化',4),(45,'车辆工程',4),(51,'市场营销',5),(53,'国际经济与贸易',5),(54,'财务管理',5),(55,'旅游管理',5),(61,'材料科学与工程',6),(62,'化学S',6),(63,'应用化学',6),(71,'体育教育S',7),(81,'生物技术',8),(82,'动物医学',8),(83,'生物科学S',8),(84,'动物科学',8),(91,'采矿工程',9),(92,'测绘工程',9),(93,'地质工程',9),(94,'土木工程',9),(101,'音乐学S',10),(102,'美术学(师范类)S',10),(103,'产品设计',10),(111,'心理学',11),(112,'学前教育S',11),(122,'机械设计制造及其自动化M',12),(124,'产品设计M',12),(125,'车辆工程M',12),(126,'财务管理M',12),(127,'物联网工程M',12),(128,'学前教育M',12);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `studentId` int(11) NOT NULL AUTO_INCREMENT,
  `studentNumber` varchar(10) DEFAULT NULL,
  `studentName` varchar(100) DEFAULT NULL,
  `grade` varchar(4) DEFAULT NULL,
  `studentSex` varchar(2) DEFAULT NULL,
  `studentEmail` varchar(45) DEFAULT '',
  `user_userId` int(11) NOT NULL,
  `classes_classesId` int(11) NOT NULL,
  PRIMARY KEY (`studentId`),
  UNIQUE KEY `studentNumber_UNIQUE` (`studentNumber`),
  KEY `fk_student_user1_idx` (`user_userId`),
  KEY `fk_student_classes1_idx` (`classes_classesId`),
  CONSTRAINT `fk_student_classes1` FOREIGN KEY (`classes_classesId`) REFERENCES `classes` (`classesId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_user1` FOREIGN KEY (`user_userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

/*Table structure for table `studyschedule` */

DROP TABLE IF EXISTS `studyschedule`;

CREATE TABLE `studyschedule` (
  `studyscheduleId` int(11) NOT NULL AUTO_INCREMENT,
  `studyscheduleHasNum` int(11) DEFAULT NULL,
  `subsection_subsectionId` int(11) NOT NULL,
  `student_studentId` int(11) NOT NULL,
  PRIMARY KEY (`studyscheduleId`),
  KEY `fk_studyschedule_subsection1_idx` (`subsection_subsectionId`),
  KEY `fk_studyschedule_student1_idx` (`student_studentId`),
  CONSTRAINT `fk_studyschedule_student1` FOREIGN KEY (`student_studentId`) REFERENCES `student` (`studentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_studyschedule_subsection1` FOREIGN KEY (`subsection_subsectionId`) REFERENCES `subsection` (`subsectionId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `studyschedule` */

/*Table structure for table `subsection` */

DROP TABLE IF EXISTS `subsection`;

CREATE TABLE `subsection` (
  `subsectionId` int(11) NOT NULL AUTO_INCREMENT,
  `subsectionName` varchar(100) DEFAULT NULL,
  `subsectionContent` text,
  `subsectionTime` int(11) DEFAULT NULL COMMENT '存储时长，只记录多少分钟',
  `subsectionChecked` bit(1) DEFAULT NULL,
  `subsectionCode` int(11) DEFAULT NULL,
  `subsectionUploader` varchar(45) NOT NULL,
  `section_sectionId` int(11) NOT NULL,
  PRIMARY KEY (`subsectionId`),
  UNIQUE KEY `subsectionCode_UNIQUE` (`subsectionCode`),
  KEY `fk_subsection_section1_idx` (`section_sectionId`),
  CONSTRAINT `fk_subsection_section1` FOREIGN KEY (`section_sectionId`) REFERENCES `section` (`sectionId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `subsection` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) NOT NULL,
  `userPassword` varchar(100) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `id_user_UNIQUE` (`userId`),
  UNIQUE KEY `userName_UNIQUE` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userId`,`userName`,`userPassword`) values (1,'2013034589','e10adc3949ba59abbe56e057f20f883e'),(2,'2013034590','e10adc3949ba59abbe56e057f20f883e'),(3,'2013034591','e10adc3949ba59abbe56e057f20f883e');

/*Table structure for table `user_classes` */

DROP TABLE IF EXISTS `user_classes`;

CREATE TABLE `user_classes` (
  `user_classes_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_userId` int(11) NOT NULL,
  `classes_classesId` int(11) NOT NULL,
  `grade` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`user_classes_id`),
  KEY `fk_user_has_classes_classes1_idx` (`classes_classesId`),
  KEY `fk_user_has_classes_user1_idx` (`user_userId`),
  CONSTRAINT `fk_user_has_classes_classes1` FOREIGN KEY (`classes_classesId`) REFERENCES `classes` (`classesId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_classes_user1` FOREIGN KEY (`user_userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

/*Data for the table `user_classes` */

insert  into `user_classes`(`user_classes_id`,`user_userId`,`classes_classesId`,`grade`) values (1,3,111,NULL),(2,3,115,NULL),(3,3,125,NULL),(4,3,126,NULL),(5,3,127,NULL),(6,3,211,NULL),(7,3,215,NULL),(8,3,216,NULL),(9,3,217,NULL),(10,3,225,NULL),(11,3,311,NULL),(12,3,325,NULL),(13,3,326,NULL),(14,3,335,NULL),(15,3,345,NULL),(16,3,346,NULL),(17,3,365,NULL),(18,3,415,NULL),(19,3,416,NULL),(20,3,417,NULL),(21,3,425,NULL),(22,3,435,NULL),(23,3,436,NULL),(24,3,445,NULL),(25,3,446,NULL),(26,3,455,NULL),(27,3,515,NULL),(28,3,516,NULL),(29,3,517,NULL),(30,3,535,NULL),(31,3,536,NULL),(32,3,545,NULL),(33,3,546,NULL),(34,3,555,NULL),(35,3,556,NULL),(36,3,615,NULL),(37,3,616,NULL),(38,3,621,NULL),(39,3,635,NULL),(40,3,636,NULL),(41,3,711,NULL),(42,3,712,NULL),(43,3,815,NULL),(44,3,825,NULL),(45,3,826,NULL),(46,3,831,NULL),(47,3,845,NULL),(48,3,915,NULL),(49,3,925,NULL),(50,3,926,NULL),(51,3,935,NULL),(52,3,945,NULL),(53,3,1011,NULL),(54,3,1012,NULL),(55,3,1021,NULL),(56,3,1025,NULL),(57,3,1035,NULL),(58,3,1036,NULL),(59,3,1111,NULL),(60,3,1115,NULL),(61,3,1121,NULL),(62,3,1122,NULL),(63,3,1123,NULL),(64,3,1225,NULL),(65,3,1245,NULL),(66,3,1246,NULL),(67,3,1255,NULL),(68,3,1265,NULL),(69,3,1275,NULL),(70,3,1285,NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_userId` int(11) NOT NULL,
  `role_roleId` int(11) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `fk_user_has_role_role1_idx` (`role_roleId`),
  KEY `fk_user_has_role_user1_idx` (`user_userId`),
  CONSTRAINT `fk_user_has_role_role1` FOREIGN KEY (`role_roleId`) REFERENCES `role` (`roleId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_user1` FOREIGN KEY (`user_userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`user_role_id`,`user_userId`,`role_roleId`) values (1,1,3),(2,2,1),(3,3,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
