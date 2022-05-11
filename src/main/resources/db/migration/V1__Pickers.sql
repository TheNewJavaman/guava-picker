create table if not exists `pickers`
(
    `id`         bigint unsigned not null auto_increment,
    `message_id` bigint unsigned not null,
    `message`    text            not null,
    `created_at` timestamp       not null default current_timestamp,
    `updated_at` timestamp       not null default current_timestamp on update current_timestamp,
    primary key (`id`),
    index (`message_id`)
);