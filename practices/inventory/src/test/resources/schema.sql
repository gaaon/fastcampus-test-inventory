drop table if exists inventory;
create table if not exists inventory
(
    id         bigint       not null auto_increment,
    item_id    varchar(255) not null,
    stock      bigint       not null,
    created_at timestamp    not null,
    updated_at timestamp    not null,
    primary key (id)
);