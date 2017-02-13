# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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

create table urgencia (
  id                        bigint auto_increment not null,
  fecha                     timestamp,
  descripcion               varchar(255),
  constraint pk_urgencia primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists medico;

drop table if exists medico_especialista;

drop table if exists paciente;

drop table if exists urgencia;

SET REFERENTIAL_INTEGRITY TRUE;

