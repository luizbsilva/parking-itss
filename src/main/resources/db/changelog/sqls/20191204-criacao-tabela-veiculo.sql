CREATE TABLE veiculo (
	codigo BIGINT NOT NULL,
	placa VARCHAR(20) NOT  NULL,
	cor VARCHAR(20),
	modelo VARCHAR(20),
	marca BIGINT NOT NULL,
  CONSTRAINT unique_veiculo_codigo UNIQUE( codigo )) ;

ALTER TABLE veiculo ADD CONSTRAINT fk_veiculo_marca FOREIGN KEY (marca)
	REFERENCES marca_veiculo(codigo)
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;

CREATE SEQUENCE veiculo_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE veiculo_codigo_seq OWNER TO postgres;
ALTER TABLE veiculo ALTER COLUMN codigo SET DEFAULT nextval('veiculo_codigo_seq'::regclass);
