INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinha (id, nome) VALUES (2, 'Indiana');

INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Bela Mineira', 10, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Picanha Mania', 20, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Bobs', 20, 2);

INSERT INTO estado (nome) VALUES ('Amazonas');
INSERT INTO estado (nome) VALUES ('Sao Paulo');

INSERT INTO cidade (nome, estado_id) VALUES ('Manaus', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Campinas', 2);