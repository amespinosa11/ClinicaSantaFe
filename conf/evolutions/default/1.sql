# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table diagnostico (
  id                        bigint auto_increment not null,
  fecha_expedicion          timestamp,
  descripcion               varchar(255),
  constraint pk_diagnostico primary key (id))
;

create table historial_medico (
  id                        bigint auto_increment not null,
  descripcion               varchar(255),
  constraint pk_historial_medico primary key (id))
;

create table medico (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  apellido                  varchar(255),
  constraint pk_medico primary key (id))
;

create table medico_especialista (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  apellido                  varchar(255),
  especialidad              varchar(255),
  constraint pk_medico_especialista primary key (id))
;

create table paciente (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  apellido                  varchar(255),
  constraint pk_paciente primary key (id))
;

create table registro (
  id                        bigint auto_increment not null,
  fecha_expedicion          timestamp,
  frecuencia_cardiaca       bigint,
  constraint pk_registro primary key (id))
;

create table urgencia (
  id                        bigint auto_increment not null,
  fecha                     timestamp,
  descripcion               varchar(255),
  constraint pk_urgencia primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists diagnostico;

drop table if exists historial_medico;

drop table if exists medico;

drop table if exists medico_especialista;

drop table if exists paciente;

drop table if exists registro;

drop table if exists urgencia;

SET REFERENTIAL_INTEGRITY TRUE;

