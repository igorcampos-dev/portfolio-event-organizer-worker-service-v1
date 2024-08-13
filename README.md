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

> Esta rota é utilizada para efetuar o login do funcionário. A existência do funcionário na empresa é verificada com base
no seu email e senha.

---

### `POST /tech-sprint-solutions/v1/events`

> Nesta rota, Criamos um novo evento,tendo como obrigatórios os campos: eventName, eventDescription, eventData e status.

---

### `GET /tech-sprint-solutions/v1/events/date`

> Nessa rota,passamos pelo header um campo chamado: "date",nele, buscamos todos os eventos com base na data fornecida

---

### `GET /tech-sprint-solutions/v1/events/name`

> Nessa rota,passamos pelo header um campo chamado: "nome",nele, buscamos o evento com base no nome fornecido.

---

### `PUT /tech-sprint-solutions/v1/events`

> Nessa rota,atualizamos um evento com base no body passado, tendo como obrigatórios os campos: eventName, eventDescription, eventData e status.