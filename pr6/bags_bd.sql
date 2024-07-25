CREATE DATABASE bags_db;

\c bags_db;

CREATE TABLE IF NOT EXISTS public.bags
(
    id serial PRIMARY KEY,
    brand character varying(100) NOT NULL,
    material text NOT NULL,
    type character varying(50) NOT NULL,
    cost integer numeric(9, 2) NOT NULL,
    volume numeric(9, 2) NOT NULL
    );

CREATE TABLE IF NOT EXISTS public.profile (
      id       SERIAL PRIMARY KEY,
      username text UNIQUE NOT NULL, -- Добавлено UNIQUE
      password text NOT NULL,
      role     text NOT NULL
);

INSERT INTO public.profile (username, password, role) VALUES
    ('admin', '$2a$10$dK5Sa3xg1ICBvpiiigCu.eEpS1QvAEguvOuWUjOPgWGIjss5JscaC', 'admin')
    ON CONFLICT (username) DO NOTHING;
INSERT INTO public.profile (username, password, role) VALUES
    ('admin_drugoy', '$2a$10$i0B02Ypcm8OXpaBShgNslu/wK.99zufFqjkKe6j9AhT3L7HCYifKm', 'admin')
    ON CONFLICT (username) DO NOTHING;