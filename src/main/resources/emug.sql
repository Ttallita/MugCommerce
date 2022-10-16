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
    pro_cod_barras         varchar(255)  NOT NULL,
    pro_imagem             varchar       NOT NULL,
    pro_ativo              boolean       NOT NULL
);

DROP TABLE IF EXISTS "categorias_produtos" CASCADE;
CREATE TABLE categorias_produtos(
    ctp_pro_id int NOT NULL,
    ctp_ctg_id int NOT NULL
);

DROP TABLE IF EXISTS "produtos_status" CASCADE;
CREATE TABLE produtos_status (
    pst_id            serial       NOT NULL,
    pst_categoria     varchar(255) NOT NULL,
    pst_prod_id       int          NOT NULL,
    pst_justificativa varchar(255) NOT NULL
);

DROP TABLE IF EXISTS "auditoria" CASCADE;
CREATE TABLE auditoria (
    aud_id     serial       NOT NULL,
    aud_data   timestamp    NOT NULL,
    aud_usr_id int          NOT NULL,
    aud_tipo   varchar(255) NOT NULL,
    aud_dados  jsonb        NOT NULL
);

DROP TABLE IF EXISTS "estoque" CASCADE;
CREATE TABLE estoque (
    est_id     serial  NOT NULL,
    est_pro_id int     NOT NULL,
    est_quant  int     NOT NULL
);

DROP TABLE IF EXISTS "estoque_historico" CASCADE;
CREATE TABLE estoque_historico (
    est_hist_id          serial        NOT NULL,
    est_hist_est_id      int           NOT NULL,
    est_hist_quant       int           NOT NULL,
    est_hist_data        timestamp     NOT NULL,
    est_hist_valor_custo numeric(8, 2) NOT NULL,
    est_hist_fornecedor  varchar(255)  NOT NULL
);

-- Constraints de primary key
ALTER TABLE usuarios
    ADD CONSTRAINT pk_usr PRIMARY KEY (usr_id);

ALTER TABLE clientes
    ADD CONSTRAINT pk_cli PRIMARY KEY (cli_usr_id);

ALTER TABLE cartoes
    ADD CONSTRAINT pk_crt PRIMARY KEY (crt_id);

ALTER TABLE enderecos
    ADD CONSTRAINT pk_end PRIMARY KEY (end_id);

ALTER TABLE categorias
    ADD CONSTRAINT pk_ctg PRIMARY KEY (ctg_id);

ALTER TABLE fabricantes
    ADD CONSTRAINT pk_fab PRIMARY KEY (fab_id);

ALTER TABLE grupos_precificacao
    ADD CONSTRAINT pk_grp PRIMARY KEY(grp_id);

ALTER TABLE produtos
    ADD CONSTRAINT pk_pro PRIMARY KEY (pro_id);

ALTER TABLE categorias_produtos
    ADD CONSTRAINT pk_ctp PRIMARY KEY (ctp_pro_id, ctp_ctg_id);

ALTER TABLE produtos_status
    ADD CONSTRAINT pk_pst PRIMARY KEY (pst_id);

ALTER TABLE estoque
    ADD CONSTRAINT pk_est PRIMARY KEY (est_id);

ALTER TABLE estoque_historico
    ADD CONSTRAINT pk_est_hist PRIMARY KEY (est_hist_id);

-- Constraints de foreign key
ALTER TABLE clientes
    ADD CONSTRAINT fk_cli_usr FOREIGN KEY (cli_usr_id)
        REFERENCES usuarios (usr_id);

ALTER TABLE cartoes
    ADD CONSTRAINT fk_crt_cli_usr FOREIGN KEY (crt_cli_usr_id)
        REFERENCES clientes (cli_usr_id);

ALTER TABLE cupons
    ADD CONSTRAINT fk_cpm_cli_usr FOREIGN KEY (cpm_cli_usr_id)
        REFERENCES clientes (cli_usr_id);

ALTER TABLE enderecos
    ADD CONSTRAINT fk_end_cli_usr FOREIGN KEY (end_cli_usr_id)
        REFERENCES clientes (cli_usr_id);

ALTER TABLE produtos
    ADD CONSTRAINT fk_pro_fab FOREIGN KEY (pro_fab_id)
        REFERENCES fabricantes (fab_id);

ALTER TABLE produtos
    ADD CONSTRAINT fk_pro_grp FOREIGN KEY (pro_grp_id)
        REFERENCES grupos_precificacao (grp_id);

ALTER TABLE produtos
    ADD CONSTRAINT fk_pro_pst FOREIGN KEY (pro_pst_id)
        REFERENCES produtos_status (pst_id);

ALTER TABLE categorias_produtos
    ADD CONSTRAINT fk_ctp_pro FOREIGN KEY (ctp_pro_id)
        REFERENCES produtos (pro_id);

ALTER TABLE categorias_produtos
    ADD CONSTRAINT fk_ctp_ctg FOREIGN KEY (ctp_ctg_id)
        REFERENCES categorias (ctg_id);

ALTER TABLE produtos_status
    ADD CONSTRAINT fk_prod_id FOREIGN KEY (pst_prod_id)
        REFERENCES produtos (pro_id);

ALTER TABLE estoque
    ADD CONSTRAINT fk_prod_id FOREIGN KEY (est_pro_id)
        REFERENCES produtos (pro_id);

ALTER TABLE estoque_historico
    ADD CONSTRAINT fk_est_id FOREIGN KEY (est_hist_est_id)
        REFERENCES estoque (est_id);

