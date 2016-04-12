SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `safelyun` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `safelyun` ;

-- -----------------------------------------------------
-- Table `safelyun`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`user` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`user` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(25) NOT NULL,
  `userPassword` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `id_user_UNIQUE` (`userId` ASC),
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`role` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`role` (
  `roleId` INT NOT NULL AUTO_INCREMENT,
  `roleName` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE INDEX `roleName_UNIQUE` (`roleName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`menu` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`menu` (
  `menuId` INT NOT NULL AUTO_INCREMENT,
  `menuName` VARCHAR(25) NULL,
  `menuUrl` VARCHAR(45) NULL,
  PRIMARY KEY (`menuId`),
  UNIQUE INDEX `menuUrl_UNIQUE` (`menuUrl` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`department` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`department` (
  `departmentId` INT NOT NULL AUTO_INCREMENT,
  `departmentName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`departmentId`),
  UNIQUE INDEX `departmentName_UNIQUE` (`departmentName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`specialty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`specialty` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`specialty` (
  `specialtyId` INT NOT NULL AUTO_INCREMENT,
  `specialtyName` VARCHAR(45) NOT NULL,
  `department_departmentId` INT NOT NULL,
  PRIMARY KEY (`specialtyId`),
  INDEX `fk_specialty_department1_idx` (`department_departmentId` ASC),
  UNIQUE INDEX `specialtyName_UNIQUE` (`specialtyName` ASC),
  CONSTRAINT `fk_specialty_department1`
    FOREIGN KEY (`department_departmentId`)
    REFERENCES `safelyun`.`department` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`classes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`classes` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`classes` (
  `classesId` INT NOT NULL AUTO_INCREMENT,
  `classesName` VARCHAR(15) NOT NULL,
  `specialty_specialtyId` INT NOT NULL,
  PRIMARY KEY (`classesId`),
  INDEX `fk_classes_specialty1_idx` (`specialty_specialtyId` ASC),
  UNIQUE INDEX `classesName_UNIQUE` (`classesName` ASC),
  CONSTRAINT `fk_classes_specialty1`
    FOREIGN KEY (`specialty_specialtyId`)
    REFERENCES `safelyun`.`specialty` (`specialtyId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`student` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`student` (
  `studentId` INT NOT NULL AUTO_INCREMENT,
  `studentNumber` VARCHAR(10) NOT NULL,
  `studentName` VARCHAR(15) NOT NULL,
  `grade` VARCHAR(4) NOT NULL,
  `studentSex` VARCHAR(2) NULL,
  `studentEmail` VARCHAR(25) NULL DEFAULT '',
  `user_userId` INT NOT NULL,
  `classes_classesId` INT NOT NULL,
  PRIMARY KEY (`studentId`),
  INDEX `fk_student_user1_idx` (`user_userId` ASC),
  INDEX `fk_student_classes1_idx` (`classes_classesId` ASC),
  UNIQUE INDEX `studentNumber_UNIQUE` (`studentNumber` ASC),
  CONSTRAINT `fk_student_user1`
    FOREIGN KEY (`user_userId`)
    REFERENCES `safelyun`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_classes1`
    FOREIGN KEY (`classes_classesId`)
    REFERENCES `safelyun`.`classes` (`classesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`score`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`score` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`score` (
  `scoreId` INT NOT NULL AUTO_INCREMENT,
  `scoreMark` FLOAT NULL,
  `scoreMakeupNum` INT NULL,
  `student_studentId` INT NOT NULL,
  PRIMARY KEY (`scoreId`),
  INDEX `fk_score_student1_idx` (`student_studentId` ASC),
  CONSTRAINT `fk_score_student1`
    FOREIGN KEY (`student_studentId`)
    REFERENCES `safelyun`.`student` (`studentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`section`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`section` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`section` (
  `sectionId` INT NOT NULL AUTO_INCREMENT,
  `sectionName` VARCHAR(25) NULL,
  `sectionChecked` BIT NULL,
  PRIMARY KEY (`sectionId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`subsection`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`subsection` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`subsection` (
  `subsectionId` INT NOT NULL AUTO_INCREMENT,
  `subsectionName` VARCHAR(45) NULL,
  `subsectionContent` TEXT NULL,
  `subsectionTime` TIME NULL,
  `subsectionChecked` BIT NULL,
  `section_sectionId` INT NOT NULL,
  PRIMARY KEY (`subsectionId`),
  INDEX `fk_subsection_section1_idx` (`section_sectionId` ASC),
  CONSTRAINT `fk_subsection_section1`
    FOREIGN KEY (`section_sectionId`)
    REFERENCES `safelyun`.`section` (`sectionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`studyschedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`studyschedule` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`studyschedule` (
  `studyscheduleId` INT NOT NULL AUTO_INCREMENT,
  `studyscheduleHasNum` INT NULL,
  `subsection_subsectionId` INT NOT NULL,
  `student_studentId` INT NOT NULL,
  PRIMARY KEY (`studyscheduleId`),
  INDEX `fk_studyschedule_subsection1_idx` (`subsection_subsectionId` ASC),
  INDEX `fk_studyschedule_student1_idx` (`student_studentId` ASC),
  CONSTRAINT `fk_studyschedule_subsection1`
    FOREIGN KEY (`subsection_subsectionId`)
    REFERENCES `safelyun`.`subsection` (`subsectionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_studyschedule_student1`
    FOREIGN KEY (`student_studentId`)
    REFERENCES `safelyun`.`student` (`studentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`semester`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`semester` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`semester` (
  `semesterId` INT NOT NULL AUTO_INCREMENT,
  `semesterName` VARCHAR(45) NULL,
  PRIMARY KEY (`semesterId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`examswitch`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`examswitch` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`examswitch` (
  `examswitchId` INT NOT NULL AUTO_INCREMENT,
  `switchOnOrOff` BIT NULL,
  PRIMARY KEY (`examswitchId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`itempool`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`itempool` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`itempool` (
  `itempoolId` INT NOT NULL AUTO_INCREMENT,
  `itempoolQuestion` VARCHAR(250) NULL,
  `A` VARCHAR(100) NULL,
  `B` VARCHAR(100) NULL,
  `C` VARCHAR(100) NULL,
  `D` VARCHAR(100) NULL,
  `itempoolCorrect` VARCHAR(1) NULL,
  `itempoolChecked` BIT NULL,
  PRIMARY KEY (`itempoolId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`semester`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`semester` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`semester` (
  `semesterId` INT NOT NULL AUTO_INCREMENT,
  `semesterName` VARCHAR(45) NULL,
  PRIMARY KEY (`semesterId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`user_role` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`user_role` (
  `user_role_id` INT NOT NULL AUTO_INCREMENT,
  `user_userId` INT NOT NULL,
  `role_roleId` INT NOT NULL,
  PRIMARY KEY (`user_role_id`),
  INDEX `fk_user_has_role_role1_idx` (`role_roleId` ASC),
  INDEX `fk_user_has_role_user1_idx` (`user_userId` ASC),
  CONSTRAINT `fk_user_has_role_user1`
    FOREIGN KEY (`user_userId`)
    REFERENCES `safelyun`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`role_roleId`)
    REFERENCES `safelyun`.`role` (`roleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`role_menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`role_menu` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`role_menu` (
  `role_menu_id` INT NOT NULL AUTO_INCREMENT,
  `role_roleId` INT NOT NULL,
  `menu_menuId` INT NOT NULL,
  PRIMARY KEY (`role_menu_id`),
  INDEX `fk_role_has_menu_menu1_idx` (`menu_menuId` ASC),
  INDEX `fk_role_has_menu_role1_idx` (`role_roleId` ASC),
  CONSTRAINT `fk_role_has_menu_role1`
    FOREIGN KEY (`role_roleId`)
    REFERENCES `safelyun`.`role` (`roleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_menu_menu1`
    FOREIGN KEY (`menu_menuId`)
    REFERENCES `safelyun`.`menu` (`menuId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`datadic_groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`datadic_groups` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`datadic_groups` (
  `group_code` VARCHAR(3) NOT NULL,
  `group_name` VARCHAR(45) NULL,
  PRIMARY KEY (`group_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`datadic_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`datadic_items` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`datadic_items` (
  `item_code` VARCHAR(3) NOT NULL,
  `item_name` VARCHAR(45) NULL,
  `datadic_groups_group_code` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`item_code`),
  INDEX `fk_datadic_items_datadic_groups1_idx` (`datadic_groups_group_code` ASC),
  CONSTRAINT `fk_datadic_items_datadic_groups1`
    FOREIGN KEY (`datadic_groups_group_code`)
    REFERENCES `safelyun`.`datadic_groups` (`group_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`grade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`grade` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`grade` (
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelyun`.`user_classes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelyun`.`user_classes` ;

CREATE TABLE IF NOT EXISTS `safelyun`.`user_classes` (
  `user_classes_id` INT NOT NULL,
  `user_userId` INT NOT NULL,
  `classes_classesId` INT NOT NULL,
  PRIMARY KEY (`user_classes_id`),
  INDEX `fk_user_has_classes_classes1_idx` (`classes_classesId` ASC),
  INDEX `fk_user_has_classes_user1_idx` (`user_userId` ASC),
  CONSTRAINT `fk_user_has_classes_user1`
    FOREIGN KEY (`user_userId`)
    REFERENCES `safelyun`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_classes_classes1`
    FOREIGN KEY (`classes_classesId`)
    REFERENCES `safelyun`.`classes` (`classesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
