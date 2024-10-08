USE savvis2_write;

CREATE TABLE IF NOT EXISTS Employee(
     id int(20) NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);
INSERT  INTO Employee(name) VALUES('Lucky');
INSERT  INTO Employee(name) VALUES('Surya');

