create table if not exists `picker_roles`
(
    `id`         bigint unsigned not null auto_increment,
    `role_id`    bigint unsigned not null,
    `picker_id`  bigint unsigned not null,
    `created_at` timestamp       not null default current_timestamp,
    `updated_at` timestamp       not null default current_timestamp on update current_timestamp,
    primary key (`id`),
    index (`role_id`),
    foreign key (`picker_id`) references `pickers` (`id`)
);