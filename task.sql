
create table task (
       id int8 not null,
        descricao varchar(255),
        dtConclusao timestamp,
        dtCriacao timestamp,
        dtUpdate timestamp,
        status int4 not null,
        titulo varchar(255) not null,
        primary key (id)
    );