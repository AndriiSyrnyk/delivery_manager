CREATE SEQUENCE IF NOT EXISTS public.client_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.client_id_seq
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.client
(
    id integer NOT NULL DEFAULT nextval('client_id_seq'::regclass),
    name character varying(20) COLLATE pg_catalog."default",
    comment character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT client_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.client
    OWNER to postgres;

ALTER SEQUENCE client_id_seq OWNED BY client.id