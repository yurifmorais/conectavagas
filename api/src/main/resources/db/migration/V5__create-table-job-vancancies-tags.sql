CREATE TABLE job_vacancies_tags (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    job_vacancy_id BIGINT,
    tag_id BIGINT,
    FOREIGN KEY (job_vacancy_id) REFERENCES job_vacancies(id),
    FOREIGN KEY (tag_id) REFERENCES tags(id)
);