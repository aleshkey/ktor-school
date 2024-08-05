create database students_db;
\connect students_db
CREATE TABLE streams (
                         id INT PRIMARY KEY,
                         name VARCHAR(255)
);

CREATE TABLE students (
                          id BIGINT PRIMARY KEY,
                          first_name VARCHAR(255),
                          last_name VARCHAR(255),
                          email VARCHAR(255),
                          age INT,
                          stream_id INT,
                          FOREIGN KEY (stream_id) REFERENCES streams(id)
);
