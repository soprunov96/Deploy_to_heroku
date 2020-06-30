-- ==============================================================
-- motor_depot DB creation script for MySQL
-- ==============================================================
SET NAMES utf8;

DROP DATABASE IF EXISTS mydb;
CREATE DATABASE mydb CHARACTER SET utf8 COLLATE utf8_bin;

USE mydb;
-- --------------------------------------------------------------
-- ROLES
-- users roles
-- --------------------------------------------------------------

DROP TABLE IF EXISTS mydb.roles;
DROP TABLE IF EXISTS mydb.users;
DROP TABLE IF EXISTS mydb.flight;
CREATE TABLE mydb.roles(
-- id has the INTEGER type (other name is INT), it is the primary key
	id INTEGER NOT NULL PRIMARY KEY,
-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE)
	name VARCHAR(10) NOT NULL UNIQUE
);

-- this two commands insert data into roles table
-- --------------------------------------------------------------
-- we use ENUM as the Role entity, so the numeration must started 
-- from 0 with the step equaled to 1
-- --------------------------------------------------------------
INSERT INTO mydb.roles VALUES(0, 'admin');
INSERT INTO mydb.roles VALUES(1, 'dispatcher');
INSERT INTO mydb.roles VALUES(2, 'driver');

-- --------------------------------------------------------------
-- USERS
-- --------------------------------------------------------------
CREATE TABLE mydb.users(

	id INTEGER NOT NULL auto_increment,
	
-- 'UNIQUE' means logins values should not be repeated in login column of table	
	login VARCHAR(20) NOT NULL UNIQUE,
	
-- not null string columns	
	password VARCHAR(255) NOT NULL,
	first_name VARCHAR(20)NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	user_avatar VARCHAR(45) ,
-- this declaration contains the foreign key constraint	
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
	role_id INTEGER NOT NULL REFERENCES roles(id) 
	
-- removing a row with some ID from roles table implies removing 
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in 
-- users table with ROLES_ID=ID
		ON DELETE CASCADE 

-- the same as previous but updating is used insted deleting
		ON UPDATE RESTRICT,
        PRIMARY KEY (id)
);

-- id = 1
INSERT INTO mydb.users (`login`, `password`, `first_name`, `last_name`,`user_avatar`,`role_id`) VALUES( 'admin', '1000:b611cf3040307002c31109db4093d451:1cca30c19ffba3956648d64d8e54658c', 'Ivan', 'Ivanov','img_avatar.png', 0);
-- id = 2
INSERT INTO mydb.users ( `login`, `password`, `first_name`, `last_name`,`user_avatar`, `role_id`) VALUES( 'dispatcher', '1000:64a97d4d1df219fef845a62232b24a31:80350a875e642712c97e09b0b69b2249', 'Petr', 'Petrov','img_avatar2.png', 1);
-- id = 3
INSERT INTO mydb.users ( `login`, `password`, `first_name`, `last_name`,`user_avatar`, `role_id`) VALUES('driver', '1000:c1b719958a05ebb5d7e2d7eebd14c846:6e0c0eedf101f74a795b576bd32c109c', 'Petr', 'Petrov','img_avatar2.png', 2);
INSERT INTO mydb.users ( `login`, `password`, `first_name`, `last_name`,`user_avatar`, `role_id`) VALUES( 'driver2', '1000:c1b719958a05ebb5d7e2d7eebd14c846:6e0c0eedf101f74a795b576bd32c109c', 'Dima', 'Sidorov','img_avatar.png', 2);
INSERT INTO mydb.users ( `login`, `password`, `first_name`, `last_name`,`user_avatar`, `role_id`) VALUES( 'driver3', '1000:c1b719958a05ebb5d7e2d7eebd14c846:6e0c0eedf101f74a795b576bd32c109c', 'Igor', 'Pupkin','img_avatar2.png', 2);
INSERT INTO mydb.users ( `login`, `password`, `first_name`, `last_name`,`user_avatar`, `role_id`) VALUES( 'driver4', '1000:c1b719958a05ebb5d7e2d7eebd14c846:6e0c0eedf101f74a795b576bd32c109c', 'Senya', 'Sidorov','img_avatar2.png', 2);


DROP TABLE IF EXISTS mydb.`vehicles`;

CREATE TABLE mydb.`vehicles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(45) NOT NULL,
  `range` int(4) NOT NULL,
  `type` varchar(45) NOT NULL,
  status VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
--
-- Dumping data for table `vehicles`
--
INSERT INTO mydb.`vehicles` VALUES (1,'Audi A6',100,'sedan','ready'),(2,'Opel Astra',100,'hatchback','needs repair'),(3,'Opel Zafira',100,'sedan','ready'),(4,'Fiat Ducato',100,'hatchback','ready');



            
 DROP TABLE IF EXISTS mydb.requests;

CREATE TABLE mydb.`requests` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
 --  driver_id int(11)NOT NULL,
  `range` int(4) NOT NULL,
  `type` varchar(45) NOT NULL,
  `driver_id` int(11),
--   flights_id int(11) ,
   PRIMARY KEY (`request_id`),
  FOREIGN KEY (`driver_id`) REFERENCES `users` (`id`) 
  ON DELETE cascade 
--   FOREIGN KEY (`flights_id`) REFERENCES `flights` (`id`) 
--   ON DELETE NO ACTION ON UPDATE NO ACTION
--   FOREIGN KEY (country_id) REFERENCES countries(id)
) ENGINE=InnoDB;
--
-- Dumping data for table `requests`
--
INSERT INTO mydb.`requests` VALUES (1,100,'sedan',3),(2,100,'buss',3),(3,100,'sedan',3),(4,100,'buss',3),(5,100,'sedan',3),(6,100,'buss',3),(7,100,'sedan',3);

    
CREATE TABLE mydb.flights(
  id INTEGER NOT NULL auto_increment,
-- `id` INT(11) NOT NULL AUTO_INCREMENT,
name VARCHAR(32),
  date DATE NOT NULL,
  departure_point VARCHAR(45) NOT NULL,
  arrival_point VARCHAR(45) NOT NULL,
  driver_id int(11),
  vehicle_id int(11) ,
  request_id int(11) ,
  status VARCHAR(45) NOT NULL,
  
  FOREIGN KEY (`request_id`) REFERENCES `requests` (`request_id`) 
     ON update cascade ON DELETE cascade,
   FOREIGN KEY (`driver_id`) REFERENCES `users` (`id`) 
     ON DELETE cascade,
  FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`) 
   ON DELETE NO ACTION ON UPDATE NO ACTION,
--   FOREIGN KEY (country_id) REFERENCES countries(id)
PRIMARY KEY (`id`)
) ENGINE=InnoDB;

--
-- Dumping data for table `flights`
--

INSERT INTO mydb.flights ( name, date,departure_point,arrival_point, driver_id,vehicle_id,request_id,status)
  VALUES ('FirstFlght','2008-11-11','Kyiv','Kharkiv','3','1',1,'in progress');
 
  INSERT INTO mydb.flights ( name, date, departure_point,arrival_point,status)
 VALUES ('Second','2018-11-11','Odessa','Dnipro','open');
 
  INSERT INTO mydb.flights ( name, date,departure_point,arrival_point, request_id,status)
 VALUES ('Donetsk-Zaporizhia','2008-11-12','Donetsk','Zaporizhia',2,'open');
 INSERT INTO mydb.flights ( name, date,departure_point,arrival_point, driver_id,vehicle_id,request_id,status)
  VALUES ('Lviv-Mykolaiv','2008-11-11','Lviv','Mykolaiv','3','1',3,'in progress');
  INSERT INTO mydb.flights ( name, date,departure_point,arrival_point, driver_id,vehicle_id,request_id,status)
  VALUES ('Mariupol-Luhansk','2008-11-11','Mariupol','Luhansk','3','1',4,'in progress');

  INSERT INTO mydb.flights ( name, date, departure_point,arrival_point,status)
 VALUES ('Sevastopol-Vinnytsia','2018-11-11','Sevastopol','Vinnytsia','open');
 
  INSERT INTO mydb.flights ( name, date,departure_point,arrival_point, request_id,status)
 VALUES ('Makiivka-Simferopol','2008-11-12','Makiivka','Simferopol',5,'open');
 INSERT INTO mydb.flights ( name, date,departure_point,arrival_point, driver_id,vehicle_id,request_id,status)
  VALUES ('Kherson-Poltava','2008-11-11','Kherson','Poltava','3','1',6,'in progress');
  INSERT INTO mydb.flights ( name, date,departure_point,arrival_point, driver_id,vehicle_id,request_id,status)
  VALUES ('Chernihiv-Cherkasy','2008-11-11','Chernihiv','Cherkasy','3','1',7,'in progress');
