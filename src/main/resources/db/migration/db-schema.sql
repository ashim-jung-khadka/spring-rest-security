--liquibase formatted sql

-- -----------------------------------------------------
-- Table app_user
-- -----------------------------------------------------
-- changeset ashim:db_app_user
CREATE TABLE `app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
);
-- rollback drop table app_user;

-- -----------------------------------------------------
-- Table user_profile
-- -----------------------------------------------------
-- changeset ashim:db_user_profile
CREATE TABLE `user_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profile_type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `profile_type` (`profile_type`)
);
-- rollback drop table user_profile;

-- -----------------------------------------------------
-- Table app_user_user_profile
-- -----------------------------------------------------
-- changeset ashim:db_app_user_user_profile
CREATE TABLE `app_user_user_profile` (
  `user_id` int(11) NOT NULL,
  `user_profile_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`user_profile_id`),
  KEY `FK_USER_PROFILE_ID` (`user_profile_id`),
  CONSTRAINT `FK_APP_USER` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK_USER_PROFILE` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`)
);
-- rollback drop table app_user_user_profile;