CREATE DATABASE IF NOT EXISTS person;
USE person;

DROP TABLE IF EXISTS info;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS status;


CREATE TABLE info (
    login VARCHAR(10) PRIMARY KEY,
    password VARCHAR(10),
    name VARCHAR(10),
    surname VARCHAR(10)
);

CREATE TABLE message (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender VARCHAR(10),
    getter VARCHAR(10),
    text VARCHAR(64),
    FOREIGN KEY (sender) REFERENCES info(login),
    FOREIGN KEY (getter) REFERENCES info(login)
);

CREATE TABLE status (
    user_login VARCHAR(10) PRIMARY KEY,
    status ENUM('online', 'offline'),
    last_changed VARCHAR(15),
    FOREIGN KEY (user_login) REFERENCES info(login)
);