CREATE TABLE product (
	id INTEGER NOT NULL PRIMARY KEY,
	description varchar(255),
	price decimal(15,2)
);

CREATE INDEX product_description on product(description);