CREATE SEQUENCE IF NOT EXISTS public.expense_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.expense
(
    id integer NOT NULL DEFAULT nextval('expense_id_seq'::regclass),
    name character varying(30) COLLATE pg_catalog."default",
    date date,
    CONSTRAINT expense_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.expense
    OWNER to postgres;

ALTER SEQUENCE expense_id_seq OWNED BY expense.id