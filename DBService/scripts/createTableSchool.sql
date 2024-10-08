

USE savvis2_read;
CREATE TABLE IF NOT EXISTS School(
     id int(20) NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

INSERT  INTO School(name) VALUES('Upkssv');
INSERT  INTO School(name) VALUES('Vsec');