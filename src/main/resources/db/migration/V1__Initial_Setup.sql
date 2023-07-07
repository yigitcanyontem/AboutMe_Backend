create table country (
                                id integer primary key not null,
                                iso character varying(255),
                                iso3 character varying(255),
                                name character varying(255),
                                nicename character varying(255),
                                numcode integer,
                                phonecode integer
);


create table users (
                              id BIGSERIAL primary key not null,
                              date_of_birth date not null,
                              email character varying(255) not null,
                              first_name character varying(255) not null,
                              last_name character varying(255) not null,
                              username character varying(255) not null,
                              country integer not null,
                              foreign key (country) references country (id)
                                  match simple on update no action on delete no action
);

create table description (
                                    usersid integer primary key not null,
                                    description character varying(255)
);

create table favalbums (
                                  id BIGSERIAL primary key not null,
                                  albumid character varying(255) not null,
                                  usersid integer not null,
                                  foreign key (usersid) references users (id)
                                      match simple on update no action on delete no action
);

create table favbooks (
                                 id BIGSERIAL primary key not null,
                                 bookid character varying(255) not null,
                                 usersid integer not null,
                                 foreign key (usersid) references users (id)
                                     match simple on update no action on delete no action
);

create table favmovie (
                                 id BIGSERIAL primary key not null,
                                 movieid integer not null,
                                 usersid integer not null,
                                 foreign key (usersid) references users (id)
                                     match simple on update no action on delete no action
);

create table favshows (
                                 id BIGSERIAL primary key not null,
                                 showid integer not null,
                                 usersid integer not null,
                                 foreign key (usersid) references users (id)
                                     match simple on update no action on delete no action
);

create table passwords (
                                  id BIGSERIAL primary key not null,
                                  password character varying(255) not null,
                                  usersid integer not null,
                                  foreign key (usersid) references users (id)
                                      match simple on update no action on delete no action
);

create table socialmedia (
                                    id BIGSERIAL primary key not null,
                                    instagram character varying(255),
                                    linkedin character varying(255),
                                    pinterest character varying(255),
                                    twitter character varying(255),
                                    usersid integer not null,
                                    foreign key (usersid) references public.users (id)
                                        match simple on update no action on delete no action
);

CREATE SEQUENCE user_id_seq START 1;
create unique index user_email_unique on users using btree (email);
create unique index user_username_unique on users using btree (username);

