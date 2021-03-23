SET foreign_key_checks = 0;

DELETE FROM cidade;
DELETE FROM cozinha;
DELETE FROM estado;
DELETE FROM forma_pagamento;
DELETE FROM grupo;
DELETE FROM grupo_permissao;
DELETE FROM permissao;
DELETE FROM produto;
DELETE FROM restaurante;
DELETE FROM restaurante_forma_pagamento;
DELETE FROM usuario;
DELETE FROM usuario_grupo;

SET foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinha (id, nome) VALUES (2, 'Indiana');
INSERT INTO cozinha (id, nome) VALUES (3, 'Brasileira');
INSERT INTO cozinha (id, nome) VALUES (4, 'Italiana');

INSERT INTO estado (nome) VALUES ('Amazonas');
INSERT INTO estado (nome) VALUES ('Sao Paulo');

INSERT INTO cidade (nome, estado_id) VALUES ('Manaus', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Campinas', 2);

INSERT INTO restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_complemento, endereco_bairro, ativo) VALUES ('Bela Mineira', 10, 1, utc_timestamp, utc_timestamp, 1, '69033-050', 'Rua Prof Clotilde', null, 'Sao Jorge', true);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) VALUES ('Picanha Mania', 20, 4, utc_timestamp, utc_timestamp, true);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) VALUES ('Bobs', 20, 2, utc_timestamp, utc_timestamp, true);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_complemento, endereco_bairro, ativo) VALUES ('Bufalo', 10, 2, utc_timestamp, utc_timestamp, 1, '69033-050', 'Rua Prof Clotilde', null, 'Sao Jorge', true);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_complemento, endereco_bairro, ativo) VALUES ('Barolo', 10, 3, utc_timestamp, utc_timestamp, 1, '69033-050', 'Rua Prof Clotilde', null, 'Sao Jorge', true);

INSERT INTO forma_pagamento (id, descricao) VALUES (1, 'Cartao de crédito');
INSERT INTO forma_pagamento (id, descricao) VALUES (2, 'Cartao de débito');
INSERT INTO forma_pagamento (id, descricao) VALUES (3, 'Dinheiro');

INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 3);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 3);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 3);