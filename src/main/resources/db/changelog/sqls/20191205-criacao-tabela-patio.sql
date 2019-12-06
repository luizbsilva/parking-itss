CREATE TABLE patio (
	codigo BIGINT,
	descricao VARCHAR(50) NOT NULL,
	numero_vagas BIGINT NOT NULL,
	taxa_hora double precision NOT NULL,
	PRIMARY KEY ( codigo ),
    CONSTRAINT unique_patio_codigo UNIQUE( codigo ));

CREATE SEQUENCE patio_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE patio_codigo_seq OWNER TO postgres;
ALTER TABLE patio ALTER COLUMN codigo SET DEFAULT nextval('patio_codigo_seq'::regclass);

