CREATE TABLE users
(
    id INT NOT NULL auto_increment,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE files
(
    id INT NOT NULL auto_increment,
    file_name varchar(255) NOT NULL,
    file_path varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE events
(
    id INT NOT NULL auto_increment,
    event_name varchar(255) NOT NULL,
    file_id INT NOT NULL,
    foreign key (file_id) references files (id) ON DELETE CASCADE on update cascade,
    PRIMARY KEY (id)
);

CREATE TABLE users_events
(
    user_id  INT NOT NULL,
    event_id INT NOT NULL,
    foreign key (user_id) references users (id) ON DELETE CASCADE on update cascade,
    foreign key (event_id) references events (id) ON DELETE CASCADE on update cascade,
    UNIQUE (user_id, event_id)
);

