  INSERT INTO permissao (codigo, descricao) values (21, 'ROLE_CADASTRAR_ESTACIONAMENTO');
INSERT INTO permissao (codigo, descricao) values (22, 'ROLE_REMOVER_ESTACIONAMENTO');
INSERT INTO permissao (codigo, descricao) values (23, 'ROLE_PESQUISAR_ESTACIONAMENTO');

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 21);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 22);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 23);