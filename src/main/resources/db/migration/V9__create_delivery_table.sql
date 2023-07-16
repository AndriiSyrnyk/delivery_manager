CREATE SEQUENCE IF NOT EXISTS public.delivery_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.delivery
(
    id integer NOT NULL DEFAULT nextval('delivery_id_seq'::regclass),
    client_id integer,
    locality_id integer,
    street_id integer,
    employee_id integer,
    date date,
    creation_time time without time zone,
    ready_time time without time zone,
    delivery_time time without time zone,
    CONSTRAINT delivery_pkey PRIMARY KEY (id),
    CONSTRAINT fk_delivery_client_id FOREIGN KEY (client_id)
        REFERENCES public.client (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT,
    CONSTRAINT fk_delivery_employee_id FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT,
    CONSTRAINT fk_delivery_locality_id FOREIGN KEY (locality_id)
        REFERENCES public.locality (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT,
    CONSTRAINT fk_delivery_street_id FOREIGN KEY (street_id)
        REFERENCES public.street (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.delivery
    OWNER to postgres;

ALTER SEQUENCE delivery_id_seq OWNED BY delivery.id