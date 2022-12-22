
# Desafio Votação

Desafio de API que gerencia sessões de votação.


## Rodando localmente

Para executar o projeto, após baixar o repositório e realizar o download das dependências, deve rodar o **docker-compose** disponível no projeto.



```bash
  docker-compose up -d
```



## Funcionalidades

- Cadastrar e listar todos os associados.
- Cadastrar, listar sessões da pauta e listar todas as pautas.
- Cadastrar, adicionar votos na sessão, listar sessões ativas, listar todas as sessões de voto e mostrar detalhes da sessão.


## Stack utilizada

**Back-end:** Java, Spring Web Flux, Kafka, MongoDB.


## Relacionados

Links para facilitar o uso da aplicação:

- [Documentação da API](http://localhost:8090/api)

- [Verificar os dados do MongoDB](http://localhost:8081/db/votes/)

- [Verificar os tópicos do Kafka](http://localhost:19000/)
