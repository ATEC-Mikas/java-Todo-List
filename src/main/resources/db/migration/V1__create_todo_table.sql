create table todo (
	id int unsigned auto_increment primary key,
    `name` varchar(100) not null,
    `description` varchar(256) not null,
    `status` bit(1) not null default false,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp
)ENGINE=InnoDB;