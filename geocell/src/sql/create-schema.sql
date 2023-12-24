CREATE EXTENSION postgis;  -- to use geographic information

CREATE TABLE users (
    id serial primary key,
    username varchar(64) unique not null,
    type varchar(8) not null default 'free' check (type in ('free', 'premium'))
);

CREATE TABLE token (
    token_validation varchar(256) primary key,
    user_id integer not null references users(id),
    created_at bigint not null,
    last_used_at bigint not null
);

CREATE TABLE band(
    id serial primary key,
    band varchar(50) not null,
    bandwidth double precision,
    uplink_freq double precision,
    downlink_freq double precision,
    earfcn double precision
);

CREATE TABLE country(
    name varchar(128) primary key,
    code varchar(4),
    polygon geometry(Polygon, 4326),
    flag varchar(128),
    UNIQUE (code)
);

CREATE TABLE district(
    id varchar(20) primary key,
    district varchar(128) not null,
    polygon geometry(Polygon, 4326),
    country_id varchar(128) references country,
    UNIQUE (district, country_id)
);

CREATE TABLE county(
    id integer primary key,
    id_county varchar(20) not null,
    county varchar(128) not null,
    polygon geometry(Polygon, 4326),
    district_id varchar(20) references district(id),
    UNIQUE (id_county, district_id)
);

CREATE TABLE location(
    id bigserial primary key,
    coordinates geometry(Point, 4326),
    address varchar(128),
    address1 varchar(128),
    zip4 integer not null,
    zip3 integer not null,
    postal_designation varchar(128),
    county_id bigint references county(id),
    UNIQUE (coordinates, address, address1, zip4, zip3, postal_designation, county_id)
);

CREATE TABLE enbgnb(
    id bigserial primary key,
    enb_gnb integer not null,
    location_id integer references location(id),
    UNIQUE (enb_gnb, location_id)
);

CREATE TABLE mcc_mnc(
    id bigserial primary key,
    type varchar(128),
    mcc integer not null,
    mnc integer not null,
    brand varchar(128),
    operator varchar(256),
    status varchar(128),
    bands varchar(256),
    notes varchar(512),
    country_id varchar(128) references country(name),
    UNIQUE (mcc, mnc)
);

CREATE TABLE cell (
    id bigserial primary key,
    lac_tac varchar(50) not null,
    ci varchar(20),
    eci_nci varchar(20),
    cgi varchar(30),
    paragon_cgi varchar(100),
    technology integer not null,
    direction integer,
    name varchar(200),
    created bigint not null,
    modified bigint not null,
    band_id integer references band(id) ON DELETE SET NULL,
    enb_gnb_id bigint references enbgnb(id) ON DELETE SET NULL,
    location_id bigint references location(id) ON DELETE CASCADE ON UPDATE CASCADE,
    mcc_mnc_id bigint references mcc_mnc(id) ON DELETE CASCADE ON UPDATE CASCADE,
    modifier_id integer references users(id),
    owner_id integer references users(id),
    polygon geography(PolygonZ, 4326),
    polygon_short geography(PolygonZ, 4326)
);

CREATE TABLE coverage(
    id bigserial primary key,
    signal_strength integer,
    timestamp bigint not null,
    cell_id bigint references cell(id),
    location_id bigint references location(id),
    user_id integer references users(id)
);
