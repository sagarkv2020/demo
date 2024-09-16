DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  salary INT(20) NOT NULL,
  age INT(3) NOT NULL,
  profile_image VARCHAR(250) DEFAULT NULL,
  created_at TIMESTAMP NOT NULL,
  created_by VARCHAR(250) NOT NULL,
  modified_at TIMESTAMP DEFAULT NULL,
  modified_by VARCHAR(250) DEFAULT NULL,
  is_active BOOLEAN DEFAULT true
);

INSERT INTO employee (id, name, salary, age, created_at, created_by) VALUES
  (1001, 'Amol Tewari', 123456, 30, CURRENT_TIMESTAMP, 'Amol Tewari'),
  (1002, 'Chandra Bhushan', 3456781, 28, CURRENT_TIMESTAMP, 'Chandra Bhushan'),
  (1003, 'Bikash Bhagat', 1234876, 26, CURRENT_TIMESTAMP, 'Bikash Bhagat');