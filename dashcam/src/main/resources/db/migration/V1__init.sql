create table if not exists trucks(
    id serial not null primary key,
    imei varchar
);

create table if not exists events(
    id serial not null primary key,
    imei varchar,
    alarm_type varchar,
    alarm_time varchar,
    latitude numeric,
    longitude numeric,
    file_list TEXT ARRAY
);

create table if not exists videos(
    id serial not null primary key,
    imei varchar,
    file_name varchar,
    video_data varchar,
    url varchar
);

create table if not exists locations(
    id serial not null primary key,
    imei varchar,
    location_time varchar,
    latitude numeric,
    longitude numeric
);
