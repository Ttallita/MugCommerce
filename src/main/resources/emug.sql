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

DROP TABLE IF EXISTS "categorias" CASCADE;
CREATE TABLE categorias (
    ctg_id   serial       NOT NULL,
    ctg_nome varchar(255) NOT NULL
);

DROP TABLE IF EXISTS "fabricantes" CASCADE;
CREATE TABLE fabricantes (
    fab_id   serial       NOT NULL,
    fab_nome varchar(255) NOT NULL
);

DROP TABLE IF EXISTS "grupos_precificacao" CASCADE;
CREATE TABLE grupos_precificacao (
    grp_id           serial        NOT NULL,
    grp_nome         varchar(255)  NOT NULL,
    grp_margem_lucro numeric(8, 2) NOT NULL
);


DROP TABLE IF EXISTS "produtos" CASCADE;
CREATE TABLE produtos (
    pro_id                 serial        NOT NULL,
    pro_fab_id             int           NOT NULL,
    pro_grp_id             int           NOT NULL,
    pro_nome               varchar(255)  NOT NULL,
    pro_valor_compra       numeric(8, 2) NOT NULL,
    pro_valor_venda        numeric(8, 2) NOT NULL,
    pro_descricao          varchar(255)  NOT NULL,
    pro_material           varchar(255)  NOT NULL,
    pro_cod_barras         varchar(255)  NOT NULL
);

DROP TABLE IF EXISTS "categorias_produtos" CASCADE;
CREATE TABLE categorias_produtos(
    ctp_pro_id int NOT NULL,
    ctp_ctg_id int NOT NULL
);



