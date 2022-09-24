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

--- Login

-- Cliente
    -- Senha = "Senha@123"
INSERT into usuarios (usr_email, usr_senha, usr_tipo, usr_ativo)
values ('login@teste.com', 'bf16fadfbdf1f8450d143857c08a8acb70033cc48d18b22037e18a8ca7081f636a0b805e5241e5e30959a21ee773c3293251a34923a8e77925dd140116f25293', 'CLIENTE', true);

-- Administrador
-- Senha = "SenhaADM@123"
INSERT into usuarios (usr_email, usr_senha, usr_tipo, usr_ativo)
values ('loginADM@teste.com', '1c12fe70e92c3544d88dec0381f6e2e1e245e201078e6c6cf23ee2c5e6523e57d6005dbd7b229beba03c373639bb2f7a316664c85e8bc8a73c397e546522779d', 'ADMINISTRADOR', true);
