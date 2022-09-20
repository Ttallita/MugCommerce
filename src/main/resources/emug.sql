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
    crt_cod_seg       varchar(3)   NOT NULL,
    crt_preferencial  boolean      NOT NULL
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
    end_observacao    varchar(255)
);


--- CONFIGURAÇÃO DO BANCO DE TESTES

--- Login

-- Cliente
-- Senha = "Senha@123"
INSERT into usuarios (usr_email, usr_senha, usr_tipo, usr_ativo)
values ('login@teste.com', 'bf16fadfbdf1f8450d143857c08a8acb70033cc48d18b22037e18a8ca7081f636a0b805e5241e5e30959a21ee773c3293251a34923a8e77925dd140116f25293', 'CLIENTE', true);

-- Administrador
-- Senha = "SenhaADM@123"
INSERT into usuarios (usr_email, usr_senha, usr_tipo, usr_ativo)
values ('loginADM@teste.com', '1c12fe70e92c3544d88dec0381f6e2e1e245e201078e6c6cf23ee2c5e6523e57d6005dbd7b229beba03c373639bb2f7a316664c85e8bc8a73c397e546522779d', 'ADMINISTRADOR', true);

