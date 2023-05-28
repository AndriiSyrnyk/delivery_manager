CREATE SEQUENCE IF NOT EXISTS public.locality_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.locality
(
    id integer NOT NULL DEFAULT nextval('locality_id_seq'::regclass),
    name character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT locality_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.locality
    OWNER to postgres;

ALTER SEQUENCE locality_id_seq OWNED BY locality.id