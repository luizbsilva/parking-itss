UPDATE usuario SET ativo = true, tipo_usuario = 'ADMINISTRADOR' WHERE codigo = 1;
 
ALTER TABLE usuario ALTER COLUMN tipo_usuario SET NOT NULL;
ALTER TABLE usuario ALTER COLUMN ativo SET NOT NULL;