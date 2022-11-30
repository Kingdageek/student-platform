CREATE TABLE tasks (
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(255),
    description VARCHAR(255),
    status VARCHAR(255),
    CONSTRAINT task_pk PRIMARY KEY(id)
);