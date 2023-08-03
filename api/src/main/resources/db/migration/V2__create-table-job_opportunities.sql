CREATE TABLE job_opportunities (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  description TEXT NOT NULL,
  location VARCHAR(100) NOT NULL,
  publication_date DATE NOT NULL,
  job_type VARCHAR(100) NOT NULL
);