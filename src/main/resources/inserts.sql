-- Configuração sistema

-- Insert de categorias de canecas
INSERT INTO categorias(ctg_nome)
values ('Anime'),
       ('Geek'),
       ('Filmes'),
       ('Series'),
       ('Esportes'),
       ('Celebridades'),
       ('Games'),
       ('Literatura'),
       ('Lugares'),
       ('Pet');

INSERT INTO fabricantes(fab_nome)
values ('Amazon'),
       ('Autoral'),
       ('Sua Caneca'),
       ('Dumont'),
       ('Kosmos');

INSERT INTO grupos_precificacao(grp_nome, grp_margem_lucro)
values ('Margem %4', 4),
       ('Margem %6', 6),
       ('Margem %8', 8);

--- CONFIGURAÇÃO DO BANCO DE TESTES

-- Cliente
-- Senha = "Teste@123"
INSERT INTO usuarios (usr_email, usr_senha, usr_tipo, usr_ativo)
VALUES ('login@teste.com', 'bf16fadfbdf1f8450d143857c08a8acb70033cc48d18b22037e18a8ca7081f636a0b805e5241e5e30959a21ee773c3293251a34923a8e77925dd140116f25293', 'CLIENTE', true);

INSERT INTO clientes (cli_usr_id,cli_nome,  cli_sobrenome,cli_cpf,cli_dt_nasc,cli_genero,cli_telefone_num,cli_telefone_ddd,cli_telefone_tp,cli_rank)
VALUES (1,'Maria','dos Anjos','303.799.550-59','2001-12-12','Feminino','50678-0441','19','CELULAR',NULL);

INSERT INTO enderecos (end_cli_usr_id,end_tp,end_apelido,end_tp_residencia,end_tp_logradouro,end_logradouro,end_num,end_bairro,end_cep,end_cidade,end_estado,end_observacao)
VALUES (1,'COBRANCA_ENTREGA','Minha casa','Sobrado','Avenida','Pingo d''água','586','Fernandes','95044-120','Mogi das Cruzes','São Paulo','');

INSERT INTO cupons (cpm_id, cpm_cli_usr_id, cpm_vnd_id, cpm_nome, cpm_tp, cpm_valor, cpm_dt_validade, cpm_descricao) VALUES
(1, 1, NULL, 'Cupom aniversariante', 'PROMOCIONAL', 20.0, '2023-01-01', 'Se presenteie bla bla bla...'),
(2, 1, NULL, 'Cupom natal', 'PROMOCIONAL', 30.0, '2023-01-01', 'HOU! HOU! HOU!..........');


-- Administrador
-- Senha = "SenhaADM@123"
INSERT into usuarios (usr_email, usr_senha, usr_tipo, usr_ativo)
values ('loginADM@teste.com', '1c12fe70e92c3544d88dec0381f6e2e1e245e201078e6c6cf23ee2c5e6523e57d6005dbd7b229beba03c373639bb2f7a316664c85e8bc8a73c397e546522779d', 'ADMINISTRADOR', true);

