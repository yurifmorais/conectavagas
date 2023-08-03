CREATE TABLE applications (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  job_opportunity_id BIGINT NOT NULL,
  application_date DATE NOT NULL,
  application_status VARCHAR(50) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (job_opportunity_id) REFERENCES job_opportunities(id)
);