CREATE SEQUENCE IF NOT EXISTS public.employee_priority_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.employee_priority_sequence
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.schedule
(
    employee_id integer NOT NULL,
    date date NOT NULL,
    start_time time without time zone,
    end_time time without time zone,
    amount_paid integer,
    employee_priority integer UNIQUE DEFAULT nextval('employee_priority_sequence'::regclass),
    CONSTRAINT schedule_pkey PRIMARY KEY (employee_id, date),
    CONSTRAINT fk_schedule_employee_id FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.schedule
    OWNER to postgres;

ALTER SEQUENCE employee_priority_sequence OWNED BY schedule.employee_priority