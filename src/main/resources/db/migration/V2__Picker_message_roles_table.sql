create table if not exists `picker_message_roles`
(
    `id`                bigint unsigned not null auto_increment,
    `picker_message_id` bigint unsigned not null,
    `role_id`           bigint unsigned not null,
    `created_at`        timestamp       not null default current_timestamp,
    `updated_at`        timestamp       not null default current_timestamp on update current_timestamp,
    primary key (`id`),
    foreign key (`picker_message_id`) references `picker_messages` (`id`),
    index (`picker_message_id`)
);