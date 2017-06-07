# Create the keyspace
CREATE KEYSPACE IF NOT EXISTS movilred WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE  movilred;

# Create the table
CREATE TABLE  IF NOT EXISTS movilred.file (
  id timeuuid PRIMARY KEY,
  content text
);

