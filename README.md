# API gerenciadora de eventos 👔🎉

A **api.event.manager** é uma solução empresarial de backend que capacita qualquer membro do departamento de RH a gerenciar os eventos da empresa.

---

## Ferramentas utilizadas 🛠️

<div style="display: flex; flex-wrap: wrap; gap: 10px;">
    <img src="https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white" />
    <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff" alt="Spring Boot">
    <img src="https://img.shields.io/badge/Lombok-f2fcf3.svg?logo=paperlessngx&logoColor=red" alt="Lombok">
    <img src="https://img.shields.io/badge/AWS-%23FF9900.svg?logo=amazon-aws&logoColor=white" alt="AWS">
    <img src="https://img.shields.io/badge/JWT-black?logo=JSON%20web%20tokens" style="border:2px solid #000;border-radius:3px;" alt="JWT">
    <img src="https://img.shields.io/badge/Swagger-6DB33F?logo=swagger&logoColor=fff" alt="Swagger">
    <img src="https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=fff" alt="MySQL">
    <img src="https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff" alt="Docker">
    <img src="https://img.shields.io/badge/Caffeine-010a11?logo=buymeacoffee&logoColor=fff" alt="Caffeine">
    <img src="https://img.shields.io/badge/Spring%20Security-6DB33F?logo=springsecurity&logoColor=fff" alt="Spring Security">
</div>

---

## Pré-requisitos 💻

<div style="display: flex; flex-wrap: wrap; gap: 10px;">
<img src="https://img.shields.io/badge/Jdk%2017-%23ED8B00.svg?logo=openjdk&logoColor=white" />
<img src="https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff">
</div>

---

## Menu 📋

- [Portas](#portas-do-projeto-)
- [Rota do swagger](#swagger-)
- [Instalação](#processo-de-instalação-)
- [Rotas](#rotas-de-para-gerenciar-eventos-)

---

## Portas do projeto 🚪
- **Docker:** _80_
- **Local:** _8081_
- **Dev:** _80_
- **Prd:** _8082_

---

## Swagger 📄

- [Local](http://localhost:8081/tech-sprint-solutions/swagger-ui/index.html)
- [Dev](http://localhost:80/tech-sprint-solutions/swagger-ui/index.html)
- [Docker](http://localhost:80/tech-sprint-solutions/swagger-ui/index.html)
- [Prd](http://localhost:8081/tech-sprint-solutions/swagger-ui/index.html)

---

## Processo de Instalação 🔧

### Implantação Local 🖥️

Para implantar o projeto localmente, siga as etapas abaixo:

1. Abra o terminal e navegue até o diretório do projeto.

2. Execute o seguinte comando para iniciar o banco de dados:
   ```bash
   docker compose up -d database
   ```
   
3. Aguarde até o banco de dados subir, depois,execute o projeto.

### Implantação no Docker 🐳

Siga as etapas abaixo para implantar o projeto no Docker:

1. Execute:
   ```bash
   docker compose up
   
2. Aguarde até o processo de instalação finalizar.

---

## Rotas de para gerenciar eventos 📍📅

### `POST /tech-sprint-solutions/v1/auth/login`

```bash
curl --location 'http://localhost:80/tech-sprint-solutions/v1/auth/login' --header 'Content-Type: application/json' --header 'Cookie: JSESSIONID=C1C72560FAF90BD05C829610920562E8' --data-raw '{"email": "rh.example@gmail.com","password": "rhExample"}'
```

> Esta rota é utilizada para efetuar o login do funcionário. A existência do funcionário na empresa é verificada com base
no seu email e senha.

---

### `POST /tech-sprint-solutions/v1/events`

```bash
curl --location 'http://localhost:80/tech-sprint-solutions/v1/events' --header 'Content-Type: application/json' --header 'Cookie: JSESSIONID=3F26D9D081998A4C9116E554368DF338; JSESSIONID=02BFB81B4EC9DE75C80EF8AAA61C11C3; JSESSIONID=9BBC687181840EEABDFC636AB5CE0186' --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhcGktYXV0aCIsInVzZXJuYW1lIjoicmguZXhhbXBsZUBnbWFpbC5jb20iLCJyb2xlcyI6WyJST0xFX1JIIl0sImV4cCI6MTcxNDM1Mjk5Nn0.tn1yie4FsRFArMfN3l47RrrwLY1ZoqJq-JxF8FAq8tQ' --data '{"eventName": "Reunião com os acionistas 2234","eventDescription": "Reunião que haverá,tendo a presença dos acionistas, a reunião será na sede da empresa e terá a finalidade de discutir os novos produtos da empresa","eventData": "2024-04-27T15:30","status": "FINALIZED"}'
```

> Nesta rota, Criamos um novo evento,tendo como obrigatórios os campos: eventName, eventDescription, eventData e status.

---

### `GET /tech-sprint-solutions/v1/events/date`

```bash
curl --location 'http://localhost:80/tech-sprint-solutions/v1/events/date' --header 'date: 2024-04-27T15:30' --header 'Authorization: Bearer SEU_TOKEN_AQUI' --header 'Cookie: JSESSIONID=9BBC687181840EEABDFC636AB5CE0186'
```

> Nessa rota,passamos pelo header um campo chamado: "date",nele, buscamos todos os eventos com base na data fornecida

---

### `GET /tech-sprint-solutions/v1/events/name`

```bash
curl --location 'http://localhost:80/tech-sprint-solutions/v1/events/name' --header 'name: Reunião com os acionistas' --header 'Authorization: Bearer SEU_TOKEN_AQUI' --header 'Cookie: JSESSIONID=9BBC687181840EEABDFC636AB5CE0186'
```

> Nessa rota,passamos pelo header um campo chamado: "nome",nele, buscamos o evento com base no nome fornecido.

---

### `PUT /tech-sprint-solutions/v1/events`

```bash
curl --location --request PUT 'http://localhost:80/tech-sprint-solutions/v1/events' --header 'Content-Type: application/json' --header 'Cookie: JSESSIONID=3F26D9D081998A4C9116E554368DF338; JSESSIONID=9BBC687181840EEABDFC636AB5CE0186' --header 'Authorization: Bearer SEU_TOKEN_AQUI' --data '{"id": "4ca79dd1-251e-4673-bf26-5bbbe356de56","eventName": "Reunião com os acionistas","eventDescription": "Reunião que haverá,tendo a presença dos acionistas, a reunião será na sede da empresa e terá a finalidade de discutir os novos produtos da empresa","eventData": "2024-04-27T15:30","status": "FINALIZED"}'
```

> Nessa rota,atualizamos um evento com base no body passado, tendo como obrigatórios os campos: eventName, eventDescription, eventData e status.