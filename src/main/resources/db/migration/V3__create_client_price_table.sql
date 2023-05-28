CREATE TABLE IF NOT EXISTS public.client_price
(
    client_id integer NOT NULL,
    price_id integer NOT NULL,
    date date NOT NULL,
    CONSTRAINT client_price_pkey PRIMARY KEY (client_id, price_id, date),
    CONSTRAINT fk_client_price_client_id FOREIGN KEY (client_id)
        REFERENCES public.client (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
        NOT VALID,
    CONSTRAINT fk_client_price_price_id FOREIGN KEY (price_id)
        REFERENCES public.price (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.client_price
    OWNER to postgres;