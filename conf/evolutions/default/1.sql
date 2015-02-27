# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table course (
  id                        bigint not null,
  subject                   varchar(255),
  code                      integer,
  title                     varchar(255),
  location_id               bigint,
  constraint pk_course primary key (id))
;

create table location (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  latitude                  double,
  longitude                 double,
  image                     varchar(255),
  location_type_id          bigint,
  constraint pk_location primary key (id))
;

create table location_type (
  id                        bigint not null,
  type                      varchar(255),
  description               varchar(255),
  constraint pk_location_type primary key (id))
;

create sequence course_seq;

create sequence location_seq;

create sequence location_type_seq;

alter table course add constraint fk_course_location_1 foreign key (location_id) references location (id) on delete restrict on update restrict;
create index ix_course_location_1 on course (location_id);
alter table location add constraint fk_location_locationType_2 foreign key (location_type_id) references location_type (id) on delete restrict on update restrict;
create index ix_location_locationType_2 on location (location_type_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists course;

drop table if exists location;

drop table if exists location_type;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists course_seq;

drop sequence if exists location_seq;

drop sequence if exists location_type_seq;

