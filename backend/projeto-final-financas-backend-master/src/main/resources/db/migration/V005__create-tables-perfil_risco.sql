CREATE TABLE perfis_risco(
    id BIGINT NOT NULL AUTO_INCREMENT,
    categoria VARCHAR(100) NOT NULL,
    usuario_id BIGINT NOT NULL UNIQUE,

    PRIMARY KEY(id),
    CONSTRAINT PERFIS_RISCO_FK_USUARIO FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE questionarios_perfil_risco(
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL UNIQUE,
    pergunta1 CHAR NOT NULL,
    pergunta2 CHAR NOT NULL,
    pergunta3 CHAR NOT NULL,
    pergunta4 CHAR NOT NULL,
    pergunta5 CHAR NOT NULL,
    pergunta6 CHAR NOT NULL,
    pergunta7 CHAR NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT QUESTIONARIOS_PERFIL_RISCO_FK_USUARIO FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
);
