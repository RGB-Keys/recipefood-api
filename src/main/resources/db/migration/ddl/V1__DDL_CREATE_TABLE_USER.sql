CREATE SEQUENCE IF NOT EXISTS id_user_seq
     INCREMENT 1
     MINVALUE 1
     MAXVALUE 99999999999
     START 1
     CACHE 1;

CREATE TABLE usuarios (
    id_user         INTEGER         NOT NULL        DEFAULT NEXTVAL('id_user_seq') PRIMARY KEY,
    nome            VARCHAR(100)    NOT NULL,
    email           VARCHAR(100)    NOT NULL        UNIQUE,
    cpf             VARCHAR(11)     NOT NULL        UNIQUE,
    telefone        VARCHAR(20)     NOT NULL,
    categoria       VARCHAR(100)    NOT NULL,
    logradouro      VARCHAR(100)    NOT NULL,
    cidade          VARCHAR(100)    NOT NULL,
    uf              CHAR(2)         NOT NULL,
    cep             VARCHAR(8)      NOT NULL,
    numero          VARCHAR(4),
    complemento     VARCHAR(20)
)