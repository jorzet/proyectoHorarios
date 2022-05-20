-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Teacher` (
  `idTeacher` INT NOT NULL AUTO_INCREMENT,
  `teacherName` VARCHAR(45) NULL,
  `teacherFirstLastName` VARCHAR(45) NULL,
  `teacherSecondLastName` VARCHAR(45) NULL,
  `employeeNum` VARCHAR(45) NULL DEFAULT 'null',
  `employeeType` ENUM('PxA', 'TCD', 'TCF') NULL DEFAULT 'PxA',
  PRIMARY KEY (`idTeacher`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Times`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Times` (
  `idTimes` INT NOT NULL AUTO_INCREMENT,
  `dayname` VARCHAR(45) NULL,
  `times` VARCHAR(45) NULL,
  PRIMARY KEY (`idTimes`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Room` (
  `idRoom` INT NOT NULL AUTO_INCREMENT,
  `roomName` VARCHAR(45) NULL,
  `capacity` INT NULL,
  PRIMARY KEY (`idRoom`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Module`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Module` (
  `idModule` INT NOT NULL AUTO_INCREMENT,
  `moduleCode` VARCHAR(45) NULL,
  `moduleName` VARCHAR(100) NULL,
  `Teacher_idTeacher` INT NOT NULL,
  PRIMARY KEY (`idModule`, `Teacher_idTeacher`),
  INDEX `fk_Module_Teacher_idx` (`Teacher_idTeacher` ASC) VISIBLE,
  CONSTRAINT `fk_Module_Teacher`
    FOREIGN KEY (`Teacher_idTeacher`)
    REFERENCES `mydb`.`Teacher` (`idTeacher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Group` (
  `idGroup` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `size` VARCHAR(45) NULL,
  `matutino` BOOLEAN NULL,
  PRIMARY KEY (`idGroup`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Group_has_Module`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Group_has_Module` (
  `Group_idGroup` INT NOT NULL,
  `Module_idModule` INT NOT NULL,
  `Module_Teacher_idTeacher` INT NOT NULL,
  `times` INT NOT NULL,
  PRIMARY KEY (`Group_idGroup`, `Module_idModule`, `Module_Teacher_idTeacher`),
  INDEX `fk_Group_has_Module_Module1_idx` (`Module_idModule` ASC, `Module_Teacher_idTeacher` ASC) VISIBLE,
  INDEX `fk_Group_has_Module_Group1_idx` (`Group_idGroup` ASC) VISIBLE,
  CONSTRAINT `fk_Group_has_Module_Group1`
    FOREIGN KEY (`Group_idGroup`)
    REFERENCES `mydb`.`Group` (`idGroup`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Group_has_Module_Module1`
    FOREIGN KEY (`Module_idModule` , `Module_Teacher_idTeacher`)
    REFERENCES `mydb`.`Module` (`idModule` , `Teacher_idTeacher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`TimesGroup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TimesGroup` (
  `idTimesGroup` INT NOT NULL AUTO_INCREMENT,
  `classNumber` VARCHAR(45) NULL,
  `groupNumber` VARCHAR(45) NULL,
  `moduleName` VARCHAR(45) NULL,
  `moduleCode` VARCHAR(45) NULL,
  `roomCode` VARCHAR(45) NULL,
  `teacherName` VARCHAR(45) NULL,
  `time` VARCHAR(45) NULL,
  `day` VARCHAR(45) NULL,
  PRIMARY KEY (`idTimesGroup`))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
