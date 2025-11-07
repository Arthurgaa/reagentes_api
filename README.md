# reagentes_api

API REST para o Sistema de Gerenciamento de Inventário de Reagentes (GIR)

## Como executar
1. Instale Java 17 e Maven.
2. Copie o projeto para uma pasta local.
3. Rode `mvn spring-boot:run` na raiz do projeto.
4. A aplicação sobe em `http://localhost:8080`.
5. O H2 console está disponível em `http://localhost:8080/h2-console` com JDBC URL `jdbc:h2:mem:reagentesdb`.

## Endpoints principais
- `POST /api/reagentes` - cria um reagente. Retorna HTTP 201.
- `GET /api/reagentes` - lista todos. Retorna HTTP 200.
- `GET /api/reagentes/{id}` - consulta por id. Retorna HTTP 200 ou 404.
- `PUT /api/reagentes/{id}` - atualiza. Retorna HTTP 200 ou 404.
- `DELETE /api/reagentes/{id}` - deleta. Retorna HTTP 204 ou 404.

Analogamente para `fabricantes`, `localizacoes` e `movimentacoes`.

## Observações
- DTOs usam `record`.
- Controllers retornam códigos HTTP apropriados.
- Projeto usa H2 para testes locais.

## Sugestão de commits
- `init: projeto reagentes_api (spring initializr)`
- `feat(entity): criar entidades Reagente, Fabricante, LocalizacaoEstoque`
- `feat(dto): adicionar records DTOs`
- `feat(repo): adicionar repositories`
- `feat(service): implementar logica basica ReagenteService`
- `feat(controller): implementar ReagenteController CRUD`
- `feat(movement): implementar MovimentacaoService e controller`
- `chore(config): application.properties e h2 console`
- `docs: adicionar README`
- `fix: ajustes mapeamento e null-safety`
