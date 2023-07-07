CREATE TABLE IF NOT EXISTS public.employee_schedule
(
    employee_id integer NOT NULL,
    year_month character varying(7) COLLATE pg_catalog."default",
    employee_priority integer,
    CONSTRAINT employee_schedule_pkey PRIMARY KEY (employee_id, year_month),
    CONSTRAINT fk_employee_schedule_employee_id FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.employee_schedule
    OWNER to postgres;