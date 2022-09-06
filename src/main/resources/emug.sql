DROP TABLE IF EXISTS "usuarios" CASCADE;
CREATE TABLE usuarios (
    usr_id    serial       NOT NULL,
    usr_email varchar(255) NOT NULL,
    usr_senha varchar(255) NOT NULL,
    usr_tipo  varchar(255) NOT NULL,
    usr_ativo boolean      NOT NULL
);

DROP TABLE IF EXISTS "clientes" CASCADE;
CREATE TABLE clientes (
    cli_usr_id       integer     NOT NULL,
    cli_nome         varchar(15) NOT NULL,
    cli_sobrenome    varchar(45) NOT NULL,
    cli_cpf          varchar(14) NOT NULL,
    cli_dt_nasc      date        NOT NULL,
    cli_genero       varchar(10) NOT NULL,
    cli_telefone_num varchar(10) NOT NULL,
    cli_telefone_ddd varchar(2)  NOT NULL,
    cli_telefone_tp  varchar(11) NOT NULL,
    cli_rank         int
);

DROP TABLE IF EXISTS "cartoes" CASCADE;
CREATE TABLE cartoes(
    crt_id            serial       NOT NULL,
    crt_cli_usr_id    int          NOT NULL,
    crt_numero        varchar(19)  NOT NULL,
    crt_bandeira      varchar(255) NOT NULL,
    crt_nome_impresso varchar(255) NOT NULL,
    crt_mes_validade  numeric(2)   NOT NULL,
    crt_ano_validade  numeric(4)   NOT NULL,
    crt_cod_seg       varchar(3)   NOT NULL
);

DROP TABLE IF EXISTS "enderecos" CASCADE;
CREATE TABLE enderecos (
    end_id            serial       NOT NULL,
    end_cli_usr_id    int          NOT NULL,
    end_tp            varchar(20)  NOT NULL,
    end_apelido       varchar(255) NOT NULL,
    end_tp_residencia varchar(255) NOT NULL,
    end_tp_logradouro varchar(255) NOT NULL,
    end_logradouro    varchar(255) NOT NULL,
    end_num           varchar(10)  NOT NULL,
    end_bairro        varchar(255) NOT NULL,
    end_cep           varchar(9)   NOT NULL,
    end_cidade        varchar(255) NOT NULL,
    end_estado        varchar(255) NOT NULL,
    end_pais          varchar(255) NOT NULL,
    end_observacao    varchar(255)
);