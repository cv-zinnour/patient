create table patient
(
    id                                  uuid    not null
        constraint patient_pk
            primary key,
    version                             integer not null,
    first_name                          varchar not null,
    last_name                           varchar not null,
    birthday                            date,
    is_active                           boolean,
    mother_name                         varchar,
    file_number                         varchar,
    family_doctor                       jsonb,
    pharmacy                            jsonb,
    questionnaire_token                 varchar,
    questionnaire_token_expiration_date date,
    login_code                          varchar,
    socio_demographic_variables         jsonb,
    gender                              varchar
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

create table professional
(
    id      uuid    not null
        constraint expert_pk
            primary key,
    version integer not null,
    root    boolean
);

create table medical_file
(
    id            uuid    not null
        constraint medical_file_pk
            primary key,
    version       integer not null,
    creation_date date,
    patient       varchar
);

create table clinical_examination
(
    cardiovascular_heart_rate_value       integer,
    cardiovascular_heart_rate_regularity  boolean,
    blood_pressure_right_hand_diastolique integer,
    blood_pressure_left_hand_diastolique  integer,
    anthropometry_weight                  double precision,
    anthropometry_height                  double precision,
    anthropometry_imc                     double precision,
    anthropometry_waist                   double precision,
    smoking_type                          varchar,
    smoking_number_cigarettes             integer,
    pharmacotherapy_cardiovascular        varchar,
    pharmacotherapy_dyslipidemia          varchar,
    pharmacotherapy_diabetes              varchar,
    pharmacotherapy_other                 varchar,
    date                                  date,
    id                                    uuid    not null
        constraint clinical_examination_pk
            primary key,
    version                               integer not null,
    medical_file_id                       uuid
        constraint clinical_examination_medical_file_id_fk
            references medical_file,
    blood_pressure_right_hand_systolique  integer,
    blood_pressure_left_hand_systolique   integer
);

create unique index clinical_examination_id_uindex
    on clinical_examination (id);

create table patient_professional
(
    patient_id      uuid
        constraint patient_professional_patient_id_fk
            references patient,
    professional_id uuid
        constraint patient_professional_professional_id_fk
            references professional
);

create table medical_file_history
(
    id              uuid not null
        constraint medical_file_history_pk
            primary key,
    version         integer,
    date            date,
    antecedents     jsonb,
    medical_file_id uuid
        constraint medical_file_history_medical_file_id_fk
            references medical_file
);

create unique index medical_file_history_id_uindex
    on medical_file_history (id);

create table questionnaire
(
    id         uuid,
    version    integer,
    type       varchar,
    value      jsonb,
    patient_id uuid
        constraint questionnaire_patient_id_fk
            references patient,
    date       date
);

create table lipid_profile
(
    id              uuid not null
        constraint lipid_profile_pk
            primary key,
    version         integer,
    ldl             double precision,
    hdl             double precision,
    nohdl           double precision,
    triglyceride    double precision,
    hba1c           double precision,
    medical_file_id uuid
        constraint lipid_profile_medical_file_id_fk
            references medical_file
);

create unique index lipid_profile_id_uindex
    on lipid_profile (id);

create table appointment
(
    id               uuid,
    version          integer,
    patient_id       uuid
        constraint appointment_patient_id_fk
            references patient,
    professional_id  uuid,
    creation_date    date,
    appointment_date date,
    jpaq_email       boolean default false
);

create table recommendation
(
    id                  serial not null
        constraint recommendation_pk
            primary key,
    version             integer,
    professional_id     uuid,
    patient_id          uuid
        constraint recommendation_patient_id_fk
            references patient,
    recommendation      jsonb,
    response            jsonb,
    date_recommendation date,
    date_response       date
);

create unique index recommendation_id_uindex
    on recommendation (id);


create view patient_questionnaire_recommendation
            (row_num, patient_id, age, file_number, gender, mother_name, socio_demographic_variables, questionnaire_id,
             questionnaire_type, questionnaire_value, questionnaire_date, recommendation_id, recommendation_value,
             recommendation_response, date_recommendation, date_response)
as
SELECT row_number() OVER (ORDER BY ((SELECT 1)))                          AS row_num,
       p.id::character varying                                            AS patient_id,
       date_part('year'::text, age(p.birthday::timestamp with time zone)) AS age,
       p.file_number,
       p.gender,
       p.mother_name,
       p.socio_demographic_variables,
       q.id::character varying                                            AS questionnaire_id,
       q.type                                                             AS questionnaire_type,
       q.value                                                            AS questionnaire_value,
       q.date                                                             AS questionnaire_date,
       r.id                                                               AS recommendation_id,
       r.recommendation                                                   AS recommendation_value,
       r.response                                                         AS recommendation_response,
       r.date_recommendation,
       r.date_response
FROM patient p
         LEFT JOIN questionnaire q ON q.patient_id = p.id
         LEFT JOIN recommendation r ON r.patient_id = p.id;
