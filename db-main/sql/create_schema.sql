-- Tabla de roles
create table if not exists rol (
    id serial primary key,
    name varchar(50) not null,
    description text,
    logo varchar(255),
    created_at TIMESTAMP not null,
    updated_at TIMESTAMP not null
);

-- Tabla de usuarios
create table if not exists "users" (
    id serial primary key,
    id_rol bigint,
    name varchar(50),
    photo text,
    address text,
    email varchar(50) unique,
    password varchar(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    constraint fk_user_rol
        foreign key (id_rol)
        references rol(id)
        on delete cascade
);

-- Tabla de robots
create table if not exists robot (
    id serial primary key,
    id_user bigint,
    name varchar(100) not null,
    model varchar(100) not null,
    serial_number varchar(100) not null,
    description text,
    created_at TIMESTAMP not null,
    updated_at TIMESTAMP not null,
    constraint fk_robot_user
        foreign key (id_user)
        references "users"(id)
        on delete cascade
);

-- Tabla de ubicaciones del robot
create table if not exists robot_location (
    id serial primary key,
    id_robot bigint,
    latitude float not null,
    longitude float not null,
    timestamp TIMESTAMP not null,
    constraint fk_location_robot
        foreign key (id_robot)
        references robot(id)
        on delete cascade
);

-- Tabla de sensores
create table if not exists sensor (
    id serial primary key,
    name varchar(100) not null,
    type varchar(50),
    created_at TIMESTAMP not null,
    updated_at TIMESTAMP not null
);

-- Tabla de relación entre robots y sensores
create table if not exists robot_sensor (
    id serial primary key,
    id_robot bigint not null,
    id_sensor bigint not null,
    created_at TIMESTAMP not null,
    constraint fk_robot_sensor_robot
        foreign key (id_robot)
        references robot(id)
        on delete cascade,
    constraint fk_robot_sensor_sensor
        foreign key (id_sensor)
        references sensor(id)
        on delete cascade
);

-- Tabla para almacenar información de los sensores y su ubicación GPS
create table if not exists sensor_data (
    id serial primary key,
    id_sensor bigint,
    id_robot bigint,
    value float not null,
    latitude float not null,  -- Coordenada GPS donde se tomó la lectura
    longitude float not null, -- Coordenada GPS donde se tomó la lectura
    timestamp TIMESTAMP not null,
    constraint fk_sensor_data_sensor
        foreign key (id_sensor)
        references sensor(id)
        on delete cascade,
    constraint fk_sensor_data_robot
        foreign key (id_robot)
        references robot(id)
        on delete cascade
);