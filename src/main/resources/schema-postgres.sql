DROP TABLE IF EXISTS locations;
DROP SEQUENCE IF EXISTS public.locations_location_id_seq;

CREATE SEQUENCE public.locations_location_id_seq
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.locations_location_id_seq
    OWNER TO postgres;

CREATE TABLE public.locations
(
    location_id             integer NOT NULL DEFAULT nextval('locations_location_id_seq'::regclass),
    country_iso_alpha_code3 text    NOT NULL,
    country_name            text    NOT NULL,
    distance_from_bs_as     integer NOT NULL,
    invocations_count       bigint  NOT NULL,
    CONSTRAINT "PK_id" PRIMARY KEY (location_id)
)
    WITH (
        OIDS = FALSE
        );
ALTER TABLE public.locations
    OWNER TO postgres;
