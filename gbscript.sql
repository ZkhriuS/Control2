CREATE DATABASE human_friends;
USE human_friends;

CREATE TABLE pets (
id INT PRIMARY KEY AUTO_INCREMENT,
pet_name char(20),
commands TEXT,
birthday DATE);

CREATE TABLE pack_animals (
id INT PRIMARY KEY AUTO_INCREMENT,
pet_name char(20),
commands TEXT,
birthday DATE);

CREATE TABLE cat (
id INT PRIMARY KEY AUTO_INCREMENT,
pet_name char(20),
commands TEXT,
birthday DATE);

CREATE TABLE dog (
id INT PRIMARY KEY AUTO_INCREMENT,
pet_name char(20),
commands TEXT,
birthday DATE);

CREATE TABLE humster (
id INT PRIMARY KEY AUTO_INCREMENT,
pet_name char(20),
commands TEXT,
birthday DATE);

CREATE TABLE horse (
id INT PRIMARY KEY AUTO_INCREMENT,
pet_name char(20),
commands TEXT,
birthday DATE);

CREATE TABLE camel (
id INT PRIMARY KEY AUTO_INCREMENT,
pet_name char(20),
commands TEXT,
birthday DATE);

CREATE TABLE donkey (
id INT PRIMARY KEY AUTO_INCREMENT,
pet_name char(20),
commands TEXT,
birthday DATE);

INSERT INTO cat (pet_name, commands, birthday) VALUES 
	('barsik', 'meow', '2021-01-01'),
	('kosh', 'meow, stand', '2019-12-10'),
    ('frank', 'meow, wlow', '2020-02-02'),
    ('dir', 'meow', '2022-03-03'),
    ('krop', 'meow', '2018-05-05');
   
INSERT INTO dog (pet_name, commands, birthday) VALUES 
	('wolf', 'flufy', '2021-01-01'),
	('ralf', 'site', '2019-12-10'),
    ('qwerty', 'left hand', '2020-02-02'),
    ('asdfg', 'right hand', '2022-03-03'),
    ('red', 'meow', '2018-05-05');
    
INSERT INTO humster (pet_name, commands, birthday) VALUES 
	('crack', 'eat', '2021-01-01'),
	('jisus', 'eat', '2019-12-10'),
    ('qwerty', 'left hand', '2020-02-02'),
    ('asdfg', 'right hand', '2022-03-03'),
    ('black', 'meow', '2018-05-05');
    
INSERT INTO horse (pet_name, commands, birthday) VALUES 
	('igogo', 'eat', '2021-01-01'),
	('igaga', 'eat', '2019-12-10'),
    ('ijoho', 'left hand', '2020-02-02'),
    ('jijij', 'right hand', '2022-03-03'),
    ('aoaoaa', 'meow', '2018-05-05');
    
INSERT INTO camel (pet_name, commands, birthday) VALUES 
	('qwea', 'eat', '2021-01-01'),
	('dsaf', 'eat', '2019-12-10'),
    ('gfdsdf', 'left hand', '2020-02-02'),
    ('grwtw', 'right hand', '2022-03-03'),
    ('werqr', 'meow', '2018-05-05');
    
INSERT INTO donkey (pet_name, commands, birthday) VALUES 
	('stup', 'eat', '2021-01-01'),
	('tupi', 'eat', '2019-12-10'),
    ('upid', 'left hand', '2020-02-02'),
    ('task', 'right hand', '2022-03-03'),
    ('done', 'meow', '2018-05-05');
    
    SELECT * 
    FROM cat;
    
    SELECT * 
    FROM dog;
    
    SELECT * 
    FROM humster;
    
    SELECT * 
    FROM horse;
    
    SELECT * 
    FROM camel;
    
    SELECT * 
    FROM donkey;
    
    DELETE FROM camel;
    
    INSERT INTO pack_animals (pet_name, commands, birthday)
    SELECT pet_name, commands, birthday
    FROM horse
    UNION
    SELECT pet_name, commands, birthday
    FROM donkey;
    
    INSERT INTO pets (pet_name, commands, birthday)
    SELECT pet_name, commands, birthday
    FROM cat
    UNION
    SELECT pet_name, commands, birthday
    FROM dog
    UNION
    SELECT pet_name, commands, birthday
    FROM humster;
    
    CREATE TABLE young_animal (
    id INT PRIMARY KEY AUTO_INCREMENT,
	pet_name char(20),
	commands TEXT,
	birthday DATE,
    age TEXT);
    
DELIMITER $$
CREATE FUNCTION age_animal (date_b DATE)
RETURNS TEXT
DETERMINISTIC
BEGIN
    DECLARE res TEXT DEFAULT '';
	SET res = CONCAT(
            TIMESTAMPDIFF(YEAR, date_b, CURDATE()),
            ' years ',
            TIMESTAMPDIFF(MONTH, date_b, CURDATE()) % 12,
            ' month'
        );
	RETURN res;
END $$
DELIMITER ;

INSERT INTO young_animal (pet_name, commands, birthday, age)
SELECT pet_name, commands, birthday, age_animal(birthday)
FROM cat
WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 1 AND 3
UNION ALL
SELECT pet_name, commands, birthday, age_animal(birthday)
FROM dog
WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 1 AND 3
UNION ALL
SELECT pet_name, commands, birthday, age_animal(birthday)
FROM humster
WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 1 AND 3
UNION ALL
SELECT pet_name, commands, birthday, age_animal(birthday)
FROM horse
WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 1 AND 3
UNION ALL
SELECT pet_name, commands, birthday, age_animal(birthday)
FROM donkey
WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 1 AND 3;

DROP TABLE animals;

CREATE TABLE animals (
	id INT PRIMARY KEY AUTO_INCREMENT,
	pet_name CHAR(30),
    commands TEXT,
    birthday DATE,
    age TEXT,
    pet_type ENUM('cat', 'dog', 'humster', 'horse', 'donkey') NOT NULL
);

INSERT INTO animals (pet_name, commands, birthday, age, pet_type)
SELECT pet_name, commands, birthday, age_animal(birthday), 'cat'
FROM cat
UNION ALL
SELECT pet_name, commands, birthday, age_animal(birthday), 'dog'
FROM dog
UNION ALL
SELECT pet_name, commands, birthday, age_animal(birthday), 'humster'
FROM humster
UNION ALL
SELECT pet_name, commands, birthday, age_animal(birthday), 'horse'
FROM horse
UNION ALL
SELECT pet_name, commands, birthday, age_animal(birthday), 'donkey'
FROM donkey;
