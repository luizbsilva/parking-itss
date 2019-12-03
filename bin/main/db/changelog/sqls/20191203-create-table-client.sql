CREATE TABLE client (
	id BIGINT NOT NULL,
	name VARCHAR( 80 ) NOT NULL,
	cpf VARCHAR( 15 ) NOT NULL,
	phone VARCHAR( 15 ) NOT NULL,
	PRIMARY KEY ( id ),
	CONSTRAINT unique_client_id UNIQUE( id ) );

CREATE SEQUENCE client_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE client_id_seq OWNER TO postgres;
ALTER TABLE client ALTER COLUMN id SET DEFAULT nextval('client_id_seq'::regclass);