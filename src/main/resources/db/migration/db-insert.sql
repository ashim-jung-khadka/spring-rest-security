-- liquibase formatted sql

-- -----------------------------------------------------
-- Table app_user
-- -----------------------------------------------------
-- changeset ashim:db_app_user
INSERT INTO app_user(user_name, password, first_name, last_name, email, status)
VALUES ('ashim','$2a$10$0rrM2oy2RMKoJ4T5PpaZruCfN3sBab8sS6TEKbwXRawE0ml.DJ.CS', 'Ashim','Khadka','ashim.jung.khadka@gmail.com', 'active');
-- rollback DELETE FROM app_user WHERE user_name = 'ashim';

-- -----------------------------------------------------
-- Table user_profile
-- -----------------------------------------------------
-- changeset ashim:db_user_profile
INSERT INTO user_profile(profile_type)
VALUES ('ADMIN');

INSERT INTO user_profile(profile_type)
VALUES ('USER');
-- rollback DELETE FROM user_profile WHERE profile_type IN ('ADMIN', 'USER');

-- -----------------------------------------------------
-- Table app_user_user_profile
-- -----------------------------------------------------
-- changeset ashim:db_app_user_user_profile
INSERT INTO app_user_user_profile (user_id, user_profile_id)
SELECT user.id, profile.id FROM app_user user, user_profile profile
  WHERE user.user_name='ashim' AND profile.profile_type='ADMIN';

-- rollback DELETE FROM app_user_user_profile WHERE user_id = (SELECT id FROM app_user WHERE user_name = 'ashim') AND user_profile_id = (SELECT id FROM user_profile WHERE profile_type = 'ADMIN');