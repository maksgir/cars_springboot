CREATE SEQUENCE IF NOT EXISTS seq_person START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS seq_car START WITH 1 INCREMENT BY 1;


CREATE TABLE IF NOT EXISTS "public"."people" (
    id INT DEFAULT nextval('seq_person') NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    birth_date DATE NOT NULL
    );

CREATE TABLE IF NOT EXISTS "public"."cars" (
    id INT DEFAULT nextval('seq_car') NOT NULL PRIMARY KEY,
    model VARCHAR(20) NOT NULL,
    horse_power INT NOT NULL,
    owner_id INT NOT NULL,
    CONSTRAINT fk_owner
    FOREIGN KEY(owner_id)
    REFERENCES people(id)
    );

CREATE TABLE IF NOT EXISTS "public"."vendors" (
    name VARCHAR(20) NOT NULL PRIMARY KEY
    )
