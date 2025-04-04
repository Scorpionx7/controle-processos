# 🧑‍⚖️ Controle de Processos Judiciais

Microsserviço desenvolvido como parte da avaliação técnica da POL, para gerenciamento de processos judiciais e réus associados.

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot 3.x
- PostgreSQL 16 (via Docker)
- Liquibase
- JPA/Hibernate
- JUnit 5

---

## ⚙️ Como rodar o projeto

### ✅ Pré-requisitos

- Java 17 ou 21 instalado
- Docker instalado
- Maven instalado (ou use `./mvnw`)

---

### 🐳 Subir o banco de dados PostgreSQL:

```bash
docker-compose up -d
```

> Isso criará um container PostgreSQL na porta `5432`, com o banco `processos_db`.

---

### ▶️ Rodar a aplicação:

```bash
./mvnw spring-boot:run
```

ou com Maven instalado:

```bash
mvn spring-boot:run
```

---

## 📚 Endpoints disponíveis

| Método | Endpoint               | Descrição                             |
|--------|------------------------|----------------------------------------|
| POST   | `/api/processos`       | Cria um novo processo                  |
| GET    | `/api/processos`       | Lista todos os processos               |
| DELETE | `/api/processos/{id}`  | Remove um processo por ID              |
| POST   | `/api/reus`            | Adiciona um réu a um processo existente|

---

## ✅ Regras de negócio implementadas

- Números de processos são **únicos**
- Não é possível cadastrar o mesmo processo duas vezes
- Ao excluir um processo, os réus associados são deletados automaticamente
- Só é possível cadastrar réu se o processo existir

---

## 🧪 Testes

- Testes de integração completos com JUnit e Spring Boot Test
- Casos válidos e inválidos
- Testes de POST, GET, DELETE e validações de erro

Para rodar:

```bash
mvn test
```

---

## 📂 Organização do projeto

```bash
src/
 └── main/
     └── java/com/exemplo/controleprocessos/
         ├── controller/
         ├── dto/
         ├── entity/
         ├── exception/
         ├── repository/
         ├── service/
 └── test/
     └── java/com/exemplo/controleprocessos/
         ├── ProcessoControllerTest.java
         ├── ReuControllerTest.java
```

---
## 📧 **Contato**
Entre em contato para dúvidas ou sugestões!
- 🌐 [**LinkedIn**](https://www.linkedin.com/in/estherrezende/)
- 📧 **E-mail:** [rezendealvesesther@gmail.com](mailto:rezendealvesesther@gmail.com)


---

