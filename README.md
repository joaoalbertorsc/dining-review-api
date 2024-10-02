# Restaurant Dining Review API

## Descrição

Este sistema é uma API para avaliação de restaurantes, com foco em alergias alimentares. Os usuários podem avaliar restaurantes com base em três categorias de alergias: amendoim, ovo e laticínios. A plataforma permite a busca de restaurantes por código postal e alergia, ajudando usuários a encontrar opções seguras de alimentação.

Os administradores podem moderar as avaliações, aceitando ou rejeitando submissões. O sistema calcula pontuações médias dos restaurantes com base nas avaliações dos usuários, exibindo os restaurantes mais relevantes.

## Funcionalidades

- **Cadastro e avaliação de restaurantes**: Usuários podem avaliar restaurantes com notas de 1 a 5 nas categorias de alergias alimentares (amendoim, ovo e laticínios).
- **Administração de avaliações**: Administradores revisam e aprovam/rejeitam avaliações submetidas por usuários.
- **Busca de restaurantes**: Pesquisas de restaurantes por código postal e alergias alimentares, exibindo restaurantes com avaliações relevantes e ordenados por suas notas.
- **Pontuação de alergias**: Cálculo das médias das avaliações para cada categoria de alergia e uma pontuação geral.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database** (banco de dados em memória)
- **Lombok**

## Requisitos

- **Java 21**
- **Maven 3.8+**

## Como Executar

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/dining-review-api.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd dining-review-api
   ```

3. Execute o comando Maven para construir o projeto:

   ```bash
   mvn clean install
   ```

4. Inicie a aplicação:

   ```bash
   mvn spring-boot:run
   ```

5. Acesse a aplicação no navegador em:

   ```
   http://localhost:8080
   ```

6. Acesse o console do H2 Database em:

   ```
   http://localhost:8080/h2-console
   ```

## Endpoints da API

### Restaurantes

- **GET /restaurants**: Retorna uma lista de todos os restaurantes.
- **GET /restaurants/search**: Permite buscar restaurantes por código postal e alergia (por exemplo, amendoim).

### Avaliações

- **POST /reviews**: Permite que um usuário envie uma avaliação para um restaurante.
- **PUT /reviews/{reviewId}**: Atualiza uma avaliação existente.
- **GET /reviews/pending**: Retorna todas as avaliações com status "pendente".

### Administração

- **POST /admin/review/{reviewId}**: Permite que um administrador aprove ou rejeite uma avaliação.

## Estrutura do Banco de Dados

O banco de dados H2 é inicializado com o arquivo `data.sql`, que contém dados de exemplo para restaurantes, usuários e avaliações.

## Autor

- **joaoalbertorsc**
