insert into user (username, password, email,realname,phone_number) values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin@','김민주','010-7638-0028');
insert into user (username, password, email,realname,phone_number) values ('momju', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'user@','김맘주','010-0000-0000');
insert into user (username, password, email,realname,phone_number) values ('min', '$2a$10$sg2gYiGFTPvqoebJkC/f4erLz.Q8x3ljfAr0P.jeRzhW3uyov56iu', 'hibye@naver.com	','신혜민','010-0000-0000');
insert into user (username, password, email,realname,phone_number) values ('jin', '$2a$10$sg2gYiGFTPvqoebJkC/f4erLz.Q8x3ljfAr0P.jeRzhW3uyov56iu', 'sujin@naver.com	','안수진','010-0000-0000');
insert into user (username, password, email,realname,phone_number) values ('you', '$2a$10$sg2gYiGFTPvqoebJkC/f4erLz.Q8x3ljfAr0P.jeRzhW3uyov56iu', 'you1004@daum.net','조유정','010-0000-0000');
insert into user (username, password, email,realname,phone_number) values ('minji', '$2a$10$sg2gYiGFTPvqoebJkC/f4erLz.Q8x3ljfAr0P.jeRzhW3uyov56iu', 'minji@google.com','김민지','010-0000-0000');
insert into user (username, password, email,realname,phone_number) values ('jangga', '$2a$10$sg2gYiGFTPvqoebJkC/f4erLz.Q8x3ljfAr0P.jeRzhW3uyov56iu', 'jangga@gmail.com','장가은','010-0000-1112');
insert into user (username, password, email,realname,phone_number) values ('minwoin', '$2a$10$sg2gYiGFTPvqoebJkC/f4erLz.Q8x3ljfAr0P.jeRzhW3uyov56iu', 'minwoin@naver.com','미노이','010-5471-0900');
insert into user (username, password, email,realname,phone_number) values ('hang', '$2a$10$sg2gYiGFTPvqoebJkC/f4erLz.Q8x3ljfAr0P.jeRzhW3uyov56iu', 'mrzo@yu.ac.kr','조행래','010-0000-0000');
insert into user (username, password, email,realname,phone_number) values ('park', '$2a$10$sg2gYiGFTPvqoebJkC/f4erLz.Q8x3ljfAr0P.jeRzhW3uyov56iu', 'duck99@gmail.com','박영덕','010-0000-0000');
insert into user (username, password, email,realname,phone_number) values ('jong', '$2a$10$sg2gYiGFTPvqoebJkC/f4erLz.Q8x3ljfAr0P.jeRzhW3uyov56iu', 'jonghee@daum.net','윤종희','010-0000-0000');
insert into user (username, password, email,realname,phone_number) values ('kkwack', '$2a$10$sg2gYiGFTPvqoebJkC/f4erLz.Q8x3ljfAr0P.jeRzhW3uyov56iu', 'kkwack@hanmail.net','곽종욱','010-0000-0000');



insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into user_authority (user_id, authority_name) values (1, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (2, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (2, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (3, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (3, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (4, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (4, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (5, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (5, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (6, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (6, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (7, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (7, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (8, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (8, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (9, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (9, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (10, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (10, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (11, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (11, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (12, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (12, 'ROLE_ADMIN');


insert into store (user_id, code, store_name, store_addr) values (1, 75124324, '맘스터치 영대점', '경북 경산시 대동');
insert into store (user_id, code, store_name, store_addr) values (1, 40487753, '김해뒷고기 영대점', '경북 경산시 조영동');
insert into store (user_id, code, store_name, store_addr) values (2, 11211954, '스타벅스 영대점', '경북 경산시 조영동');
insert into store (user_id, code, store_name, store_addr) values (2, 02478963, '서브웨이 영대점', '경북 경산시 조영동');
insert into store (user_id, code, store_name, store_addr) values (2, 31488791, '맥도날드 신매점', '대구 수성구 시지동');


insert into work_info(user_id, store_id, wage, account, activated, mon_start, mon_end, tue_start, tue_end, wed_start, wed_end, thu_start, thu_end, fri_start, fri_end, sat_start, sat_end, sun_start, sun_end) values (1, 2, 10000, '1111-22222-44444', 0,'null', 'null', '10:00', '16:00', '14:30', '18:00', 'null', 'null', '09:00', '13:00','09:00', '13:00', 'null', 'null');
insert into work_info(user_id, store_id, wage, account, activated, mon_start, mon_end, tue_start, tue_end, wed_start, wed_end, thu_start, thu_end, fri_start, fri_end, sat_start, sat_end, sun_start, sun_end) values (3, 2, 9160, '3333-8989-14444', 0, '09:00', '13:00', '09:00', '13:00', '09:00', '13:00', '09:00', '15:00', 'null', 'null','null', 'null', 'null', 'null');
insert into work_info(user_id, store_id, wage, account, activated, mon_start, mon_end, tue_start, tue_end, wed_start, wed_end, thu_start, thu_end, fri_start, fri_end, sat_start, sat_end, sun_start, sun_end) values (4, 2, 9160, '1004-44444-5555', 2, '17:00', '23:00', 'null', 'null', 'null', 'null', 'null', 'null', 'null', 'null','17:00', '22:00', '17:00', '22:00');
insert into work_info(user_id, store_id, wage, account, activated, mon_start, mon_end, tue_start, tue_end, wed_start, wed_end, thu_start, thu_end, fri_start, fri_end, sat_start, sat_end, sun_start, sun_end) values (5, 1, 9300, '7777-74217-47411', 1,'10:00', '16:00', '10:00', '16:00', '14:30', '18:00', 'null', 'null', '09:00', '13:00','09:00', '13:00', 'null', 'null');
insert into work_info(user_id, store_id, wage, account, activated, mon_start, mon_end, tue_start, tue_end, wed_start, wed_end, thu_start, thu_end, fri_start, fri_end, sat_start, sat_end, sun_start, sun_end) values (6, 1, 10000, '010-5474100-10', 1,'null', 'null', '10:00', '16:00', '14:30', '18:00', 'null', 'null', '09:00', '13:00','09:00', '13:00', 'null', 'null');
insert into work_info(user_id, store_id, wage, account, activated, mon_start, mon_end, tue_start, tue_end, wed_start, wed_end, thu_start, thu_end, fri_start, fri_end, sat_start, sat_end, sun_start, sun_end) values (7, 1, 9500, '21110-20-14445', 1,'null', 'null', '10:00', '16:00', '14:30', '18:00', 'null', 'null', '09:00', '13:00','09:00', '13:00', 'null', 'null');

insert into commute (user_id, store_id, year, month, day, start, end, time) values ('1', '1', '2022','10','1', '13:00', '14:00', 60);
insert into commute (user_id, store_id, year, month, day, start, end, time) values ('1', '1', '2022','11','11', '13:00', '14:00', 60);
insert into commute (user_id, store_id, year, month, day, start, end, time) values ('1', '1', '2022', '11', '12', '11:00', '12:00', 60);
insert into commute (user_id, store_id, year, month, day, start, end, time) values ('1', '1', '2022', '11', '12', '13:00', '15:00', 120);
insert into commute (user_id, store_id, year, month, day, start, end, time) values ('1', '2', '2022', '11', '13', '11:00', '12:00', 60);
insert into commute (user_id, store_id, year, month, day, start, end, time) values ('4', '2', '2022','11','14', '13:00', '14:00', 60);
insert into commute (user_id, store_id, year, month, day, start, end, time) values ('5', '1', '2022','11','15', '13:00', '14:00', 60);
insert into commute (user_id, store_id, year, month, day, start, end, time) values ('5', '1', '2022','11','16', '13:00', '14:00', 60);
insert into commute (user_id, store_id, year, month, day, start, end, time) values ('6', '1', '2022','11','17', '13:00', '14:00', 60);