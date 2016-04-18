SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `safelyun` DEFAULT CHARACTER SET utf8 ;
USE `safelyun` ;

-- -----------------------------------------------------
-- Table `safelyun`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`department` (
  `departmentId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`departmentId`),
  UNIQUE INDEX `departmentName_UNIQUE` (`departmentName` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`specialty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`specialty` (
  `specialtyId` INT(11) NOT NULL AUTO_INCREMENT,
  `specialtyName` VARCHAR(45) NOT NULL,
  `department_departmentId` INT(11) NOT NULL,
  PRIMARY KEY (`specialtyId`),
  UNIQUE INDEX `specialtyName_UNIQUE` (`specialtyName` ASC),
  INDEX `fk_specialty_department1_idx` (`department_departmentId` ASC),
  CONSTRAINT `fk_specialty_department1`
    FOREIGN KEY (`department_departmentId`)
    REFERENCES `safelyun`.`department` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`classes` (
  `classesId` INT(11) NOT NULL AUTO_INCREMENT,
  `classesName` VARCHAR(15) NOT NULL,
  `specialty_specialtyId` INT(11) NOT NULL,
  PRIMARY KEY (`classesId`),
  UNIQUE INDEX `classesName_UNIQUE` (`classesName` ASC),
  INDEX `fk_classes_specialty1_idx` (`specialty_specialtyId` ASC),
  CONSTRAINT `fk_classes_specialty1`
    FOREIGN KEY (`specialty_specialtyId`)
    REFERENCES `safelyun`.`specialty` (`specialtyId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`datadic_groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`datadic_groups` (
  `group_code` VARCHAR(10) NOT NULL,
  `group_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`group_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`datadic_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`datadic_items` (
  `item_code` VARCHAR(10) NOT NULL,
  `item_name` VARCHAR(45) NULL DEFAULT NULL,
  `datadic_groups_group_code` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`item_code`),
  INDEX `fk_datadic_items_datadic_groups1_idx` (`datadic_groups_group_code` ASC),
  CONSTRAINT `fk_datadic_items_datadic_groups1`
    FOREIGN KEY (`datadic_groups_group_code`)
    REFERENCES `safelyun`.`datadic_groups` (`group_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`examswitch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`examswitch` (
  `examswitchId` INT(11) NOT NULL AUTO_INCREMENT,
  `switchOnOrOff` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`examswitchId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`itempool`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`itempool` (
  `itempoolId` INT(11) NOT NULL AUTO_INCREMENT,
  `itempoolQuestion` VARCHAR(250) NULL DEFAULT NULL,
  `A` VARCHAR(100) NULL DEFAULT NULL,
  `B` VARCHAR(100) NULL DEFAULT NULL,
  `C` VARCHAR(100) NULL DEFAULT NULL,
  `D` VARCHAR(100) NULL DEFAULT NULL,
  `itempoolCorrect` VARCHAR(1) NULL DEFAULT NULL,
  `itempoolChecked` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`itempoolId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`menu` (
  `menuId` INT(11) NOT NULL AUTO_INCREMENT,
  `menuName` VARCHAR(25) NULL DEFAULT NULL,
  `menuUrl` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`menuId`),
  UNIQUE INDEX `menuUrl_UNIQUE` (`menuUrl` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`role` (
  `roleId` INT(11) NOT NULL AUTO_INCREMENT,
  `roleName` VARCHAR(25) NULL DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE INDEX `roleName_UNIQUE` (`roleName` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`role_menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`role_menu` (
  `role_menu_id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_roleId` INT(11) NOT NULL,
  `menu_menuId` INT(11) NOT NULL,
  PRIMARY KEY (`role_menu_id`),
  INDEX `fk_role_has_menu_menu1_idx` (`menu_menuId` ASC),
  INDEX `fk_role_has_menu_role1_idx` (`role_roleId` ASC),
  CONSTRAINT `fk_role_has_menu_menu1`
    FOREIGN KEY (`menu_menuId`)
    REFERENCES `safelyun`.`menu` (`menuId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_menu_role1`
    FOREIGN KEY (`role_roleId`)
    REFERENCES `safelyun`.`role` (`roleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`user` (
  `userId` INT(11) NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(25) NOT NULL,
  `userPassword` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `id_user_UNIQUE` (`userId` ASC),
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`student` (
  `studentId` INT(11) NOT NULL AUTO_INCREMENT,
  `studentNumber` VARCHAR(10) NULL DEFAULT NULL,
  `studentName` VARCHAR(15) NULL DEFAULT NULL,
  `grade` VARCHAR(4) NULL DEFAULT NULL,
  `studentSex` VARCHAR(2) NULL DEFAULT NULL,
  `studentEmail` VARCHAR(25) NULL DEFAULT '',
  `user_userId` INT(11) NOT NULL,
  `classes_classesId` INT(11) NOT NULL,
  PRIMARY KEY (`studentId`),
  UNIQUE INDEX `studentNumber_UNIQUE` (`studentNumber` ASC),
  INDEX `fk_student_user1_idx` (`user_userId` ASC),
  INDEX `fk_student_classes1_idx` (`classes_classesId` ASC),
  CONSTRAINT `fk_student_classes1`
    FOREIGN KEY (`classes_classesId`)
    REFERENCES `safelyun`.`classes` (`classesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_user1`
    FOREIGN KEY (`user_userId`)
    REFERENCES `safelyun`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`score`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`score` (
  `scoreId` INT(11) NOT NULL AUTO_INCREMENT,
  `scoreMark` FLOAT NULL DEFAULT NULL,
  `scoreMakeupNum` INT(11) NULL DEFAULT NULL,
  `student_studentId` INT(11) NOT NULL,
  PRIMARY KEY (`scoreId`),
  INDEX `fk_score_student1_idx` (`student_studentId` ASC),
  CONSTRAINT `fk_score_student1`
    FOREIGN KEY (`student_studentId`)
    REFERENCES `safelyun`.`student` (`studentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`section`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`section` (
  `sectionId` INT(11) NOT NULL AUTO_INCREMENT,
  `sectionName` VARCHAR(25) NULL DEFAULT NULL,
  `sectionChecked` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`sectionId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`semester`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`semester` (
  `semesterId` INT(11) NOT NULL AUTO_INCREMENT,
  `semesterName` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`semesterId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`subsection`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`subsection` (
  `subsectionId` INT(11) NOT NULL AUTO_INCREMENT,
  `subsectionName` VARCHAR(45) NULL DEFAULT NULL,
  `subsectionContent` TEXT NULL DEFAULT NULL,
  `subsectionTime` INT NULL DEFAULT NULL COMMENT '存储时长，只记录多少分钟',
  `subsectionChecked` BIT(1) NULL DEFAULT NULL,
  `section_sectionId` INT(11) NOT NULL,
  PRIMARY KEY (`subsectionId`),
  INDEX `fk_subsection_section1_idx` (`section_sectionId` ASC),
  CONSTRAINT `fk_subsection_section1`
    FOREIGN KEY (`section_sectionId`)
    REFERENCES `safelyun`.`section` (`sectionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`studyschedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`studyschedule` (
  `studyscheduleId` INT(11) NOT NULL AUTO_INCREMENT,
  `studyscheduleHasNum` INT(11) NULL DEFAULT NULL,
  `subsection_subsectionId` INT(11) NOT NULL,
  `student_studentId` INT(11) NOT NULL,
  PRIMARY KEY (`studyscheduleId`),
  INDEX `fk_studyschedule_subsection1_idx` (`subsection_subsectionId` ASC),
  INDEX `fk_studyschedule_student1_idx` (`student_studentId` ASC),
  CONSTRAINT `fk_studyschedule_student1`
    FOREIGN KEY (`student_studentId`)
    REFERENCES `safelyun`.`student` (`studentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_studyschedule_subsection1`
    FOREIGN KEY (`subsection_subsectionId`)
    REFERENCES `safelyun`.`subsection` (`subsectionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`user_classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`user_classes` (
  `user_classes_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_userId` INT(11) NOT NULL,
  `classes_classesId` INT(11) NOT NULL,
  PRIMARY KEY (`user_classes_id`),
  INDEX `fk_user_has_classes_classes1_idx` (`classes_classesId` ASC),
  INDEX `fk_user_has_classes_user1_idx` (`user_userId` ASC),
  CONSTRAINT `fk_user_has_classes_classes1`
    FOREIGN KEY (`classes_classesId`)
    REFERENCES `safelyun`.`classes` (`classesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_classes_user1`
    FOREIGN KEY (`user_userId`)
    REFERENCES `safelyun`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `safelyun`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safelyun`.`user_role` (
  `user_role_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_userId` INT(11) NOT NULL,
  `role_roleId` INT(11) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  INDEX `fk_user_has_role_role1_idx` (`role_roleId` ASC),
  INDEX `fk_user_has_role_user1_idx` (`user_userId` ASC),
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`role_roleId`)
    REFERENCES `safelyun`.`role` (`roleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_user1`
    FOREIGN KEY (`user_userId`)
    REFERENCES `safelyun`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
