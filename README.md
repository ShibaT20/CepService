# CEP Service

API para consulta e manutenção de CEP com segurança via JWT e documentação no Swagger.

## Requisitos
- `Java 17`
- `Maven 3.8+`

## Como rodar
- Compilar: `mvn clean package`
- Executar: `mvn spring-boot:run`
- A aplicação sobe em `http://localhost:8080`

## Swagger
- UI: `http://localhost:8080/swagger-ui/index.html`
- Docs JSON: `http://localhost:8080/v3/api-docs`

## Autenticação
- `POST /login` com JSON `{ "username": "usuarioTeste1", "password": "usuarioTeste1" }`
- A resposta traz o `token` JWT
- Envie o header nas rotas protegidas: `Authorization: Bearer <token>`

## Endpoints
- `GET /api/ceps/{cep}` — busca por CEP
- `GET /api/ceps/logradouro?nome={texto}` — busca por logradouro (paginado)
- `GET /api/ceps/cidade?nome={texto}` — busca por cidade (paginado)
- `POST /api/ceps` — cria um CEP
- `PUT /api/ceps/{id}` — atualiza um CEP

## Banco e Migrations
- H2 em memória (`jdbc:h2:mem:testdb`)
- Console H2: `http://localhost:8080/h2-console`
- Liquibase aplica as mudanças na inicialização (`db/changelog/db.changelog-master.yaml`)

## Usuários de teste
- `usuarioTeste1` ... `usuarioTeste4`
- Senha igual ao nome do usuário

## Stack
- Spring Boot, Spring Web, Spring Data JPA
- Spring Security com JWT (JJWT)
- SpringDoc OpenAPI (Swagger UI)
- H2 e Liquibase

## Requests HTTP
- Arquivo: `./requests.http`
- Como usar:
  - Suba a aplicação (`mvn spring-boot:run`)
  - Execute o login no arquivo para capturar o token (`{{token}}`)
  - Rode os `GET/POST/PUT` seguintes; o header `Authorization: Bearer {{token}}` já está configurado