# Desafio - ITSS

## 1. Passos para começar

### Clonando o Repositório

`https://github.com/luizbsilva/parking-itss.git`

### Instalando as Dependências

###  Desntro da pasta do projeto

### Caso o sistema Operacional seja Windows abrir o PowerShel 
`.\gradlew build -x test`

### Caso o sistema Operacional seja alguma versão Linux abrir o terminal 
`gradle build -x test`

## 2. Criando Banco de Dados
### Criar o banco de Dados no postgresql com o nome de sua preferencia

## 3. Criando arquivo aplication.properties
### Alterar o nome do aquivo application.properties.exemplo para application.properties

###Alterar os campos para conexao com o banco
###{{USUARIO_POSTAGRES}} = usuario do banco no qual a aplicação vai conectar
###{{SENHA_USUARIO_POSTGRES}} = senha do usuario do banco no qual a aplicação vai conectar
  
  
### Será necessario configurar os dados de usuario e senha do provedor de email
###username={{USUARIO_EMAIL}} = usuario do servidor de e-mail
###password={{SENHA_USUARIO_EMAIL}} = senha do usuario do servidor de e-mail 

### Foi utilizado o bucket da Amazon para efetuar os testes a id e chave seram encaminhado pelo email por questoes de segurança		
###access-key-id={{ID_S3_AMAZON}} = id encaminhada pelo email
###secret-access-key={{KEY_ID_S3_AMAZON}} = chave encaminhada pelo email

### Para Facilitar encaminharei no email o arquivi configurado para uso

