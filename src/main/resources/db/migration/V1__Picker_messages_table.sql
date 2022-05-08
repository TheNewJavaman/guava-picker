create table if not exists `picker_messages`
(
    `id`         bigint unsigned not null auto_increment,
    `message_id` bigint unsigned not null,
    `header`     text                     default null,
    `created_at` timestamp       not null default current_timestamp,
    `updated_at` timestamp       not null default current_timestamp on update current_timestamp,
    primary key (`id`)
);