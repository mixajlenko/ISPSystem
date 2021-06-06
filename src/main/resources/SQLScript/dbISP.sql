--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

-- Started on 2021-06-05 16:42:59

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 17064)
-- Name: PAYMENT; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."PAYMENT" (
    id integer NOT NULL,
    user_id integer NOT NULL,
    bill integer NOT NULL,
    status integer NOT NULL,
    balance integer NOT NULL,
    type character varying(50) NOT NULL,
    date date NOT NULL
);


ALTER TABLE public."PAYMENT" OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 17062)
-- Name: PAYMENT_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."PAYMENT_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."PAYMENT_id_seq" OWNER TO postgres;

--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 214
-- Name: PAYMENT_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."PAYMENT_id_seq" OWNED BY public."PAYMENT".id;


--
-- TOC entry 211 (class 1259 OID 17043)
-- Name: ROLE; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."ROLE" (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public."ROLE" OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 17041)
-- Name: ROLE_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."ROLE_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."ROLE_id_seq" OWNER TO postgres;

--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 210
-- Name: ROLE_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."ROLE_id_seq" OWNED BY public."ROLE".id;


--
-- TOC entry 203 (class 1259 OID 16811)
-- Name: SERVICE; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."SERVICE" (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description text NOT NULL
);


ALTER TABLE public."SERVICE" OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16896)
-- Name: SERVICE_TARIFF; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."SERVICE_TARIFF" (
    id integer NOT NULL,
    service_id integer NOT NULL,
    tariff_id integer NOT NULL
);


ALTER TABLE public."SERVICE_TARIFF" OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16894)
-- Name: SERVICE_TARIFF_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."SERVICE_TARIFF_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."SERVICE_TARIFF_id_seq" OWNER TO postgres;

--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 208
-- Name: SERVICE_TARIFF_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."SERVICE_TARIFF_id_seq" OWNED BY public."SERVICE_TARIFF".id;


--
-- TOC entry 202 (class 1259 OID 16809)
-- Name: SERVICE_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."SERVICE_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."SERVICE_id_seq" OWNER TO postgres;

--
-- TOC entry 3066 (class 0 OID 0)
-- Dependencies: 202
-- Name: SERVICE_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."SERVICE_id_seq" OWNED BY public."SERVICE".id;


--
-- TOC entry 213 (class 1259 OID 17051)
-- Name: STATUS; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."STATUS" (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public."STATUS" OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 17049)
-- Name: STATUS_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."STATUS_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."STATUS_id_seq" OWNER TO postgres;

--
-- TOC entry 3067 (class 0 OID 0)
-- Dependencies: 212
-- Name: STATUS_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."STATUS_id_seq" OWNED BY public."STATUS".id;


--
-- TOC entry 207 (class 1259 OID 16880)
-- Name: TARIFF; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."TARIFF" (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description text NOT NULL,
    price integer NOT NULL
);


ALTER TABLE public."TARIFF" OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16878)
-- Name: TARIFF_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."TARIFF_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."TARIFF_id_seq" OWNER TO postgres;

--
-- TOC entry 3068 (class 0 OID 0)
-- Dependencies: 206
-- Name: TARIFF_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."TARIFF_id_seq" OWNED BY public."TARIFF".id;


--
-- TOC entry 201 (class 1259 OID 16789)
-- Name: USER; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."USER" (
    id integer NOT NULL,
    "firstName" character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    "secondName" character varying(255) NOT NULL,
    wallet integer NOT NULL,
    status integer NOT NULL,
    password character varying NOT NULL,
    role integer NOT NULL
);


ALTER TABLE public."USER" OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16843)
-- Name: USERS_PLAN; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."USERS_PLAN" (
    id integer NOT NULL,
    user_id integer NOT NULL,
    tariff_id integer NOT NULL,
    status integer NOT NULL,
    "nextBill" date,
    "subDate" date NOT NULL
);


ALTER TABLE public."USERS_PLAN" OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16841)
-- Name: USERS_PLAN_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."USERS_PLAN_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."USERS_PLAN_id_seq" OWNER TO postgres;

--
-- TOC entry 3069 (class 0 OID 0)
-- Dependencies: 204
-- Name: USERS_PLAN_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."USERS_PLAN_id_seq" OWNED BY public."USERS_PLAN".id;


--
-- TOC entry 200 (class 1259 OID 16787)
-- Name: USER_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."USER_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."USER_id_seq" OWNER TO postgres;

--
-- TOC entry 3070 (class 0 OID 0)
-- Dependencies: 200
-- Name: USER_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."USER_id_seq" OWNED BY public."USER".id;


--
-- TOC entry 2904 (class 2604 OID 17067)
-- Name: PAYMENT id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."PAYMENT" ALTER COLUMN id SET DEFAULT nextval('public."PAYMENT_id_seq"'::regclass);


--
-- TOC entry 2902 (class 2604 OID 17046)
-- Name: ROLE id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ROLE" ALTER COLUMN id SET DEFAULT nextval('public."ROLE_id_seq"'::regclass);


--
-- TOC entry 2898 (class 2604 OID 16814)
-- Name: SERVICE id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."SERVICE" ALTER COLUMN id SET DEFAULT nextval('public."SERVICE_id_seq"'::regclass);


--
-- TOC entry 2901 (class 2604 OID 16899)
-- Name: SERVICE_TARIFF id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."SERVICE_TARIFF" ALTER COLUMN id SET DEFAULT nextval('public."SERVICE_TARIFF_id_seq"'::regclass);


--
-- TOC entry 2903 (class 2604 OID 17054)
-- Name: STATUS id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."STATUS" ALTER COLUMN id SET DEFAULT nextval('public."STATUS_id_seq"'::regclass);


--
-- TOC entry 2900 (class 2604 OID 16883)
-- Name: TARIFF id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."TARIFF" ALTER COLUMN id SET DEFAULT nextval('public."TARIFF_id_seq"'::regclass);


--
-- TOC entry 2897 (class 2604 OID 16792)
-- Name: USER id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."USER" ALTER COLUMN id SET DEFAULT nextval('public."USER_id_seq"'::regclass);


--
-- TOC entry 2899 (class 2604 OID 16846)
-- Name: USERS_PLAN id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."USERS_PLAN" ALTER COLUMN id SET DEFAULT nextval('public."USERS_PLAN_id_seq"'::regclass);


--
-- TOC entry 2922 (class 2606 OID 17069)
-- Name: PAYMENT PAYMENT_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."PAYMENT"
    ADD CONSTRAINT "PAYMENT_pkey" PRIMARY KEY (id);


--
-- TOC entry 2918 (class 2606 OID 17048)
-- Name: ROLE ROLE_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ROLE"
    ADD CONSTRAINT "ROLE_pkey" PRIMARY KEY (id);


--
-- TOC entry 2916 (class 2606 OID 16901)
-- Name: SERVICE_TARIFF SERVICE_TARIFF_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."SERVICE_TARIFF"
    ADD CONSTRAINT "SERVICE_TARIFF_pkey" PRIMARY KEY (id);


--
-- TOC entry 2910 (class 2606 OID 16850)
-- Name: SERVICE SERVICE_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."SERVICE"
    ADD CONSTRAINT "SERVICE_pkey" PRIMARY KEY (id);


--
-- TOC entry 2920 (class 2606 OID 17056)
-- Name: STATUS STATUS_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."STATUS"
    ADD CONSTRAINT "STATUS_pkey" PRIMARY KEY (id);


--
-- TOC entry 2914 (class 2606 OID 16888)
-- Name: TARIFF TARIFFS_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."TARIFF"
    ADD CONSTRAINT "TARIFFS_pkey" PRIMARY KEY (id);


--
-- TOC entry 2912 (class 2606 OID 16848)
-- Name: USERS_PLAN USERS_PLAN_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."USERS_PLAN"
    ADD CONSTRAINT "USERS_PLAN_pkey" PRIMARY KEY (id);


--
-- TOC entry 2906 (class 2606 OID 16797)
-- Name: USER USERS_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."USER"
    ADD CONSTRAINT "USERS_pkey" PRIMARY KEY (id);


--
-- TOC entry 2908 (class 2606 OID 17076)
-- Name: USER USER_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."USER"
    ADD CONSTRAINT "USER_email_key" UNIQUE (email);


--
-- TOC entry 2925 (class 2606 OID 17019)
-- Name: SERVICE_TARIFF S_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."SERVICE_TARIFF"
    ADD CONSTRAINT "S_id" FOREIGN KEY (service_id) REFERENCES public."SERVICE"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2926 (class 2606 OID 17024)
-- Name: SERVICE_TARIFF T_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."SERVICE_TARIFF"
    ADD CONSTRAINT "T_id" FOREIGN KEY (tariff_id) REFERENCES public."TARIFF"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2924 (class 2606 OID 17080)
-- Name: USERS_PLAN tariff_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."USERS_PLAN"
    ADD CONSTRAINT tariff_id_fk FOREIGN KEY (tariff_id) REFERENCES public."TARIFF"(id) ON UPDATE RESTRICT ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2927 (class 2606 OID 17070)
-- Name: PAYMENT user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."PAYMENT"
    ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES public."USER"(id) ON UPDATE RESTRICT ON DELETE CASCADE;


--
-- TOC entry 2923 (class 2606 OID 17034)
-- Name: USERS_PLAN user_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."USERS_PLAN"
    ADD CONSTRAINT user_user_id_fk FOREIGN KEY (user_id) REFERENCES public."USER"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


-- Completed on 2021-06-05 16:42:59

--
-- PostgreSQL database dump complete
--

