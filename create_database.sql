

create table task (
       id bigint not null,
        criada date,
        descricao varchar(255),
        finalizada date,
        status integer not null,
        titulo varchar(255) not null,
        primary key (id)
);