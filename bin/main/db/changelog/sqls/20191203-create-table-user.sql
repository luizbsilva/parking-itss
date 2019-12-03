CREATE TABLE system_user (
	id BIGINT,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	passowrd VARCHAR(150) NOT NULL,
	PRIMARY KEY ( id ),
    CONSTRAINT unique_system_user_id UNIQUE( id ));

CREATE SEQUENCE system_user_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE system_user_id_seq OWNER TO postgres;
ALTER TABLE system_user ALTER COLUMN id SET DEFAULT nextval('system_user_id_seq'::regclass);