CREATE SEQUENCE IF NOT EXISTS id_recipe_seq
     INCREMENT 1
     MINVALUE 1
     MAXVALUE 99999999999
     START 1
     CACHE 1;

CREATE TABLE recipes (
    id_recipe       INTEGER         NOT NULL        DEFAULT NEXTVAL('id_recipe_seq') PRIMARY KEY,
    titulo          VARCHAR(100)    NOT NULL,
    ingredientes    TEXT[]          NOT NULL,
    instrucoes      VARCHAR(500)    NOT NULL,
    dificuldade     VARCHAR(20)     NOT NULL,
    porcao          INTEGER         NOT NULL,
    id_user         INTEGER         NOT NULL,
    FOREIGN KEY (id_user) REFERENCES usuarios(id_user)
)