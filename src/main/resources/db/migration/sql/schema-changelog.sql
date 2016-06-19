--liquibase formatted sql

-- -----------------------------------------------------
-- Table app_user
-- -----------------------------------------------------
-- changeset ashim:tbl_app_user
	CREATE TABLE app_user (
		id int(11) NOT NULL AUTO_INCREMENT,
		user_name varchar(30) NOT NULL,
		password varchar(100) NOT NULL,
		first_name varchar(30) NOT NULL,
		last_name varchar(30) NOT NULL,
		email varchar(30) NOT NULL,
		status varchar(30) NOT NULL,
		PRIMARY KEY (id),
		UNIQUE KEY user_name (user_name)
	);
-- rollback drop table app_user;

-- -----------------------------------------------------
-- Table app_user
-- -----------------------------------------------------
-- changeset ashim:ins_app_user
	INSERT INTO app_user(user_name, password, first_name, last_name, email, status)
	VALUES ('ashim','$2a$10$0rrM2oy2RMKoJ4T5PpaZruCfN3sBab8sS6TEKbwXRawE0ml.DJ.CS', 'Ashim','Khadka','ashim@gmail.com', 'active');
	
	INSERT INTO app_user(user_name, password, first_name, last_name, email, status)
	VALUES ('niraj','$2a$10$0rrM2oy2RMKoJ4T5PpaZruCfN3sBab8sS6TEKbwXRawE0ml.DJ.CS', 'Niraj','lama','niraj@gmail.com', 'active');
	
	INSERT INTO app_user(user_name, password, first_name, last_name, email, status)
	VALUES ('sumit','$2a$10$0rrM2oy2RMKoJ4T5PpaZruCfN3sBab8sS6TEKbwXRawE0ml.DJ.CS', 'Sumit','Kc','sumit@gmail.com', 'active');
	
	INSERT INTO app_user(user_name, password, first_name, last_name, email, status)
	VALUES ('pukar','$2a$10$0rrM2oy2RMKoJ4T5PpaZruCfN3sBab8sS6TEKbwXRawE0ml.DJ.CS', 'Pukar','Thapaliya','pukar@gmail.com', 'inactive');
	
	INSERT INTO app_user(user_name, password, first_name, last_name, email, status)
	VALUES ('pawan','$2a$10$0rrM2oy2RMKoJ4T5PpaZruCfN3sBab8sS6TEKbwXRawE0ml.DJ.CS', 'Pawan','Maharjan','pawan@gmail.com', 'inactive');
-- rollback TRUNCATE TABLE app_user;

-- -----------------------------------------------------
-- Table user_profile
-- -----------------------------------------------------
-- changeset ashim:tbl_user_profile
	CREATE TABLE user_profile (
		id int(11) NOT NULL AUTO_INCREMENT,
		profile_type varchar(30) NOT NULL,
		PRIMARY KEY (id),
		UNIQUE KEY profile_type (profile_type)
	);
-- rollback drop table user_profile;

-- -----------------------------------------------------
-- Table user_profile
-- -----------------------------------------------------
-- changeset ashim:ins_user_profile
	INSERT INTO user_profile(profile_type)
	VALUES ('ADMIN');
	
	INSERT INTO user_profile(profile_type)
	VALUES ('USER');
-- rollback TRUNCATE TABLE user_profile;

-- -----------------------------------------------------
-- Table app_user_user_profile
-- -----------------------------------------------------
-- changeset ashim:tbl_app_user_user_profile
	CREATE TABLE app_user_user_profile (
		user_id int(11) NOT NULL,
		user_profile_id int(11) NOT NULL,
		PRIMARY KEY (user_id,user_profile_id),
		KEY FK_USER_PROFILE_ID (user_profile_id)
	);
-- rollback DROP TABLE app_user_user_profile;

-- -----------------------------------------------------
-- Table app_user_user_profile
-- -----------------------------------------------------
-- changeset ashim:ins_app_user_user_profile
	INSERT INTO app_user_user_profile (user_id, user_profile_id)
	SELECT user.id, profile.id FROM app_user user, user_profile profile
		WHERE user.user_name='ashim' AND profile.profile_type='ADMIN';
	
	INSERT INTO app_user_user_profile (user_id, user_profile_id)
	SELECT user.id, profile.id FROM app_user user, user_profile profile
		WHERE user.user_name='niraj' AND profile.profile_type='ADMIN';
	
	INSERT INTO app_user_user_profile (user_id, user_profile_id)
	SELECT user.id, profile.id FROM app_user user, user_profile profile
		WHERE user.user_name='sumit' AND profile.profile_type='USER';
	
	INSERT INTO app_user_user_profile (user_id, user_profile_id)
	SELECT user.id, profile.id FROM app_user user, user_profile profile
		WHERE user.user_name='pukar' AND profile.profile_type='USER';
	
	INSERT INTO app_user_user_profile (user_id, user_profile_id)
	SELECT user.id, profile.id FROM app_user user, user_profile profile
		WHERE user.user_name='pawan' AND profile.profile_type='USER';
-- rollback TRUNCATE TABLE app_user_user_profile;

-- -----------------------------------------------------
-- Table app_user_user_profile
-- -----------------------------------------------------
-- changeset ashim:app_user_user_profile_foreign_user_id
	ALTER TABLE app_user_user_profile ADD CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES app_user (id);
-- rollback ALTER TABLE app_user_user_profile DROP FOREIGN KEY FK_APP_USER;

-- -----------------------------------------------------
-- Table app_user_user_profile
-- -----------------------------------------------------
-- changeset ashim:app_user_user_profile_foreign_user_profile_id
	ALTER TABLE app_user_user_profile ADD CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES user_profile (id);
-- rollback ALTER TABLE app_user_user_profile DROP FOREIGN KEY FK_USER_PROFILE;