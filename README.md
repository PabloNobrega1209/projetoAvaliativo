# To-Do CRUD API

API REST para cadastro e gerenciamento de tarefas, desenvolvida com Java 17, Spring Boot e PostgreSQL. Não possui login nem conceito de usuário.

## Requisitos

- JDK 17+
- Maven 3.6+
- PostgreSQL 14+

## Banco de dados

Crie o banco e execute o script incluído:

```sql
CREATE DATABASE todo_db;
```

Depois, conectado ao banco `todo_db`:

```bash
psql -U postgres -d todo_db -f src/main/resources/schema.sql
```

Por padrão, a aplicação conecta em `localhost:5432`, usuário `postgres`, senha `postgres`. Ajuste `DB_URL`, `DB_USERNAME` e `DB_PASSWORD` conforme seu ambiente.

## Executar

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080/api/tarefas`.

## Endpoints

| Método | Caminho | Descrição |
| --- | --- | --- |
| `GET` | `/api/tarefas` | Lista todas as tarefas |
| `GET` | `/api/tarefas/{id}` | Busca uma tarefa |
| `POST` | `/api/tarefas` | Cria uma tarefa |
| `PUT` | `/api/tarefas/{id}` | Altera uma tarefa |
| `DELETE` | `/api/tarefas/{id}` | Remove uma tarefa |

Exemplo de corpo para criação ou atualização:

```json
{
  "nome": "Estudar Spring",
  "descricao": "Revisar APIs REST",
  "status": "EM_ANDAMENTO",
  "observacoes": "Capítulo 4"
}
```

Status aceitos: `PENDENTE`, `EM_ANDAMENTO`, `CONCLUIDA`. Se omitido, o status será `PENDENTE`. As datas de criação e atualização são gerenciadas pela aplicação.

## Testes

```bash
mvn test
```
