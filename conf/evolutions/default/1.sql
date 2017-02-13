# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table paciente (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  apellido                  varchar(255),
  constraint pk_paciente primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists paciente;

SET REFERENTIAL_INTEGRITY TRUE;

