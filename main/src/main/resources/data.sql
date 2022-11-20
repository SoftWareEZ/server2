insert into user (username, password, email,realname,phone_number) values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin@','김민주','010-7638-0028');
insert into user (username, password, email,realname,phone_number) values ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user@','테스트이름','010-0000-0000');

insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into user_authority (user_id, authority_name) values (1, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (2, 'ROLE_USER');

insert into commute (commute_id, user_id, store_id, date, start, end) values (1, 1, 1, '2022-11-21', '11:21', '12:22');
insert into commute (commute_id, user_id, store_id, date, start, end) values (2, 1, 1, '2022-11-22', '11:21', '12:22');
insert into commute (commute_id, user_id, store_id, date, start, end) values (3, 1, 2, '2022-11-23', '11:21', '12:22');
insert into commute (commute_id, user_id, store_id, date, start, end) values (4, 2, 1, '2022-11-21', '11:21', '12:22');