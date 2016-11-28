CREATE SCHEMA `appdb` DEFAULT CHARACTER SET utf8 ;

CREATE USER 'appuser'@'%' IDENTIFIED BY 'appuser123';

GRANT ALL PRIVILEGES ON appdb.* TO 'appuser'@'%';
GRANT ALL PRIVILEGES ON appdb.* TO 'appuser'@'localhost';

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE  TABLE IF NOT EXISTS `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(50) NOT NULL ,
  `password` VARCHAR(50) NOT NULL ,
  `active` BIT(1) NOT NULL ,
  `initial_date` DATE NOT NULL ,
  `final_date` DATE NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `username_UNIQUE` ON `user` (`username` ASC) ;


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `role` ;

CREATE  TABLE IF NOT EXISTS `role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `role` (`name` ASC) ;


-- -----------------------------------------------------
-- Table `user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_role` ;

CREATE  TABLE IF NOT EXISTS `user_role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `role_id` BIGINT NOT NULL ,
  `user_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_user_role_role`
    FOREIGN KEY (`role_id` )
    REFERENCES `role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_user_role_role_idx` ON `user_role` (`role_id` ASC) ;

CREATE INDEX `fk_user_role_user1_idx` ON `user_role` (`user_id` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
