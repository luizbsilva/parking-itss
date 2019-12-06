CREATE TABLE estacionamento (
	codigo BIGINT NOT NULL,
	data_entrada DATE NOT  NULL,
	horario_entrada TIME,
	horario_saida TIME,
	valor_periodo double precision,
	placa_veiculo VARCHAR(20) NOT NULL,
	patio BIGINT NOT NULL,
  CONSTRAINT unique_estacionamento_codigo UNIQUE( codigo )) ;

ALTER TABLE estacionamento ADD CONSTRAINT fk_estacionamento_patio FOREIGN KEY (patio)
	REFERENCES patio(codigo)
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;

CREATE SEQUENCE estacionamento_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE estacionamento_codigo_seq OWNER TO postgres;
ALTER TABLE estacionamento ALTER COLUMN codigo SET DEFAULT nextval('estacionamento_codigo_seq'::regclass);
