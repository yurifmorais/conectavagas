CREATE TABLE applications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    person_id BIGINT,
    job_vacancy_id BIGINT,
    application_date DATE,
    FOREIGN KEY (person_id) REFERENCES users(ID),
    FOREIGN KEY (job_vacancy_id) REFERENCES job_vacancies(ID)
);