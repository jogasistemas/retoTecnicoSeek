CREATE TABLE IF NOT EXISTS `candidate_management`.`candidate` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(80) NULL DEFAULT NULL,
  `experience` INT NOT NULL,
  `fullname` VARCHAR(80) NULL DEFAULT NULL,
  `gender` VARCHAR(15) NULL DEFAULT NULL,
  `salary` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- insert candidate
-- -----------------------------------------------------
INSERT INTO candidate (fullname, email, gender, salary, experience) VALUES
('Luis Garcia', 'luis.rivera@hotmail.com', 'Male', 2000.00, 3),
('Maria Rivera', 'maria.smith@hotmail.com', 'Female', 2500.00, 4),
('Lucia Terrafon', 'lucia.terrafon@hotmail.com', 'Female', 4000.00, 6),
('Java Terra', 'java.terra@hotmail.com', 'Male', 48000.00, 2),
('Juan Loreto', 'juan.loreto@hotmail.com', 'Male', 1800.00, 5);


CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS user_authorities (
    user_id BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, authority_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (authority_id) REFERENCES authorities (id) ON DELETE CASCADE
);

INSERT INTO users (username, password, enabled) VALUES
('user1', '$2a$10$DowJHfnwzjTsmPQi5sQe2e7YXZcFT8mfAXmjRv6GB1eUn6sWxDioO', true), -- password: password
('admin', '$2a$10$DowJHfnwzjTsmPQi5sQe2e7YXZcFT8mfAXmjRv6GB1eUn6sWxDioO', true); -- password: password

INSERT INTO authorities (name) VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO user_authorities (user_id, authority_id) VALUES
(1, 1), -- user1 has ROLE_USER
(2, 1), -- admin has ROLE_USER
(2, 2); -- admin has ROLE_ADMIN
