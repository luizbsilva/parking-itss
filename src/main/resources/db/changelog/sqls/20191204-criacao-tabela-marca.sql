CREATE TABLE marca_veiculo (
	codigo BIGINT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL,
	PRIMARY KEY ( codigo ),
    CONSTRAINT unique_marca_codigo UNIQUE( codigo ));

CREATE SEQUENCE marca_veiculo_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE marca_veiculo_codigo_seq OWNER TO postgres;
ALTER TABLE marca_veiculo ALTER COLUMN codigo SET DEFAULT nextval('marca_veiculo_codigo_seq'::regclass);