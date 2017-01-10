DROP SCHEMA library;
CREATE SCHEMA if not exists library;

CREATE USER if not exists 'library_admin'@'localhost' IDENTIFIED BY 'library_admin_pass';
GRANT ALL privileges on library.* to 'library_admin'@'localhost';
#comment


Drop table if exists  `library`.`library_users`;
CREATE TABLE `library`.`library_users` (
  `uuid` VARCHAR(80) NOT NULL,
  `name` VARCHAR(45) NOT NULL UNIQUE,
  `user_type` TINYINT(1),
  `loyality_index` INT(2),
   `password` VARCHAR(80),
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC)); 
  
  
   Drop table if exists  `library`.`authors`;
   CREATE TABLE `library`.`authors` (
  `uuid` VARCHAR(80) NOT NULL,
  `name` VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC));
  
	Drop table if exists `library`.`publication_type`;
CREATE TABLE `library`.`publication_type` (
`id` INT(5) NOT NULL,
`name` VARCHAR(45) NOT NULL UNIQUE,
PRIMARY KEY (`id`));

 Drop table if exists  `library`.`publications`;
  CREATE TABLE `library`.`publications` (
  `uuid` VARCHAR(80) NOT NULL,
  `title` VARCHAR(45) NOT NULL UNIQUE,
  `publisher` VARCHAR(45),  
  `release_date` INT(5),
  `nr_of_copies` INT(5),
  `copies_left` INT(5),
  `type` INT(5),
  PRIMARY KEY (`uuid`),
  FOREIGN KEY (type) 
	REFERENCES `publication_type`(id)
    ON DELETE CASCADE,
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC)); 
  
  Drop table if exists  `library`.`publications_authors`;
  CREATE TABLE `library`.`publications_authors` (
  `publications_uuid` VARCHAR(80) NOT NULL,
  `authors_uuid` VARCHAR(80) NOT NULL, 
PRIMARY KEY (`publications_uuid`,`authors_uuid`),
FOREIGN KEY (publications_uuid) 
	REFERENCES `publications`(uuid)
    ON DELETE CASCADE,
FOREIGN KEY (authors_uuid) 
	REFERENCES `authors`(uuid)
    ON DELETE CASCADE); 

Drop table if exists `library`.`publication_borrowings`;
create table `library`.`publication_borrowings`(
`publications_uuid` VARCHAR(80) NOT NULL,
`user_uuid` VARCHAR(80) NOT NULL,
`uuid` VARCHAR(80) NOT NULL,
`borrowing_date` DATE,
`deadline` DATE,
PRIMARY KEY (`publications_uuid`,`uuid`),
FOREIGN KEY (publications_uuid) 
	REFERENCES `publications`(uuid)
    ON DELETE CASCADE,
FOREIGN KEY (user_uuid) 
	REFERENCES `library_users`(uuid)
    ON DELETE CASCADE); 

