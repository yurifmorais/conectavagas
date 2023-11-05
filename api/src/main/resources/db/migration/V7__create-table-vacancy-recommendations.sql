CREATE TABLE vacancy_recommendations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    job_vacancy_id BIGINT NOT NULL,
    recommendation_read BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (job_vacancy_id) REFERENCES job_vacancies(id)
);