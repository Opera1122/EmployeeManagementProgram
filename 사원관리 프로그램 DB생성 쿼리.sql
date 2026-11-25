CREATE DATABASE IF NOT EXISTS employeedb DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE employeedb;

CREATE TABLE IF NOT EXISTS `admin` (
    `id` VARCHAR(45) NOT NULL,
    `pw` VARCHAR(45) NOT NULL,
    `birthdate` INT NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    `tel` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `employee` (
    `number` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `birthdate` INT NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `tel` VARCHAR(45) NOT NULL,
    `position` VARCHAR(45) NOT NULL,
    `image` LONGBLOB NULL,
    PRIMARY KEY (`number`)
);

-- 기존 employee 테이블에 image 컬럼이 없을 때만 실행하세요.
-- ALTER TABLE employee ADD COLUMN image LONGBLOB NULL;

INSERT INTO `admin` (id, pw, birthdate, name, address, tel)
SELECT 'admin', '1234', 19800504, '홍길동', '서울특별시', '010-1234-5678'
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM `admin` LIMIT 1);

INSERT INTO `employee` (name, birthdate, address, email, tel, position)
SELECT '심청이', 19900211, '인천광역시', 'simchung123@gmail.com', '010-1111-1111', '사원'
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM `employee` LIMIT 1);
