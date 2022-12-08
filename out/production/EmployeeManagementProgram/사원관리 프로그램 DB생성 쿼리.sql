create database employeedb;
use employeedb;

create table `admin` (
	`id` varchar(45) not null,
    `pw` varchar(45) not null,
    `birthdate` int not null,
    `name` varchar(45) not null,
    `address` varchar(45) not null,
    `tel` varchar(45) not null,
    primary key (`id`)
);

create table `employee` (
	`number` int not null auto_increment,
    `name` varchar(45) not null,
    `birthdate` int not null,
    `address` varchar(45) not null,
    `email` varchar(45) not null,
    `tel` varchar(45) not null,
    `position` varchar(45) not null,
<--    `image` longblob, 미 구현 상태
    primary key (`number`)
);

insert into `admin` values('admin', '1234', 19800504, '홍길동', '서울특별시', '010-1234-5678');

insert into `employee` values(1, '심청이', 19900211, '인천광역시', 'simchung123@gmail.com', '010-1111-1111', '사원');