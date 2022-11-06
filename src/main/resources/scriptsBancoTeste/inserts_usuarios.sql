--- CONFIGURAÇÃO DO BANCO DE TESTES

-- Cliente
-- Senha = "Teste@123"
INSERT INTO usuarios (usr_email, usr_senha, usr_tipo, usr_ativo)
VALUES ('login@teste.com', 'bf16fadfbdf1f8450d143857c08a8acb70033cc48d18b22037e18a8ca7081f636a0b805e5241e5e30959a21ee773c3293251a34923a8e77925dd140116f25293', 'CLIENTE', true);

INSERT INTO clientes (cli_usr_id,cli_nome,  cli_sobrenome,cli_cpf,cli_dt_nasc,cli_genero,cli_telefone_num,cli_telefone_ddd,cli_telefone_tp,cli_rank)
VALUES (1,'Maria','dos Anjos','303.799.550-59','2001-12-12','Feminino','50678-0441','19','CELULAR',NULL);

INSERT INTO cartoes (crt_cli_usr_id, crt_numero, crt_bandeira, crt_nome_impresso, crt_mes_validade, crt_ano_validade, crt_cod_seg, crt_preferencial) VALUES
	 (1,'1111 1111 1111 1111','Elo','Alexandre Portugal','01','2031','111',true),
	 (1,'2222 2222 2222 2222','Mastercard','Bianca Jade','02','2032','222',false),
	 (1,'3333 3333 3333 3333','American Express','Carlos Sergipe','03','2033','333',false),
	 (1,'4444 4444 4444 4444','Hipercard','Daniel Salvador','04','2034','444',false),
	 (1,'5555 5555 5555 5555','Visa','Eduarda Salmão','05','2035','555',false),
	 (1,'6666 6666 6666 6666','Elo','Fábio Dom Jão','06','2036','666',false);


INSERT INTO enderecos (end_cli_usr_id,end_tp,end_apelido,end_tp_residencia,end_tp_logradouro,end_logradouro,end_num,end_bairro,end_cep,end_cidade,end_estado,end_observacao)
VALUES  (1,'COBRANCA_ENTREGA','Minha casa','Sobrado','Avenida','Pingo d''água','586','Fernandes','95044-120','Mogi das Cruzes','São Paulo',''),
        (1,'COBRANCA_ENTREGA','Apartamento A','Apartamento','Alameda','Águas sagradas','111','Amélia Soares','11111-111','Acrelândia','Acre',''),
        (1,'COBRANCA','Sobrado B','Sobrado','Beco','Bonda dourada','222','Barões','22222-222','Bonito','Bahia',''),
        (1,'ENTREGA','Casa C','Casa','Comunidade','Coroa Lorenzo','333','Carmo Nogueira','33333-333','Camocim','Ceará',''),
        (1,'COBRANCA_ENTREGA','Casa D','Casa','Distrito','Dom Terciário Quarto','444','Dama Claudite','44444-444','Brasília','Distrito Federal','');

INSERT INTO cupons (cpm_cli_usr_id, cpm_nome, cpm_tp, cpm_valor, cpm_dt_validade, cpm_descricao) VALUES
(1, 'Cupom aniversariante', 'PROMOCIONAL', 20.0, '2023-01-01', 'Se presenteie bla bla bla...'),
(1, 'Cupom natal', 'PROMOCIONAL', 30.0, '2023-01-01', 'HOU! HOU! HOU!..........'),
(1, 'Cupom halloween', 'PROMOCIONAL', 50.0, '2023-01-01', 'Buuuuuuuuuuuuuuu...');


-- Administrador
-- Senha = "SenhaADM@123"
INSERT into usuarios (usr_email, usr_senha, usr_tipo, usr_ativo)
values ('loginADM@teste.com', '1c12fe70e92c3544d88dec0381f6e2e1e245e201078e6c6cf23ee2c5e6523e57d6005dbd7b229beba03c373639bb2f7a316664c85e8bc8a73c397e546522779d', 'ADMINISTRADOR', true);

