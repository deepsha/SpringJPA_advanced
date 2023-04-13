CREATE TABLE APPLICATIONS
(
    application_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    app_name    VARCHAR(100),
    description     VARCHAR(100),
    owner VARCHAR(100)
   
);

CREATE TABLE TICKETS
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    title    VARCHAR(100),
    description     VARCHAR(100),
    status VARCHAR(100),
    application_id BIGINT
   
);


CREATE TABLE RELEASES
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    RELEASE_DATE    VARCHAR(100),
    description     VARCHAR(100),
    RELEASE_MANAGER VARCHAR(100)
   
);

