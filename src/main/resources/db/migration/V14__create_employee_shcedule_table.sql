CREATE SEQUENCE IF NOT EXISTS public.employee_priority_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.employee_priority_sequence
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.employee_schedule
(
    employee_id integer NOT NULL,
    year_month character varying(7) COLLATE pg_catalog."default",
    employee_priority integer UNIQUE DEFAULT nextval('employee_priority_sequence'::regclass),
    CONSTRAINT employee_schedule_pkey PRIMARY KEY (employee_id, year_month),
    CONSTRAINT fk_employee_schedule_employee_id FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.employee_schedule
    OWNER to postgres;

ALTER SEQUENCE employee_priority_sequence OWNED BY employee_schedule.employee_priority