CREATE SEQUENCE IF NOT EXISTS public.street_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.street
(
    id integer NOT NULL DEFAULT nextval('street_id_seq'::regclass),
    name character varying(40) COLLATE pg_catalog."default",
    locality_id integer,
    CONSTRAINT street_pkey PRIMARY KEY (id),
    CONSTRAINT fk_street_locality_id FOREIGN KEY (locality_id)
           REFERENCES public.locality (id) MATCH SIMPLE
           ON UPDATE RESTRICT
           ON DELETE RESTRICT
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.street
    OWNER to postgres;

ALTER SEQUENCE street_id_seq OWNED BY street.id