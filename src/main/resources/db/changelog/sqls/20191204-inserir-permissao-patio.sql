INSERT INTO permissao (codigo, descricao) values (18, 'ROLE_CADASTRAR_PATIO');
INSERT INTO permissao (codigo, descricao) values (19, 'ROLE_REMOVER_PATIO');
INSERT INTO permissao (codigo, descricao) values (20, 'ROLE_PESQUISAR_PATIO');

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 18);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 19);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 20);