CREATE SEQUENCE client_id_generator;

CREATE TABLE client(
	id INTEGER  DEFAULT client_id_generator.nextval,
	email VARCHAR(50) NOT NULL,
	name VARCHAR(50) NOT NULL,
	birth_date DATE NOT NULL,
	phone CHAR(11),
	sex CHAR(1) CHECK(sex = 'M' OR sex = 'F' OR sex = '?'),
	PRIMARY KEY(id),
	UNIQUE(email)
);