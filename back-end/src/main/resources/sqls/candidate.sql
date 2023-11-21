CREATE TABLE IF NOT EXISTS tb_candidate (

    ID                  BIGSERIAL,
    NAME                VARCHAR(60),
    TAX_ID              VARCHAR(11),
    EMAIL               VARCHAR(40),
    BIRTH_DATE          DATE,
    INTENDED_ROLE       VARCHAR(25),
    CURRICULUM          VARCHAR(800),
    UPDATED_AT          TIMESTAMP,
    CREATED_AT          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (ID)

);