CREATE TABLE tags (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    parent_id BIGINT,
    FOREIGN KEY (parent_id) REFERENCES tags(id)
);