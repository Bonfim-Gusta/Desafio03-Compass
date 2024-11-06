
# Desafio 03 - API User com spring security

O projeto consiste em uma aplicação springboot que realiza registros de usuários, salvando informações, criptografando a senha e armazenando dados em um banco de dados mysql. Ao enviar a requisição de registro, o cep informado é usado para consultar a API externa ViaCep, que trás dados de endereço caso o cep informado seja válido. Se o registro é realizado com sucesso, é enviado uma mensagem com username e a operação realizada (CREATE/UPDATE) para uma fila no serviço de mensageria RabbitMQ, que será lida pelo microsserviço de notify que armazenará a mensagem um banco de dados mongodb.

Para realizar alteração de senha, é necessário estar autenticado. Essa camada de segurança é aplicada e configurada através do spring security. Ao requisitar o endpoint de login, caso os dados informados estejam corretos, é retornado um token JWT que identifica que aquele usuário está acessando o sistema e que somente a sua senha poderá ser alterada.

## Ressalvas:
Para garantir a melhor experiência e aproveitamento ao executar a aplicação em sua máquina, leve em consideração os apontamentos levantados no tópico "Atenção" localizado mais abaixo no texto.

## EndPoints
- Registro de Usuário: POST /api/users/register
#### Corpo de requisição:
```
{
    "username": "",
    "password": "",
    "email": "",
    "cep": ""
}
```
- Atualização de Senha: PUT /api/users/update-password
#### Corpo de requisição:
```
{
    "oldPassword": "",
    "newPassword": ""
}
```
- Login de usuário: POST /auth/login
#### Corpo de requisição:
```
{
    "username": "",
    "password": ""
}
```
- Verificar mensagens salvas pelo notify: GET (Port:8081)/api/notifications
#### Sem corpo de requisição

## Como instalar e testar na minha máquina local?

### Requisitos:
- JDK 17 ou superior
- Docker Desktop
- Git
- Postman (ou outro aplicativo de sua preferência para requisições)

### Passo a passo:
- Escolha um diretório de sua preferência para clonar o projeto.
- Para clonar, abra a o diretório em um terminal e aplique o seguinte comando:
```
    https://github.com/Bonfim-Gusta/Desafio03-Compass.git
```
- Com o projeto carregado, acesse a pasta 'msusers' e aplique em ordem os seguintes comandos:

```
    ./mvnw clean package -DskipTests
```
```
    docker build --tag msusers .
```
- Repita os comandos acessando a pasta 'notify'
- Volte a pasta raiz do projeto, chamada 'desafio03' e execute o comando:
```
    docker-compose up -d
```
- Acesse o Docker desktop e vá na aba 'containers' e verifique se todos estão executando normalmente
- Acesse o Postman, e realize as requisições acima

## Atenção:
Para garantir que o microsserviço de notify funcione normalmente, reinicie apenas ele após executar o docker-compose. Também verifique se o MySQL server e o MondoDB server não estão sendo executados localmente ocupando as portas necessárias para a aplicação.

Caso a tabela 'users' não seja criada, siga os seguintes passos:
- Abra o docker, acesse o container do mysql e clique na aba 'exec'
- Aplique em ordem, os seguintes comandos:
```
    mysql -u root -p
```
(Acesse com sua senha root).
```
    use msusers
```
```
    CREATE TABLE users (id int primary key auto_increment, username varchar(255) unique, password varchar(255), email varchar(255) unique, cep varchar(255));
```

(Alterações para resolver estes problemas em andamento).

### Contato

Em caso de dúvidas, entre em contato comigo:
- Nome: Gustavo
- Email: dev.gusta.io@gmail.com
- Github: github.com/Bonfim-Gusta
