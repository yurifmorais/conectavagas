CREATE TABLE job_vacancies (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    description TEXT,
    location VARCHAR(50),
    salary DECIMAL(10, 2),
    user_id BIGINT,
    post_date DATE,
    end_date DATE,
    internship_type VARCHAR(50),
    requirements TEXT,
    benefits TEXT,
    FOREIGN KEY (user_id) REFERENCES users(ID)
);