CREATE TABLE IF NOT EXISTS public.employee_salary
(
    employee_id integer NOT NULL,
    salary_id integer NOT NULL,
    date date NOT NULL,
    CONSTRAINT employee_salary_pkey PRIMARY KEY (employee_id, salary_id, date),
    CONSTRAINT fk_employee_salary_employee_id FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT,
    CONSTRAINT fk_employee_salary_salary_id FOREIGN KEY (salary_id)
        REFERENCES public.salary (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.employee_salary
    OWNER to postgres;