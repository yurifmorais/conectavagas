CREATE TABLE jobvacancy (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    description TEXT,
    location VARCHAR(50),
    salary DECIMAL(10, 2),
    user_id BIGINT,
    filters VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);