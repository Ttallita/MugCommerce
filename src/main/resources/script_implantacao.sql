-- Configuração sistema (RNF0013 - Cadastro de domínios)

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