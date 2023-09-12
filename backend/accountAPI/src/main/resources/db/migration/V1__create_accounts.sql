DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts(
                      customerID INT PRIMARY KEY AUTO_INCREMENT,
                      firstName VARCHAR(255) not null,
                      lastName VARCHAR(255) not null,
                      address VARCHAR(255) not null,
                      email VARCHAR(255) not null,
                      password VARCHAR(255) not null,
                      phone VARCHAR(255) not null
);