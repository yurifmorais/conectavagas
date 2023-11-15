CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255),
    name VARCHAR(255),
    cpf VARCHAR(11),
    cnpj VARCHAR(14),
    user_type BOOLEAN,
    city VARCHAR(255),
    github VARCHAR(255),
    linkedin VARCHAR(255)
);
