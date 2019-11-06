create table todo_comment (
    	id int unsigned auto_increment primary key,
        `comment` varchar(256) not null,
        `todo_id` int unsigned,
        created_at timestamp not null default current_timestamp,
        updated_at timestamp not null default current_timestamp on update current_timestamp,
        CONSTRAINT FK_todo_comment_todo FOREIGN KEY (todo_id) REFERENCES todo (id) on delete cascade
       )ENGINE=InnoDB;