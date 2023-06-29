CREATE TABLE IF NOT EXISTS public.schedule
(
    employee_id integer NOT NULL,
    date date NOT NULL,
    start_time time without time zone,
    end_time time without time zone,
    amount_paid integer,
    CONSTRAINT schedule_pkey PRIMARY KEY (employee_id, date),
    CONSTRAINT fk_schedule_employee_id FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.schedule
    OWNER to postgres;