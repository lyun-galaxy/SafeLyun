-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.12 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 safelyun 的数据库结构
CREATE DATABASE IF NOT EXISTS `safelyun` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `safelyun`;


-- 导出  表 safelyun.classes 结构
CREATE TABLE IF NOT EXISTS `classes` (
  `classesId` int(11) NOT NULL AUTO_INCREMENT,
  `classesName` varchar(45) NOT NULL,
  `specialty_specialtyId` int(11) NOT NULL,
  PRIMARY KEY (`classesId`),
  UNIQUE KEY `classesName_UNIQUE` (`classesName`),
  KEY `fk_classes_specialty1_idx` (`specialty_specialtyId`),
  CONSTRAINT `fk_classes_specialty1` FOREIGN KEY (`specialty_specialtyId`) REFERENCES `specialty` (`specialtyId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.classes 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;


-- 导出  表 safelyun.datadic_groups 结构
CREATE TABLE IF NOT EXISTS `datadic_groups` (
  `group_code` varchar(10) NOT NULL,
  `group_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`group_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.datadic_groups 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `datadic_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `datadic_groups` ENABLE KEYS */;


-- 导出  表 safelyun.datadic_items 结构
CREATE TABLE IF NOT EXISTS `datadic_items` (
  `item_code` varchar(10) NOT NULL,
  `item_name` varchar(45) DEFAULT NULL,
  `datadic_groups_group_code` varchar(3) NOT NULL,
  PRIMARY KEY (`item_code`),
  KEY `fk_datadic_items_datadic_groups1_idx` (`datadic_groups_group_code`),
  CONSTRAINT `fk_datadic_items_datadic_groups1` FOREIGN KEY (`datadic_groups_group_code`) REFERENCES `datadic_groups` (`group_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.datadic_items 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `datadic_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `datadic_items` ENABLE KEYS */;


-- 导出  表 safelyun.department 结构
CREATE TABLE IF NOT EXISTS `department` (
  `departmentId` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(45) NOT NULL,
  PRIMARY KEY (`departmentId`),
  UNIQUE KEY `departmentName_UNIQUE` (`departmentName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.department 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;


-- 导出  表 safelyun.examswitch 结构
CREATE TABLE IF NOT EXISTS `examswitch` (
  `examswitchId` int(11) NOT NULL AUTO_INCREMENT,
  `switchOnOrOff` bit(1) DEFAULT NULL,
  PRIMARY KEY (`examswitchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.examswitch 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `examswitch` DISABLE KEYS */;
/*!40000 ALTER TABLE `examswitch` ENABLE KEYS */;


-- 导出  表 safelyun.itempool 结构
CREATE TABLE IF NOT EXISTS `itempool` (
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

-- 正在导出表  safelyun.itempool 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `itempool` DISABLE KEYS */;
/*!40000 ALTER TABLE `itempool` ENABLE KEYS */;


-- 导出  表 safelyun.menu 结构
CREATE TABLE IF NOT EXISTS `menu` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(100) DEFAULT NULL,
  `menuUrl` varchar(200) DEFAULT NULL,
  `parentMenuId` int(11) DEFAULT NULL,
  PRIMARY KEY (`menuId`),
  UNIQUE KEY `menuUrl_UNIQUE` (`menuUrl`),
  KEY `fk_menu_menu1_idx` (`parentMenuId`),
  CONSTRAINT `fk_menu_menu1` FOREIGN KEY (`parentMenuId`) REFERENCES `menu` (`menuId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.menu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


-- 导出  表 safelyun.role 结构
CREATE TABLE IF NOT EXISTS `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `roleName_UNIQUE` (`roleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- 导出  表 safelyun.role_menu 结构
CREATE TABLE IF NOT EXISTS `role_menu` (
  `role_menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_roleId` int(11) NOT NULL,
  `menu_menuId` int(11) NOT NULL,
  PRIMARY KEY (`role_menu_id`),
  KEY `fk_role_has_menu_menu1_idx` (`menu_menuId`),
  KEY `fk_role_has_menu_role1_idx` (`role_roleId`),
  CONSTRAINT `fk_role_has_menu_menu1` FOREIGN KEY (`menu_menuId`) REFERENCES `menu` (`menuId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_menu_role1` FOREIGN KEY (`role_roleId`) REFERENCES `role` (`roleId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.role_menu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;


-- 导出  表 safelyun.score 结构
CREATE TABLE IF NOT EXISTS `score` (
  `scoreId` int(11) NOT NULL AUTO_INCREMENT,
  `scoreMark` float DEFAULT NULL,
  `scoreMakeupNum` int(11) DEFAULT NULL,
  `student_studentId` int(11) NOT NULL,
  PRIMARY KEY (`scoreId`),
  KEY `fk_score_student1_idx` (`student_studentId`),
  CONSTRAINT `fk_score_student1` FOREIGN KEY (`student_studentId`) REFERENCES `student` (`studentId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.score 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
/*!40000 ALTER TABLE `score` ENABLE KEYS */;


-- 导出  表 safelyun.section 结构
CREATE TABLE IF NOT EXISTS `section` (
  `sectionId` int(11) NOT NULL AUTO_INCREMENT,
  `sectionName` varchar(100) DEFAULT NULL,
  `sectionChecked` bit(1) DEFAULT NULL,
  `sectionCode` int(11) DEFAULT NULL,
  `sectionUploader` varchar(45) NOT NULL,
  PRIMARY KEY (`sectionId`),
  UNIQUE KEY `sectionCode_UNIQUE` (`sectionCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.section 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
/*!40000 ALTER TABLE `section` ENABLE KEYS */;


-- 导出  表 safelyun.semester 结构
CREATE TABLE IF NOT EXISTS `semester` (
  `semesterId` int(11) NOT NULL AUTO_INCREMENT,
  `semesterName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`semesterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.semester 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;


-- 导出  表 safelyun.specialty 结构
CREATE TABLE IF NOT EXISTS `specialty` (
  `specialtyId` int(11) NOT NULL AUTO_INCREMENT,
  `specialtyName` varchar(45) NOT NULL,
  `department_departmentId` int(11) NOT NULL,
  PRIMARY KEY (`specialtyId`),
  UNIQUE KEY `specialtyName_UNIQUE` (`specialtyName`),
  KEY `fk_specialty_department1_idx` (`department_departmentId`),
  CONSTRAINT `fk_specialty_department1` FOREIGN KEY (`department_departmentId`) REFERENCES `department` (`departmentId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.specialty 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `specialty` DISABLE KEYS */;
/*!40000 ALTER TABLE `specialty` ENABLE KEYS */;


-- 导出  表 safelyun.student 结构
CREATE TABLE IF NOT EXISTS `student` (
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

-- 正在导出表  safelyun.student 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;


-- 导出  表 safelyun.studyschedule 结构
CREATE TABLE IF NOT EXISTS `studyschedule` (
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

-- 正在导出表  safelyun.studyschedule 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `studyschedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `studyschedule` ENABLE KEYS */;


-- 导出  表 safelyun.subsection 结构
CREATE TABLE IF NOT EXISTS `subsection` (
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

-- 正在导出表  safelyun.subsection 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `subsection` DISABLE KEYS */;
/*!40000 ALTER TABLE `subsection` ENABLE KEYS */;


-- 导出  表 safelyun.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) NOT NULL,
  `userPassword` varchar(100) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `id_user_UNIQUE` (`userId`),
  UNIQUE KEY `userName_UNIQUE` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- 导出  表 safelyun.user_classes 结构
CREATE TABLE IF NOT EXISTS `user_classes` (
  `user_classes_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_userId` int(11) NOT NULL,
  `classes_classesId` int(11) NOT NULL,
  `grade` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`user_classes_id`),
  KEY `fk_user_has_classes_classes1_idx` (`classes_classesId`),
  KEY `fk_user_has_classes_user1_idx` (`user_userId`),
  CONSTRAINT `fk_user_has_classes_classes1` FOREIGN KEY (`classes_classesId`) REFERENCES `classes` (`classesId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_classes_user1` FOREIGN KEY (`user_userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.user_classes 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_classes` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_classes` ENABLE KEYS */;


-- 导出  表 safelyun.user_role 结构
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_userId` int(11) NOT NULL,
  `role_roleId` int(11) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `fk_user_has_role_role1_idx` (`role_roleId`),
  KEY `fk_user_has_role_user1_idx` (`user_userId`),
  CONSTRAINT `fk_user_has_role_role1` FOREIGN KEY (`role_roleId`) REFERENCES `role` (`roleId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_user1` FOREIGN KEY (`user_userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  safelyun.user_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
