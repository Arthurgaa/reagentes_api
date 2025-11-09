# ⚗️ reagentes_api  
**API REST para o Sistema de Gerenciamento de Inventário de Reagentes (GIR)**  

---

## 🚀 Visão Geral
O **reagentes_api** é um backend em **Spring Boot 3** desenvolvido em **Java 17**, projetado para gerenciar o inventário de reagentes de laboratório.  
A aplicação oferece endpoints RESTful para o controle de **reagentes**, **fabricantes**, **localizações** e **movimentações** (entradas e saídas de estoque), com arquitetura em camadas, DTOs baseados em `record` e integração com banco **H2 em memória**.

---

## ⚙️ Tecnologias Utilizadas
| Categoria | Tecnologias |
|------------|--------------|
| **Linguagem** | Java 17 |
| **Framework** | Spring Boot 3.1.4 |
| **Banco de Dados** | H2 (em memória) |
| **ORM** | Spring Data JPA |
| **Validação** | Jakarta Validation (via `spring-boot-starter-validation`) |
| **Utilitários** | Lombok, Jackson JSR310, Apache Commons Lang |
| **Build Tool** | Maven |

---

## 🧠 Funcionalidades Principais
- Cadastro, listagem, atualização e exclusão de reagentes.  
- Gerenciamento de fabricantes e localizações de estoque.  
- Registro de movimentações de entrada e saída de reagentes.  
- Retorno de respostas HTTP adequadas (`201`, `200`, `204`, `404`).  
- DTOs imutáveis utilizando `record`.  
- Banco em memória H2 para testes e prototipagem rápida.  

---

## 🔗 Endpoints Principais

### 🧪 Reagentes
| Método | Endpoint | Descrição | Status |
|--------|-----------|------------|--------|
| `POST` | `/api/reagentes` | Cria um reagente | `201 Created` |
| `GET` | `/api/reagentes` | Lista todos os reagentes | `200 OK` |
| `GET` | `/api/reagentes/{id}` | Busca reagente por ID | `200 OK / 404 Not Found` |
| `PUT` | `/api/reagentes/{id}` | Atualiza um reagente | `200 OK / 404 Not Found` |
| `DELETE` | `/api/reagentes/{id}` | Remove um reagente | `204 No Content / 404 Not Found` |

Endpoints equivalentes existem para:
- `/api/fabricantes`  
- `/api/localizacoes`  
- `/api/movimentacoes`

---

## 🧱 Boas Práticas Implementadas
- Arquitetura em camadas (**Controller → Service → Repository**).  
- DTOs imutáveis com `record`.  
- Tratamento de exceções e códigos HTTP padronizados.  
- Configuração simples e limpa via `application.properties`.  
- Uso de dependências essenciais e leves (Lombok, Validation, JPA).  

---

## 📘 Observações
- Os DTOs utilizam `record` para imutabilidade.  
- Os controllers retornam respostas HTTP adequadas conforme o contexto.  
- O projeto utiliza o banco **H2** para ambiente de desenvolvimento e testes locais.  

---

## 👥 Integrantes
- **Arthur Galvão Alves** - RM554462  
- **Felipe Braunstein e Silva** - RM554483  
- **Felipe do Nascimento Fernandes** - RM554598  
- **Henrique Ignacio Bartalo** - RM555274  
- **Gustavo Henrique Martins** - RM556956  

