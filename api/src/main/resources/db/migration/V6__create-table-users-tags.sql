CREATE TABLE users_tags (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    tag_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (tag_id) REFERENCES tags(id)
);