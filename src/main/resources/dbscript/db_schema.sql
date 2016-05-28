-- -----------------------------------------------------
-- Table app_user
-- -----------------------------------------------------
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user
  (
     id         INT NOT NULL auto_increment,
     user_name  VARCHAR(30) NOT NULL,
     password   VARCHAR(100) NOT NULL,
     first_name VARCHAR(30) NOT NULL,
     last_name  VARCHAR(30) NOT NULL,
     email      VARCHAR(30) NOT NULL,
     status     VARCHAR(30) NOT NULL,
     PRIMARY KEY (id),
     UNIQUE (user_name)
  );

-- -----------------------------------------------------
-- Table user_profile
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_profile;

CREATE TABLE user_profile
  (
     id           INT NOT NULL auto_increment,
     profile_type VARCHAR(30) NOT NULL,
     PRIMARY KEY (id),
     UNIQUE (profile_type)
  );

-- -----------------------------------------------------
-- Table app_user_user_profile
-- -----------------------------------------------------
DROP TABLE IF EXISTS app_user_user_profile;

CREATE TABLE app_user_user_profile
  (
     user_id         INT NOT NULL,
     user_profile_id INT NOT NULL,
     PRIMARY KEY (user_id, user_profile_id)
  );