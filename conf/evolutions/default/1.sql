# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table consejo (
  id                        bigserial not null,
  asunto                    varchar(255),
  descripcion               varchar(255),
  medico_id                 bigint,
  paciente_id               bigint,
  constraint pk_consejo primary key (id))
;

create table diagnostico (
  id                        bigserial not null,
  fecha_expedicion          timestamp,
  descripcion               varchar(255),
  paciente_id               bigint,
  constraint pk_diagnostico primary key (id))
;

create table examen (
  id                        bigserial not null,
  descripcion               varchar(255),
  fecha                     timestamp,
  paciente_id               bigint,
  constraint pk_examen primary key (id))
;

create table marcapaso (
  id                        bigserial not null,
  medico_especialista_id    bigint not null,
  frecuencia_marcapaso      float,
  paciente_id               bigint,
  constraint uq_marcapaso_paciente_id unique (paciente_id),
  constraint pk_marcapaso primary key (id))
;

create table medico (
  id                        bigserial not null,
  nombre                    varchar(255),
  apellido                  varchar(255),
  constraint pk_medico primary key (id))
;

create table medicoespecialista (
  id                        bigserial not null,
  nombre                    varchar(255),
  apellido                  varchar(255),
  especialidad              varchar(255),
  constraint pk_medicoespecialista primary key (id))
;

create table notificacion (
  id                        bigserial not null,
  tipo                      varchar(255),
  descripcion               varchar(255),
  fecha                     timestamp,
  medico_id                 bigint,
  paciente_id               bigint,
  registro_id               bigint,
  constraint uq_notificacion_registro_id unique (registro_id),
  constraint pk_notificacion primary key (id))
;

create table paciente (
  id                        bigserial not null,
  nombre                    varchar(255),
  apellido                  varchar(255),
  edad                      integer,
  peso                      float,
  estatura                  float,
  sexo                      varchar(255),
  marcapaso_id              bigint,
  constraint uq_paciente_marcapaso_id unique (marcapaso_id),
  constraint pk_paciente primary key (id))
;

create table registro (
  id                        bigserial not null,
  fecha_expedicion          timestamp,
  frecuencia_cardiaca       integer,
  presion_sanguinea1        integer,
  presion_sanguinea2        integer,
  nivel_estres              integer,
  nivel_actividad_fisica    integer,
  paciente_id               bigint,
  constraint pk_registro primary key (id))
;

create table tratamiento (
  id                        bigserial not null,
  descripcion               varchar(255),
  fecha                     timestamp,
  paciente_id               bigint,
  constraint pk_tratamiento primary key (id))
;

create table urgencia (
  id                        bigserial not null,
  fecha                     timestamp,
  descripcion               varchar(255),
  paciente_id               bigint,
  constraint pk_urgencia primary key (id))
;


create table medico_paciente (
  medico_id                      bigint not null,
  paciente_id                    bigint not null,
  constraint pk_medico_paciente primary key (medico_id, paciente_id))
;

create table paciente_medico (
  paciente_id                    bigint not null,
  medico_id                      bigint not null,
  constraint pk_paciente_medico primary key (paciente_id, medico_id))
;

create table paciente_medicoespecialista (
  paciente_id                    bigint not null,
  medicoespecialista_id          bigint not null,
  constraint pk_paciente_medicoespecialista primary key (paciente_id, medicoespecialista_id))
;
alter table consejo add constraint fk_consejo_medico_1 foreign key (medico_id) references medico (id);
create index ix_consejo_medico_1 on consejo (medico_id);
alter table consejo add constraint fk_consejo_paciente_2 foreign key (paciente_id) references paciente (id);
create index ix_consejo_paciente_2 on consejo (paciente_id);
alter table diagnostico add constraint fk_diagnostico_paciente_3 foreign key (paciente_id) references paciente (id);
create index ix_diagnostico_paciente_3 on diagnostico (paciente_id);
alter table examen add constraint fk_examen_paciente_4 foreign key (paciente_id) references paciente (id);
create index ix_examen_paciente_4 on examen (paciente_id);
alter table marcapaso add constraint fk_marcapaso_medicoespecialist_5 foreign key (medico_especialista_id) references medicoespecialista (id);
create index ix_marcapaso_medicoespecialist_5 on marcapaso (medico_especialista_id);
alter table marcapaso add constraint fk_marcapaso_paciente_6 foreign key (paciente_id) references paciente (id);
create index ix_marcapaso_paciente_6 on marcapaso (paciente_id);
alter table notificacion add constraint fk_notificacion_paciente_7 foreign key (paciente_id) references paciente (id);
create index ix_notificacion_paciente_7 on notificacion (paciente_id);
alter table notificacion add constraint fk_notificacion_registro_8 foreign key (registro_id) references registro (id);
create index ix_notificacion_registro_8 on notificacion (registro_id);
alter table paciente add constraint fk_paciente_marcapaso_9 foreign key (marcapaso_id) references marcapaso (id);
create index ix_paciente_marcapaso_9 on paciente (marcapaso_id);
alter table registro add constraint fk_registro_paciente_10 foreign key (paciente_id) references paciente (id);
create index ix_registro_paciente_10 on registro (paciente_id);
alter table registro add constraint fk_registro_notificacion_11 foreign key (notificacion_id) references notificacion (id);
create index ix_registro_notificacion_11 on registro (notificacion_id);
alter table tratamiento add constraint fk_tratamiento_paciente_12 foreign key (paciente_id) references paciente (id);
create index ix_tratamiento_paciente_12 on tratamiento (paciente_id);
alter table urgencia add constraint fk_urgencia_paciente_13 foreign key (paciente_id) references paciente (id);
create index ix_urgencia_paciente_13 on urgencia (paciente_id);
alter table marcapaso add constraint fk_marcapaso_medicoespecialist_4 foreign key (medico_especialista_id) references medicoespecialista (id);
create index ix_marcapaso_medicoespecialist_4 on marcapaso (medico_especialista_id);
alter table marcapaso add constraint fk_marcapaso_paciente_5 foreign key (paciente_id) references paciente (id);
create index ix_marcapaso_paciente_5 on marcapaso (paciente_id);
alter table notificacion add constraint fk_notificacion_medico_6 foreign key (medico_id) references medico (id);
create index ix_notificacion_medico_6 on notificacion (medico_id);
alter table notificacion add constraint fk_notificacion_paciente_7 foreign key (paciente_id) references paciente (id);
create index ix_notificacion_paciente_7 on notificacion (paciente_id);
alter table paciente add constraint fk_paciente_marcapaso_8 foreign key (marcapaso_id) references marcapaso (id);
create index ix_paciente_marcapaso_8 on paciente (marcapaso_id);
alter table registro add constraint fk_registro_paciente_9 foreign key (paciente_id) references paciente (id);
create index ix_registro_paciente_9 on registro (paciente_id);
alter table urgencia add constraint fk_urgencia_paciente_10 foreign key (paciente_id) references paciente (id);
create index ix_urgencia_paciente_10 on urgencia (paciente_id);



alter table medico_paciente add constraint fk_medico_paciente_medico_01 foreign key (medico_id) references medico (id);

alter table medico_paciente add constraint fk_medico_paciente_paciente_02 foreign key (paciente_id) references paciente (id);

alter table paciente_medico add constraint fk_paciente_medico_paciente_01 foreign key (paciente_id) references paciente (id);

alter table paciente_medico add constraint fk_paciente_medico_medico_02 foreign key (medico_id) references medico (id);

alter table paciente_medicoespecialista add constraint fk_paciente_medicoespecialist_01 foreign key (paciente_id) references paciente (id);

alter table paciente_medicoespecialista add constraint fk_paciente_medicoespecialist_02 foreign key (medicoespecialista_id) references medicoespecialista (id);

# --- !Downs

drop table if exists consejo cascade;

drop table if exists diagnostico cascade;

drop table if exists examen cascade;

drop table if exists marcapaso cascade;

drop table if exists medico cascade;

drop table if exists medico_paciente cascade;

drop table if exists medicoespecialista cascade;

drop table if exists notificacion cascade;

drop table if exists paciente cascade;

drop table if exists paciente_medico cascade;

drop table if exists paciente_medicoespecialista cascade;

drop table if exists registro cascade;

drop table if exists tratamiento cascade;

drop table if exists urgencia cascade;

