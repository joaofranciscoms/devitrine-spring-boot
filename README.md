# Devitrine

Plataforma digital voltada para a area de tecnologia, onde desenvolvedores, pesquisadores e empresas podem divulgar conteudos, projetos e oportunidades de trabalho, alem de interagir e colaborar entre si.

# Desenvolvedores

- João Francisco Martins Simão
- Hugo Rafael Pessoni Batista
- Pedro Augusto Garcias Santos

## Sumário

- [Sobre o projeto](#sobre-o-projeto)
- [Requisitos funcionais](#requisitos-funcionais)
- [Tecnologias utilizadas](#tecnologias-utilizadas)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Autenticação e perfis de usuário](#autenticacao-e-perfis-de-usuario)
- [Endpoints da API](#endpoints-da-api)
- [Como executar localmente](#como-executar-localmente)
  - [Pre-requisitos](#pre-requisitos)
  - [Clonando o repositório](#clonando-o-repositorio)
  - [Configurando o banco de dados](#configurando-o-banco-de-dados)
  - [Executando o backend](#executando-o-backend)
  - [Executando o frontend](#executando-o-frontend)
- [Solução de problemas](#solucao-de-problemas)
- [Proximos passos](#proximos-passos)

## Sobre o projeto

O DeVitrine e um projeto em desenvolvimento, criado como uma plataforma para a comunidade de tecnologia. A ideia e reunir em um so lugar publicacoes, projetos e oportunidades de trabalho, permitindo que desenvolvedores, pesquisadores e empresas interajam entre si.

As funcionalidades descritas neste documento estao operacionais, mas a plataforma ainda esta em evolucao e novos recursos e melhorias estao sendo adicionados aos poucos.

Hoje, o projeto permite:

- Cadastro e autenticacao de usuarios com perfil de desenvolvedor, pesquisador ou empresa.
- Publicacao de posts, com comentarios.
- Publicacao de projetos, filtraveis por tipo e por tecnologia.
- Publicacao de vagas por empresas e candidatura de desenvolvedores e pesquisadores.
- Registro de ideias e colaboracao de outros usuarios nelas.

O projeto e composto por uma API REST (backend) e uma aplicacao web (frontend), executados localmente.

## Requisitos funcionais

| Codigo | Descricao |
|--------|-----------|
| RF01 | O sistema deve permitir o cadastro de usuarios com perfil de desenvolvedor. |
| RF02 | O sistema deve permitir o cadastro de usuarios com perfil de pesquisador. |
| RF03 | O sistema deve permitir o cadastro de usuarios com perfil empresarial. |
| RF04 | O sistema deve permitir que os usuarios realizem publicacoes. |
| RF05 | O sistema deve permitir que os usuarios realizem comentarios em publicacoes. |
| RF06 | O sistema deve permitir que empresas publiquem vagas de emprego. |
| RF07 | O sistema deve permitir que desenvolvedores se candidatem a vagas de emprego. |
| RF08 | O sistema deve permitir colaboracao de ideias em projetos. |
| RF09 | O sistema deve permitir que pesquisadores publiquem artigos e projetos cientificos. |
| RF10 | O sistema deve permitir que desenvolvedores publiquem projetos pessoais. |
| RF11 | O sistema deve permitir que projetos sejam classificados por tecnologia. |

> Projeto em desenvolvimento. Os requisitos acima estao contemplados pela versao atual, mas a plataforma continua recebendo melhorias.

## Tecnologias utilizadas

### Backend

- Java
- Spring Boot
- Spring Web (API REST)
- Spring Data JPA / Hibernate
- PostgreSQL
- Bean Validation (Jakarta Validation)
- Autenticacao stateless com JWT (assinatura HS256)
- MapStruct (mapeamento entre entidades e DTOs)
- Lombok
- Maven

### Frontend

- Angular
- TypeScript
- Reactive Forms
- RxJS
- ngx-toastr (notificacoes)
- HTML e SCSS

## Estrutura do projeto

A organizacao do backend segue a separacao por dominio, com camadas bem definidas dentro de cada um:

```
dominio/
  usuario/         # Cadastro, login e perfis de usuario
  post/
  comentario/
  projeto/
  tecnologia/
  vaga/
  candidatura/
  ideia/
  colaboracao/
    controller/    # Endpoints REST
    dto/           # Objetos de transferencia de dados
    mapper/        # Conversao entre entidade e DTO (MapStruct)
    model/         # Entidades JPA
    repository/    # Acesso a dados (Spring Data JPA)
    service/       # Regras de negocio
infra/
  security/        # Geracao e validacao de JWT, controle de acesso por tipo de usuario
common/
  exceptions/      # Excecoes de negocio
```

Cada dominio de negocio segue a mesma divisao de camadas (controller, dto, mapper, model, repository e service).

No frontend, as telas estao organizadas por funcionalidade:

```
posts/
  cadastro-post/     # Formulario de criacao de post
  listagem-posts/    # Listagem paginada de posts
  conteudo-post/     # Visualizacao do post e seus comentarios
  post-service.ts    # Comunicacao com a API
  dados-post.ts      # Modelos de dados
comentarios/
common/
  components/        # Componentes reutilizaveis (ex.: header)
  pagination/        # Modelo de paginacao
```

## Autenticação e perfis de usuário

A API utiliza autenticacao baseada em token JWT:

1. O usuario se cadastra em `POST /auth/signup` ou faz login em `POST /auth/signin`.
2. A resposta da autenticacao fornece o token JWT, assinado com o algoritmo HS256 e com tempo de expiracao configuravel.
3. Nas demais requisicoes (rotas `/api`), o token deve ser enviado no cabecalho `Authorization`:

```
Authorization: Bearer <token>
```

O token carrega o identificador do usuario, o nome, o tipo de perfil e a data de expiracao. Tokens invalidos, adulterados ou expirados sao rejeitados.

Existem tres perfis de usuario:

| Perfil | Permissoes especificas |
|--------|------------------------|
| Desenvolvedor | Pode se candidatar a vagas. |
| Pesquisador | Pode se candidatar a vagas. |
| Empresa | Pode publicar vagas. |

Os demais recursos (posts, comentarios, projetos, ideias e colaboracoes) estao disponiveis para qualquer usuario autenticado.

## Endpoints da API

URL base: `http://localhost:8080`

Os endpoints de cadastro e login ficam sob `/auth`. Todos os demais ficam sob `/api` e exigem usuario autenticado. Listagens sao paginadas pelos parametros `page` (padrao `0`) e `size` (padrao `10`).

### Autenticacao

| Metodo | Rota | Descricao |
|--------|------|-----------|
| POST | `/auth/signup` | Cadastra um novo usuario. |
| POST | `/auth/signin` | Autentica um usuario existente. |

### Posts

| Metodo | Rota | Descricao |
|--------|------|-----------|
| POST | `/api/posts` | Cria um novo post. |
| GET | `/api/posts` | Lista os posts. |
| GET | `/api/posts/{id}` | Retorna os detalhes de um post. |
| PUT | `/api/posts/{id}` | Atualiza um post. |
| DELETE | `/api/posts/{id}` | Remove um post. |

### Comentarios

| Metodo | Rota | Descricao |
|--------|------|-----------|
| POST | `/api/comentarios` | Cria um comentario. |
| GET | `/api/comentarios?postId={id}` | Lista os comentarios de um post (`postId` obrigatorio). |
| GET | `/api/comentarios/{id}` | Retorna os detalhes de um comentario. |
| PUT | `/api/comentarios/{id}` | Atualiza um comentario. |
| DELETE | `/api/comentarios/{id}` | Remove um comentario. |

### Projetos

| Metodo | Rota | Descricao |
|--------|------|-----------|
| POST | `/api/projetos` | Cria um projeto. |
| GET | `/api/projetos` | Lista os projetos. Filtros opcionais: `tipo` e `tecnologia`. |
| GET | `/api/projetos/{id}` | Retorna os detalhes de um projeto. |

### Tecnologias

| Metodo | Rota | Descricao |
|--------|------|-----------|
| GET | `/api/tecnologias` | Lista os nomes das tecnologias disponiveis. |

### Vagas

| Metodo | Rota | Descricao |
|--------|------|-----------|
| POST | `/api/vagas` | Cria uma vaga. Restrito a usuarios do tipo empresa. |
| GET | `/api/vagas` | Lista as vagas. |
| GET | `/api/vagas/{id}` | Retorna os detalhes de uma vaga. |

### Candidaturas

| Metodo | Rota | Descricao |
|--------|------|-----------|
| POST | `/api/candidaturas` | Cria uma candidatura. Restrito a usuarios do tipo desenvolvedor ou pesquisador. |
| GET | `/api/candidaturas` | Lista as candidaturas. |

### Ideias

| Metodo | Rota | Descricao |
|--------|------|-----------|
| POST | `/api/ideias` | Cria uma ideia. |
| GET | `/api/ideias` | Lista as ideias. |
| GET | `/api/ideias/{id}` | Retorna os detalhes de uma ideia. |

### Colaboracoes

| Metodo | Rota | Descricao |
|--------|------|-----------|
| POST | `/api/colaboracoes` | Registra uma colaboracao em uma ideia. |
| GET | `/api/colaboracoes?ideiaId={id}` | Lista as colaboracoes de uma ideia (`ideiaId` obrigatorio). |

### Exemplos

Corpo para criacao de post:

```json
{
  "titulo": "Meu primeiro post",
  "conteudo": "Conteudo do post."
}
```

Resposta de detalhes de um post:

```json
{
  "id": "b7a1c3e0-0000-0000-0000-000000000000",
  "titulo": "Meu primeiro post",
  "conteudo": "Conteudo do post.",
  "dataPostagem": "2026-10-30T10:00:00",
  "dataAtualizacao": "2026-10-30T10:00:00",
  "autorNome": "Nome do autor"
}
```

Listagem paginada:

```
GET http://localhost:8080/api/posts?page=0&size=5
```

## Como executar localmente

O projeto foi pensado para execucao local. Siga os passos abaixo na ordem.

### Pre-requisitos

Instale as ferramentas abaixo antes de comecar:

- [Git](https://git-scm.com/)
- [JDK](https://adoptium.net/) (versao compativel com o projeto, 17 ou superior)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) para executar a API
- [PostgreSQL](https://www.postgresql.org/download/)
- [Node.js](https://nodejs.org/) (inclui o npm)
- [Visual Studio Code](https://code.visualstudio.com/) para executar o frontend

Observacao sobre o Angular: nao e necessario instalar o Angular CLI globalmente. O CLI vem como dependencia do projeto e e instalado junto com as demais dependencias ao executar `npm install`, sendo utilizado automaticamente pelo comando `npm start`.

### Clonando o repositorio

```bash
git clone https://github.com/joaofranciscoms/devitrine-spring-boot.git
```

### Configurando o banco de dados

1. Certifique-se de que o PostgreSQL esta em execucao (porta padrao `5432`).
2. Crie um banco de dados vazio. Pode ser feito pelo pgAdmin ou pelo terminal:

```sql
CREATE DATABASE devitrine;
```

3. Utilize o usuario e a senha padrao configurados no arquivo `application.yml` do backend (em `src/main/resources`).

Caso voce prefira usar outro nome de banco, outro usuario ou outra senha, basta alterar os valores correspondentes no `application.yml`. Exemplo da secao de conexao:

```yaml
spring:
  datasource:
    url: ${DATABASE_URL:jdbc:postgresql://localhost:5432/devitrine}
    username: ${DATABASE_USERNAME:postgres}
    password: ${DATABASE_PASSWORD:postgres}
    driver-class-name: org.postgresql.Driver
```

> Confira no arquivo do projeto os valores padrao exatos e mantenha o banco criado com o mesmo nome definido na URL.

Alem da conexao com o banco, o `application.yml` precisa conter as configuracoes do JWT:

```yaml
app:
  jwt:
    secret: <chave-longa>
    expiration-seconds: 3600
```

### Executando o backend

1. Abra o projeto no IntelliJ IDEA (pasta do backend, onde esta o `pom.xml`).
2. Aguarde o IntelliJ importar as dependencias do Maven.
3. Certifique-se de que o Lombok e o processamento de anotacoes estao habilitados. Isso é necessário para o Lombok e o MapStruct.
4. Execute a classe principal da aplicação (classe anotada com `@SpringBootApplication`).
5. A API ficara disponivel em `http://localhost:8080`.

### Executando o frontend

1. Abra a pasta do frontend no Visual Studio Code.
2. Abra o terminal integrado (`Ctrl + '`).
3. Instale as dependencias (necessario apenas na primeira execucao):

```bash
npm install
```

4. Inicie a aplicacao:

```bash
npm start
```

5. Acesse `http://localhost:4200` no navegador.

> O backend precisa estar em execução para que o frontend consiga listar e criar posts, pois a aplicação consome a API em `http://localhost:8080/api`.

## Solucao de problemas

- **Erro de conexao com o banco:** verifique se o PostgreSQL esta em execucao, se o banco foi criado e se usuario, senha e nome do banco no `application.yml` estao corretos.
- **Falha ao iniciar a API por propriedade ausente:** confirme que `app.jwt.secret` e `app.jwt.expiration-seconds` estao definidos no `application.yml`.
- **Resposta 401 ou acesso negado nas rotas `/api`:** verifique se o token foi obtido em `/auth/signin` ou `/auth/signup`, se esta sendo enviado no cabecalho `Authorization` e se ainda nao expirou.
- **Acesso negado ao criar vaga ou candidatura:** vagas so podem ser criadas por usuarios do tipo empresa, e candidaturas apenas por desenvolvedores e pesquisadores.
- **Porta 8080 em uso:** encerre o processo que esta usando a porta ou altere `server.port` no `application.yml` (e atualize a URL base no servico do frontend).
- **Porta 4200 em uso:** o Angular costuma sugerir outra porta automaticamente. Aceite a sugestao ou encerre o processo que ocupa a porta.
- **Erros de compilacao relacionados a mappers ou getters:** confirme que o processamento de anotacoes esta habilitado no IntelliJ.
- **Erro de CORS no navegador:** confirme que o backend esta no ar e acessivel em `http://localhost:8080`.
- **Comando `npm` nao encontrado:** instale o Node.js e reabra o terminal.

## Proximos passos

O projeto segue em evolucao. Algumas melhorias previstas:

- Continuar o desenvolvimento do frontend, que hoje cobre posts e comentarios
- Ampliar as opcoes de filtro e busca de projetos
- Edicao e remocao de vagas, ideias e projetos
- Testes automatizados
