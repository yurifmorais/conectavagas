CREATE TABLE resumes (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  experiences TEXT,
  skills TEXT,
  education TEXT,
  contact_info TEXT,
  FOREIGN KEY (user_id) REFERENCES users(id)
);