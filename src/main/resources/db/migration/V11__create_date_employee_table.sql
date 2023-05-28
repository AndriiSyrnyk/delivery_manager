CREATE SEQUENCE IF NOT EXISTS public.date_employee_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.date_employee
(
    id integer NOT NULL DEFAULT nextval('date_employee_id_seq'::regclass),
    employee_id integer,
    value integer,
    date date,
    CONSTRAINT date_employee_pkey PRIMARY KEY (id),
    CONSTRAINT fk_date_employee_employee_id FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.date_employee
    OWNER to postgres;

ALTER SEQUENCE date_employee_id_seq OWNED BY date_employee.id