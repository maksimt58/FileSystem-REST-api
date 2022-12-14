insert into users (first_name, last_name) values ('Maks', 'Tata');
insert into users (first_name, last_name) values ('John', 'Dou');
insert into users (first_name, last_name) values ('Eugene', 'Proselyte');

insert into files (file_name, file_path) values ('picture', 'C:\\Program Files');
insert into files (file_name, file_path) values ('film', 'C:\\Program Files x86');

insert into events (event_name, file_id) values ('UPLOAD', 1);
insert into events (event_name, file_id) values ('DOWNLOAD', 2);

insert into users_events (user_id, event_id) values (2 , 1);
insert into users_events (user_id, event_id) values (1 , 2);
insert into users_events (user_id, event_id) values (3 , 2);