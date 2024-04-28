# API de Event Manager

---

## Introdução

Este projeto foi desenvolvido por Igor de Campos com o objetivo de testar suas habilidades lógicas e experiências em
programação.

---

## Sobre o Projeto

A API de Contra Cheques é uma solução backend empresarial que permite que qualquer funcionário do setor do RH gerencie eventos da empresa.

---

## Processo de instalação

### Implantação Local

1. **Requisitos minimos:**
    - Jdk 22
    - Docker

Siga as etapas abaixo para implantar o projeto localmente:

1. Execute os seguintes comandos: :
   ```bash
   docker compose up -d database
2. Aguarde até o processo de instalação do docker finalizar, depois,execute o projeto.

---

### Implantação no Docker

1. **Requisitos minimos:**
    - Docker

Siga as etapas abaixo para implantar o projeto no Docker:

1. Execute:
   ```bash
   docker compose up
2. Aguarde até o processo de instalação finalizar.

---

## Atenção!

Todos os Curls declarados no Readme são do ambiente de dev, certifique-se de mudar a porta para a porta do ambiente adequado

---

## Portas do projeto:
- **docker:** 80
- **local:** 8081
- **dev:** 80
- **prd:** 8082

---

## Rota do Swagger:
- **url:** `/tech-sprint-solutions/swagger-ui/index.html#/`

---

## Rotas para Funcionários

---

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

Esta rota é utilizada para efetuar o login do funcionário. A existência do funcionário na empresa é verificada com base
no seu email e senha.

<br>

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

Nesta rota, Criamos um novo evento,tendo como obrigatórios os campos: eventName, eventDescription, eventData e status.

<br>

---

### `GET /tech-sprint-solutions/v1/events/date`

```bash
curl --location 'http://localhost:80/tech-sprint-solutions/v1/events/date' \
--header 'date: 2024-04-27T15:30' \
--header 'Authorization: Bearer SEU_TOKEN_AQUI' \
--header 'Cookie: JSESSIONID=9BBC687181840EEABDFC636AB5CE0186'
```

Nessa rota,passamos pelo header um campo chamado: "date",nele, buscamos todos os eventos com base na data fornecida

<br>

---

### `GET /tech-sprint-solutions/v1/events/name`

```bash
curl --location 'http://localhost:80/tech-sprint-solutions/v1/events/name' \
--header 'name: Reunião com os acionistas' \
--header 'Authorization: Bearer SEU_TOKEN_AQUI' \
--header 'Cookie: JSESSIONID=9BBC687181840EEABDFC636AB5CE0186'
```

Nessa rota,passamos pelo header um campo chamado: "nome",nele, buscamos o evento com base no nome fornecido.

<br>

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

Nessa rota,atualizamos um evento com base no body passado, tendo como obrigatórios os campos: eventName, eventDescription, eventData e status.

<br>

