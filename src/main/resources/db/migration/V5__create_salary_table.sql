CREATE SEQUENCE IF NOT EXISTS public.salary_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.salary
(
    id integer NOT NULL DEFAULT nextval('salary_id_seq'::regclass),
    salary_per_delivery integer,
    salary_per_hour integer,
    salary_per_km integer,
    CONSTRAINT salary_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.salary
    OWNER to postgres;

ALTER SEQUENCE salary_id_seq OWNED BY salary.id