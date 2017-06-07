DROP DATABASE IF EXISTS payment_engine;

CREATE DATABASE payment_engine
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
 DROP TABLE IF EXISTS payment_request;
 
 CREATE TABLE payment_request (
 	payment_request_id UUID PRIMARY KEY,
 	creation_date TIMESTAMP DEFAULT now() NOT NULL,
 	reference VARCHAR(64),
 	value  NUMERIC(10,2)
 );