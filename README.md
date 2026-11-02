# Учёт клиентов

REST-сервис для CRUD-операций над клиентами (`/api/clients`).

Стек: Java 21, Spring Boot 4.1, Spring Data JPA (Hibernate 7), PostgreSQL 17, Maven.

## Запуск

```bash
docker compose up -d
./mvnw spring-boot:run
```

PostgreSQL доступен на `localhost:5433` (БД, пользователь и пароль: `clients`).
Таблица `client_account` создаётся скриптом `db/init.sql` при первом старте контейнера.
Чтобы пересоздать базу с нуля: `docker compose down -v && docker compose up -d`.

Swagger UI: http://localhost:8080/swagger-ui.html

## API

| Метод  | URL                 | Тело                                   | Ответ              |
|--------|---------------------|----------------------------------------|--------------------|
| POST   | `/api/clients`      | `{"fullName": "...", "gender": "M"}`   | 201 + клиент       |
| GET    | `/api/clients`      |                                        | 200 + список       |
| GET    | `/api/clients/{id}` |                                        | 200 / 404          |
| PUT    | `/api/clients/{id}` | `{"fullName"?, "gender"?, "status"?}`  | 200 / 404          |
| DELETE | `/api/clients/{id}` |                                        | 204 / 404          |

`gender`: `M` / `F`, `status`: `ACTIVE` / `INACTIVE`. В PUT передаются только изменяемые поля.

## SQL

```bash
docker compose exec postgres psql -U clients -d clients
```

```sql
select * from client_account order by id;
select status, count(*) from client_account group by status;
select * from client_account where full_name ilike '%иван%';
select * from client_account where create_dttm >= now() - interval '1 day';
update client_account set status = 'INACTIVE', modify_dttm = now() where id = 1;
delete from client_account where status = 'INACTIVE';
```
