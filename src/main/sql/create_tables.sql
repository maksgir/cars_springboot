

CREATE TABLE people (
    id int NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    birth_date DATE NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS cars (
    id int NOT NULL AUTO_INCREMENT,
    model VARCHAR(20) NOT NULL,
    horse_power INT NOT NULL,
    owner_id INT NOT NULL,
    FOREIGN KEY(owner_id)
    REFERENCES people(id)
    );

CREATE TABLE IF NOT EXISTS vendors (
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY (name)
    )

CREATE TABLE employees (
                           id int NOT NULL AUTO_INCREMENT,
                           name varchar(15),
                           surname varchar(25),
                           department varchar(20),
                           salary int,
                           PRIMARY KEY (id)
) ;