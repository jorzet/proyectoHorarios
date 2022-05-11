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
  `moduleCode` VARCHAR(100) NULL,
  `moduleName` VARCHAR(200) NULL,
  `Teacher_idTeacher` INT NOT NULL,
  PRIMARY KEY (`idModule`, `Teacher_idTeacher`),
  INDEX `fk_Module_Teacher1_idx` (`Teacher_idTeacher` ASC) VISIBLE,
  CONSTRAINT `fk_Module_Teacher1`
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
  `size` VARCHAR(45) NULL,
  `Module_idModule` INT NOT NULL,
  PRIMARY KEY (`idGroup`, `Module_idModule`),
  INDEX `fk_Group_Module_idx` (`Module_idModule` ASC) VISIBLE,
  CONSTRAINT `fk_Group_Module`
    FOREIGN KEY (`Module_idModule`)
    REFERENCES `mydb`.`Module` (`idModule`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
