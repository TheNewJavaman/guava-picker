create table if not exists `editors`
(
    `id`         bigint unsigned not null auto_increment,
    `message_id` bigint unsigned not null,
    `picker_id`  bigint unsigned not null,
    `created_at` timestamp       not null default current_timestamp,
    `updated_at` timestamp       not null default current_timestamp on update current_timestamp,
    primary key (`id`),
    index (`message_id`),
    foreign key (`picker_id`) references `pickers` (`id`)
);