CREATE SEQUENCE IF NOT EXISTS public.price_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.price
(
    id integer NOT NULL DEFAULT nextval('price_id_seq'::regclass),
    value integer,
    CONSTRAINT price_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.price
    OWNER to postgres;

ALTER SEQUENCE price_id_seq OWNED BY price.id