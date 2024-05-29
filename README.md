# API gerenciadora de eventos 👔🎉

O **api.event.manager** é uma solução empresarial de backend que capacita qualquer membro do departamento de RH a gerenciar os eventos da empresa.

## Pré-requisitos 💻

<img src="https://img.shields.io/badge/Jdk%2017-%23ED8B00.svg?logo=openjdk&logoColor=white" /> | <img src="https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff">

## Menu 📋

- [Ferramentas](#ferramentas-utilizadas-️)
- [Portas](#portas-do-projeto)
- [Rota do swagger](#rota-do-swagger-)
- [Instalação](#processo-de-instalação-)
- [Rotas](#rotas-de-para-gerenciar-eventos-)

---

## Ferramentas utilizadas 🛠️

<img src="https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white" /> 
<img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff">
<img src="https://img.shields.io/badge/Lombok-f2fcf3.svg?logo=paperlessngx&logoColor=red">
<img src="https://img.shields.io/badge/AWS-%23FF9900.svg?logo=amazon-aws&logoColor=white">
<img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens" style="width:48px;height:auto;border:2px solid #000;border-radius:3px;">
<img src="https://img.shields.io/badge/Swagger-6DB33F?logo=swagger&logoColor=fff">
<img src="https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=fff">
<img src="https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff">
<img src="https://img.shields.io/badge/Caffeine-010a11?logo=buymeacoffee&logoColor=fff">
<img src="https://img.shields.io/badge/Spring%20Security-6DB33F?logo=springsecurity&logoColor=fff">

---

## Portas do projeto 🚪
- **Docker:** _80_
- **Local:** _8081_
- **Dev:** _80_
- **Prd:** _8082_

---

## Rota do Swagger 📄

- **url:** `/tech-sprint-solutions/swagger-ui/index.html`

---

## Rotas de para gerenciar eventos 📍📅

### `POST /tech-sprint-solutions/v1/auth/login`

```bash
curl --location 'http://localhost:80/tech-sprint-solutions/v1/auth/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=C1C72560FAF90BD05C829610920562E8' \
--data-raw '{
    "email": "rh.example@gmail.com",
    "password": "rhExample"
}'
```

> Esta rota é utilizada para efetuar o login do funcionário. A existência do funcionário na empresa é verificada com base
no seu email e senha.

---

### `POST /tech-sprint-solutions/v1/events`

```bash
curl --location 'http://localhost:80/tech-sprint-solutions/v1/events' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=3F26D9D081998A4C9116E554368DF338; JSESSIONID=02BFB81B4EC9DE75C80EF8AAA61C11C3; JSESSIONID=9BBC687181840EEABDFC636AB5CE0186' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhcGktYXV0aCIsInVzZXJuYW1lIjoicmguZXhhbXBsZUBnbWFpbC5jb20iLCJyb2xlcyI6WyJST0xFX1JIIl0sImV4cCI6MTcxNDM1Mjk5Nn0.tn1yie4FsRFArMfN3l47RrrwLY1ZoqJq-JxF8FAq8tQ' \
--data '{
    "eventName": "Reunião com os acionistas 2234",
    "eventDescription": "Reunião que haverá,tendo a presença dos acionistas, a reunião será na sede da empresa e terá a finalidade de discutir os novos produtos da empresa",
    "eventData": "2024-04-27T15:30",
    "status": "FINALIZED"
}'
```

> Nesta rota, Criamos um novo evento,tendo como obrigatórios os campos: eventName, eventDescription, eventData e status.

---

### `GET /tech-sprint-solutions/v1/events/date`

```bash
curl --location 'http://localhost:80/tech-sprint-solutions/v1/events/date' \
--header 'date: 2024-04-27T15:30' \
--header 'Authorization: Bearer SEU_TOKEN_AQUI' \
--header 'Cookie: JSESSIONID=9BBC687181840EEABDFC636AB5CE0186'
```

> Nessa rota,passamos pelo header um campo chamado: "date",nele, buscamos todos os eventos com base na data fornecida

---

### `GET /tech-sprint-solutions/v1/events/name`

```bash
curl --location 'http://localhost:80/tech-sprint-solutions/v1/events/name' \
--header 'name: Reunião com os acionistas' \
--header 'Authorization: Bearer SEU_TOKEN_AQUI' \
--header 'Cookie: JSESSIONID=9BBC687181840EEABDFC636AB5CE0186'
```

> Nessa rota,passamos pelo header um campo chamado: "nome",nele, buscamos o evento com base no nome fornecido.

---

### `PUT /tech-sprint-solutions/v1/events`

```bash
curl --location --request PUT 'http://localhost:80/tech-sprint-solutions/v1/events' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=3F26D9D081998A4C9116E554368DF338; JSESSIONID=9BBC687181840EEABDFC636AB5CE0186' \
--header 'Authorization: Bearer SEU_TOKEN_AQUI' \
--data '{
    "id": "4ca79dd1-251e-4673-bf26-5bbbe356de56",
    "eventName": "Reunião com os acionistas",
    "eventDescription": "Reunião que haverá,tendo a presença dos acionistas, a reunião será na sede da empresa e terá a finalidade de discutir os novos produtos da empresa",
    "eventData": "2024-04-27T15:30",
    "status": "FINALIZED"
}'
```

> Nessa rota,atualizamos um evento com base no body passado, tendo como obrigatórios os campos: eventName, eventDescription, eventData e status.

---

## Processo de instalação 🔧

### Implantação Local

Siga as etapas abaixo para implantar o projeto localmente:

1. Execute os seguintes comandos: :
   ```bash
   docker compose up -d database
2. Aguarde até o processo de instalação do docker finalizar, depois,execute o projeto.

---

### Implantação no Docker

Siga as etapas abaixo para implantar o projeto no Docker:

1. Execute:
   ```bash
   docker compose up
2. Aguarde até o processo de instalação finalizar.
