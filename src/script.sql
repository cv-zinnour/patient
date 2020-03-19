create table professional
(
    id         uuid    not null
        constraint expert_pk
            primary key,
    first_name varchar not null,
    last_name  varchar not null,
    version    integer not null,
    root       boolean
);

create table patient
(
    id              uuid    not null
        constraint patient_pk
            primary key,
    version         integer not null,
    first_name      varchar not null,
    last_name       varchar not null,
    birthday        date,
    is_active       boolean,
    mother_name     varchar,
    file_number     varchar,
    family_doctor   jsonb,
    pharmacy        jsonb,
    professional_id uuid
        constraint patient_professional_id_fk
            references professional
);

create unique index patient_file_number_uindex
    on patient (file_number);

create table contact
(
    id            uuid    not null
        constraint contact_pk
            primary key
        constraint contact_patient_id_fk
            references patient,
    phone         bigint,
    email         varchar,
    street        varchar,
    street_number integer,
    city          varchar,
    postal_code   varchar,
    province      varchar,
    version       integer not null
);

create table medical_file
(
    id                          uuid    not null
        constraint medical_file_pk
            primary key,
    version                     integer not null,
    creation_date               date,
    socio_demographic_variables jsonb,
    antecedents                 jsonb,
    patient                     varchar
);

create table clinical_examination
(
    cardiovascular_heart_rate_value          integer,
    cardiovascular_heart_rate_regularity     boolean,
    cardiovascular_blood_pressure_right_hand integer,
    cardiovascular_blood_pressure_left_hand  integer,
    anthropometry_weight                     double precision,
    anthropometry_height                     double precision,
    anthropometry_imc                        double precision,
    anthropometry_waist                      double precision,
    smoking_type                             varchar,
    smoking_number_cigarettes                integer,
    pharmacotherapy_cardiovascular           varchar,
    pharmacotherapy_dyslipidemia             varchar,
    pharmacotherapy_diabetes                 varchar,
    pharmacotherapy_other                    varchar,
    date                                     date    not null,
    id                                       uuid    not null
        constraint clinical_examination_pk
            primary key,
    version                                  integer not null,
    medical_file                             uuid
        constraint clinical_examination_medical_file_id_fk
            references medical_file
);

create unique index clinical_examination_id_uindex
    on clinical_examination (id);


CREATE VIEW birthday_gender AS
select p.id as id,
       cast (p.birthday as varchar) as birthday,
       cast (p.socio_demographic_variables->>'gender' as varchar) as gender
from patient p;


CREATE VIEW height_weight AS
select mf.id as id,
       mf.patient as patient_id,
       cast (ce.anthropometry_height as varchar) as height,
       cast (ce.anthropometry_weight as varchar) as weight
from medical_file mf
         left join clinical_examination ce on ce.medical_file_id = mf.id ;
