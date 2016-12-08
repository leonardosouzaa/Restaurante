DROP DATABASE IF EXISTS restaurante;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema restaurante
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema restaurante
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `restaurante` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `restaurante` ;

-- -----------------------------------------------------
-- Table `restaurante`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`pedido` (
  `codigo` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `dataCompra` VARCHAR(20) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `valor` DOUBLE NULL DEFAULT NULL,
  `numeroMesa` INT NULL DEFAULT 0,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `restaurante`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`produto` (
  `codigo` BIGINT(20) NOT NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `descricao` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `preco` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `restaurante`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`item` (
  `codigo` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `produto_codigo` BIGINT(20) NOT NULL,
  `pedido_codigo` BIGINT(20) NOT NULL,
  `quantidade` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `Item_FKIndex1` (`pedido_codigo` ASC),
  INDEX `item_FKIndex2` (`produto_codigo` ASC),
  CONSTRAINT `item_ibfk_1`
    FOREIGN KEY (`pedido_codigo`)
    REFERENCES `restaurante`.`pedido` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `item_ibfk_2`
    FOREIGN KEY (`produto_codigo`)
    REFERENCES `restaurante`.`produto` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO `restaurante`.`produto` (codigo, nome, descricao, preco) VALUES (1, 'Coca-Cola', 'Refrigerante de 350ml', '1000.00');
INSERT INTO `restaurante`.`produto` (codigo, nome, descricao, preco) VALUES (2, 'Sprite', 'Refrigerante de 350ml', '4.00');
INSERT INTO `restaurante`.`produto` (codigo, nome, descricao, preco) VALUES (3, 'Fanta Laranja', 'Refrigerante de 350ml', '4.00');
INSERT INTO `restaurante`.`produto` (codigo, nome, descricao, preco) VALUES (4, 'Fanta Uva', 'Refrigerante de 350ml', '4.00');
INSERT INTO `restaurante`.`produto` (codigo, nome, descricao, preco) VALUES (5, 'Pepsi', 'Refrigerante de 350ml', '3.50');
