insert into user (username, password, email,realname,phone_number) values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin@','김민주','010-7638-0028');
insert into user (username, password, email,realname,phone_number) values ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user@','테스트이름','010-0000-0000');
insert into user (username, password, email,realname,phone_number) values ('min', '$2a$10$sg2gYiGFTPvqoebJkC/f4erLz.Q8x3ljfAr0P.jeRzhW3uyov56iu	', 'hibye@naver.com	','신혜민','010-0000-0000');
insert into user (username, password, email,realname,phone_number) values ('jin', '$2a$10$sg2gYiGFTPvqoebJkC/f4erLz.Q8x3ljfAr0P.jeRzhW3uyov56iu	', 'sujin@naver.com	','안수진','010-0000-0000');


insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into user_authority (user_id, authority_name) values (1, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (2, 'ROLE_USER');

insert into store (user_id, code, store_name, store_addr) values (1, 75124324, 'momstouch', 'seoul');

insert into work_info(user_id, store_id, wage, account, mon_start, mon_end, tue_start, tue_end, wed_start, wed_end, thu_start, thu_end, fri_start, fri_end, sat_start, sat_end, sun_start, sun_end) values (3, 1, 0, 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'null','null', 'null', 'null', 'null');
insert into work_info(user_id, store_id, wage, account, mon_start, mon_end, tue_start, tue_end, wed_start, wed_end, thu_start, thu_end, fri_start, fri_end, sat_start, sat_end, sun_start, sun_end) values (4, 1, 0, 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'null','null', 'null', 'null', 'null');
