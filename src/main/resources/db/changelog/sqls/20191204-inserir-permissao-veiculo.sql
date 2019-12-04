INSERT INTO permissao (codigo, descricao) values (15, 'ROLE_CADASTRAR_VEICULO');
INSERT INTO permissao (codigo, descricao) values (16, 'ROLE_REMOVER_VEICULO');
INSERT INTO permissao (codigo, descricao) values (17, 'ROLE_PESQUISAR_VEICULO');

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 15);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 16);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 17);