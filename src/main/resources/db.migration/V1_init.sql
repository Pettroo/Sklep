-- TYPES

CREATE TYPE sizes_enum AS ENUM('36', '37', '38', '39', '40', '41', '42', '43', '44', '45', '46', '47', '48');
CREATE TYPE statuses_enum AS ENUM('Oczekujące', 'Zrealizowane', 'Anulowane');

-- SCHEMAS

CREATE SCHEMA s_users;
CREATE SCHEMA s_products;
CREATE SCHEMA s_orders;

-- TABLES

CREATE TABLE roles (
	id SERIAL UNIQUE,
	name varchar(40),
	role_code varchar(40) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE users (
	id SERIAL UNIQUE,
	forename varchar(40),
	name varchar (40),
	login varchar(40) NOT NULL,
	password varchar(40) NOT NULL,	
	role Integer NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (role) REFERENCES roles (id)
);

CREATE TABLE shoes (
	id SERIAL UNIQUE,
	name varchar(40) NOT NULL,
	value Double Precision NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE products (
	id SERIAL UNIQUE,
	shoe_id Integer NOT NULL,
	size sizes_enum NOT NULL,
	available_status boolean NOT NULL,
	PRIMARY KEY (shoe_id, size),
	FOREIGN KEY (shoe_id) REFERENCES shoes (id)
);

CREATE TABLE orders (
	id SERIAL UNIQUE,
	date DATE,
	status statuses_enum NOT NULL,
	customer Integer NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (customer) REFERENCES users (id)
);

CREATE TABLE orders_positions (
	id SERIAL UNIQUE,
	order_id Integer NOT NULL,
	product_id Integer NOT NULL,
	quantity Integer NOT NULL,
	single_price Double Precision NOT NULL,
	PRIMARY KEY (order_id, product_id),
	FOREIGN KEY (order_id) REFERENCES orders (id),
	FOREIGN KEY (product_id) REFERENCES products (id)
);

-- PROCEDURES

CREATE OR REPLACE PROCEDURE s_users.add_role(new_name varchar(40), new_code varchar(40))
AS
$BODY$
DECLARE
BEGIN
	INSERT INTO roles (name, role_code)
	VALUES (new_name, new_code);
END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_users.add_user(
	login varchar(40),
	password varchar(40),
	forename varchar(40) DEFAULT NULL,
	name varchar(40) DEFAULT NULL)
AS
$BODY$
DECLARE
	user_role_id Integer;
BEGIN
	Select id
	INTO user_role_id
	FROM roles
	WHERE role_code = 'USER' ;

	INSERT INTO users (forename, name, login, password, role)
	VALUES (forename, name, login, password, user_role_id);
END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_users.set_user_forename(
	id Integer,
	forename varchar(40))
AS
$BODY$
DECLARE
BEGIN
	UPDATE users
		SET forename = set_user_forename.forename
		WHERE users.id = set_user_forename.id;
END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_users.set_user_name(
	id Integer,
	name varchar(40))
AS
$BODY$
DECLARE
BEGIN
	UPDATE users
		SET name = set_user_name.name
		WHERE users.id = set_user_name.id;
END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_users.set_user_password(
	id Integer,
	password varchar(40))
AS
$BODY$
DECLARE
BEGIN
	UPDATE users
		SET password = set_user_password.password
		WHERE users.id = set_user_password.id;
END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_users.set_user_role(
	id Integer,
	code varchar(40))
AS
$BODY$
DECLARE
	role_id Integer;
BEGIN
	SELECT roles.id
	INTO role_id
	FROM roles
	WHERE role_code = code;

	UPDATE users
		SET role = role_id
		WHERE users.id = set_user_role.id;
END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_products.add_product(p_shoe_id Integer, product_size sizes_enum)
AS
$BODY$
DECLARE
	p_id Integer;
BEGIN
	INSERT INTO products (shoe_id, size, available_status)
	VALUES (p_shoe_id, product_size::sizes_enum, true);
END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_products.add_shoe(p_name varchar(40), p_value Double Precision)
AS
$BODY$
DECLARE
	c_sizes CURSOR FOR
		SELECT unnest(enum_range(NULL::sizes_enum)) size;
	
	r_sizes RECORD;
	
	p_id Integer;
BEGIN
	INSERT INTO shoes (name, value)
	VALUES (p_name, p_value);
	
	SELECT id
	INTO p_id
	FROM shoes
	WHERE shoes.name = p_name
		AND shoes.value = p_value;

	OPEN c_sizes;
	LOOP
		fetch c_sizes into r_sizes;
		EXIT WHEN NOT FOUND;
		
		CALL s_products.add_product(p_id, r_sizes.size);
		
	END LOOP;
	CLOSE c_sizes;
		
END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_products.set_shoe_name(
	p_id Integer,
	p_name varchar(40))
AS
$BODY$
DECLARE
BEGIN
	UPDATE shoes
		SET name = p_name
		WHERE id = p_id;
END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_products.set_shoe_value(
	p_id Integer,
	p_value Double Precision)
AS
$BODY$
DECLARE
BEGIN
	UPDATE shoes
		SET value = p_value
		WHERE id = p_id;
END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_products.set_product_available(
	p_id Integer)
AS
$BODY$
DECLARE
	p_status boolean;
BEGIN
	SELECT available_status
	INTO p_status
	FROM products
	WHERE products.id = p_id;

	IF p_status
	THEN
		UPDATE products
			SET available_status = false
			WHERE id = p_id;
	ELSE
		UPDATE products
			SET available_status = true
			WHERE id = p_id;
	END IF;
END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION s_orders.add_order(p_user_id Integer) RETURNS Integer
AS
$BODY$
DECLARE
	p_order_id Integer;
BEGIN
	INSERT INTO orders (status, date, customer)
	VALUES ('Oczekujące'::statuses_enum, current_date, p_user_id);
	
	SELECT MAX(id)
	INTO p_order_id
	FROM orders
	WHERE orders.customer = p_user_id
		AND status = 'Oczekujące';
	
	RETURN p_order_id;
END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_orders.add_order_position(p_order_id Integer, p_product_id Integer, p_quantity Integer)
AS
$BODY$
DECLARE
	p_single_price Double Precision;
	p_update Integer;
BEGIN
	SELECT count(*)
	INTO p_update
	FROM orders_positions
	WHERE order_id = p_order_id
		AND product_id = p_product_id;
	
	IF p_update = 0
	THEN
		SELECT value
		INTO p_single_price
		FROM products
		JOIN shoes
			ON shoes.id = products.shoe_id
		WHERE products.id = p_product_id;	

		INSERT INTO orders_positions (order_id, product_id, quantity, single_price)
		VALUES (p_order_id, p_product_id, p_quantity, p_single_price);
	ELSE
		UPDATE orders_positions
			SET quantity = quantity + p_quantity
			WHERE order_id = p_order_id
				AND product_id = p_product_id;
	END IF;

END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_orders.delete_order(p_order_id Integer)
AS
$BODY$
DECLARE
BEGIN

	DELETE FROM orders_positions
	WHERE orders_positions.order_id = p_order_id;
	
	DELETE FROM orders
	WHERE orders.id = p_order_id;	

END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_orders.delete_order_positions(p_order_postion_id Integer)
AS
$BODY$
DECLARE
BEGIN

	DELETE FROM orders_positions
	WHERE orders_positions.id = p_order_postion_id;

END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_orders.set_order_status(p_order_id Integer, p_status varchar(40))
AS
$BODY$
DECLARE
BEGIN
	UPDATE orders
		SET status = p_status::statuses_enum
		WHERE orders.id = p_order_id;

END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE s_orders.set_position_quantity(p_position_id Integer, p_quantity Double precision)
AS
$BODY$
DECLARE
BEGIN
	UPDATE orders_positions
		SET quantity = p_quantity
		WHERE orders_positions.id = p_position_id;

END;
$BODY$
LANGUAGE plpgsql;

-----------------------------------------------------------------------------------------------

CALL s_users.add_role('Menadżer', 'MANAGER');
CALL s_users.add_role('Pracownik', 'EMPLOYE');
CALL s_users.add_role('Użytkownik', 'USER');

-----------------------------------------------------------------------------------------------

CALL s_users.add_user('manager', 'manager', 'Jan', 'Kowalski');
CALL s_users.add_user('employe', 'employe', 'Adam', 'Nowak');
CALL s_users.add_user('user1', 'user1', 'Anna', 'Nowak');
CALL s_users.add_user('user2', 'user2', 'Bogusław', 'Tester');
CALL s_users.add_user('user3', 'user3', 'Andrzej', 'Bazdan');
CALL s_users.add_user('user4', 'user4', 'Michał', 'Michalski');

CALL s_users.set_user_role(1, 'MANAGER');
CALL s_users.set_user_role(2, 'EMPLOYE');

-----------------------------------------------------------------------------------------------

CALL s_products.add_shoe('Sandały Apollo', 59.99);
CALL s_products.add_shoe('Kozaki zimowe', 99.99);
CALL s_products.add_shoe('Klapki męski', 19.99);
CALL s_products.add_shoe('Klapki damskie', 19.99);
CALL s_products.add_shoe('Tenisówki', 39.99);
CALL s_products.add_shoe('Cichobiegi', 199.99);
CALL s_products.add_shoe('Trampki', 20.99);

-----------------------------------------------------------------------------------------------

CALL s_products.set_product_available(1);
CALL s_products.set_product_available(4);
CALL s_products.set_product_available(8);
CALL s_products.set_product_available(12);
CALL s_products.set_product_available(15);
CALL s_products.set_product_available(23);
CALL s_products.set_product_available(29);
CALL s_products.set_product_available(30);
CALL s_products.set_product_available(31);
CALL s_products.set_product_available(39);
CALL s_products.set_product_available(46);
CALL s_products.set_product_available(49);
CALL s_products.set_product_available(52);
CALL s_products.set_product_available(59);
CALL s_products.set_product_available(62);
CALL s_products.set_product_available(68);
CALL s_products.set_product_available(73);
CALL s_products.set_product_available(78);
CALL s_products.set_product_available(79);
CALL s_products.set_product_available(82);
CALL s_products.set_product_available(86);
CALL s_products.set_product_available(91);

-----------------------------------------------------------------------------------------------

SELECT s_orders.add_order(3);
SELECT s_orders.add_order(4);
SELECT s_orders.add_order(5);
SELECT s_orders.add_order(6);

-----------------------------------------------------------------------------------------------

CALL s_orders.add_order_position(1, 1, 10);
CALL s_orders.add_order_position(1, 10, 2);
CALL s_orders.add_order_position(1, 54, 1);
CALL s_orders.add_order_position(1, 39, 4);

CALL s_orders.add_order_position(2, 23, 1);
CALL s_orders.add_order_position(2, 56, 3);
CALL s_orders.add_order_position(2, 87, 7);
CALL s_orders.add_order_position(2, 13, 1);
CALL s_orders.add_order_position(2, 51, 8);
CALL s_orders.add_order_position(2, 11, 2);

CALL s_orders.add_order_position(3, 12, 2);
CALL s_orders.add_order_position(3, 16, 1);
CALL s_orders.add_order_position(3, 37, 5);
CALL s_orders.add_order_position(3, 89, 4);
CALL s_orders.add_order_position(3, 24, 6);
CALL s_orders.add_order_position(3, 65, 1);
CALL s_orders.add_order_position(3, 14, 2);
CALL s_orders.add_order_position(3, 87, 2);

CALL s_orders.add_order_position(4, 12, 13);
CALL s_orders.add_order_position(4, 14, 1);
CALL s_orders.add_order_position(4, 16, 10);
CALL s_orders.add_order_position(4, 34, 1);
CALL s_orders.add_order_position(4, 62, 8);
CALL s_orders.add_order_position(4, 81, 1);
CALL s_orders.add_order_position(4, 12, 3);

-----------------------------------------------------------------------------------------------

CALL s_orders.set_order_status(1, 'Anulowane');

-----------------------------------------------------------------------------------------------

SELECT s_orders.add_order(3);

CALL s_orders.add_order_position(5, 14, 10);
CALL s_orders.add_order_position(5, 4, 2);
CALL s_orders.add_order_position(5, 64, 1);
CALL s_orders.add_order_position(5, 72, 4);

-----------------------------------------------------------------------------------------------