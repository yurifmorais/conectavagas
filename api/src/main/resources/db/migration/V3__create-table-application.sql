CREATE TABLE APPLICATIONS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    person_id BIGINT,
    job_vacancy_id BIGINT,
    application_date DATE,
    FOREIGN KEY (person_id) REFERENCES USERS(ID),
    FOREIGN KEY (job_vacancy_id) REFERENCES JOB_VACANCIES(ID)
);