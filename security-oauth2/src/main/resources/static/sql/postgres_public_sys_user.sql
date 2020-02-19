create table sys_user
(
    id       integer      not null
        constraint sys_user_pkey
            primary key,
    username varchar(50)  not null,
    email    varchar(50)  not null,
    password varchar(250) not null,
    phone    varchar(11),
    address  varchar(100)
);

alter table sys_user
    owner to postgres;

INSERT INTO public.sys_user (id, username, email, password, phone, address) VALUES (1, 'admin', 'admin@test.com', '$2a$10$3k/VyEk97d79Go9l83zUEe1lYLVVGgq71JBdZ8djI1BBg6SLw5kGW', '11111111111', 'address');

create sequence sys_user_id_seq
start with 1
increment by 1
no minvalue
no maxvalue
cache 1;

alter table sys_user alter column id set default nextval('sys_user_id_seq');