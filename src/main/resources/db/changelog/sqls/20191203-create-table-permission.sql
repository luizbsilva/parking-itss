CREATE TABLE permission (
	id BIGINT,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(50) NOT NULL,
	PRIMARY KEY ( id ),
    CONSTRAINT unique_permissao_codigo UNIQUE( id ));

CREATE SEQUENCE permission_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE permission_id_seq OWNER TO postgres;
ALTER TABLE permission ALTER COLUMN id SET DEFAULT nextval('permission_id_seq'::regclass);