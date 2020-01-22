INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinha (id, nome) VALUES (2, 'Indiana');

INSERT INTO estado (nome) VALUES ('Amazonas');
INSERT INTO estado (nome) VALUES ('Sao Paulo');

INSERT INTO cidade (nome, estado_id) VALUES ('Manaus', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Campinas', 2);

INSERT INTO restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_complemento, endereco_bairro) VALUES ('Bela Mineira', 10, 1, 1, '69033-050', 'Rua Prof Clotilde', null, 'Sao Jorge');
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Picanha Mania', 20, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Bobs', 20, 2);

INSERT INTO forma_pagamento (id, descricao) VALUES (1, 'Cartao de crédito');
INSERT INTO forma_pagamento (id, descricao) VALUES (2, 'Cartao de débito');
INSERT INTO forma_pagamento (id, descricao) VALUES (3, 'Dinheiro');

INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 3);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 3);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 3);