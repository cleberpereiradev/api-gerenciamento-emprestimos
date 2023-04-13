# Capacitação Java + Angular Minsait - Programa jovens profissionais e 40 +.
## API Rest para simulação de gerenciamento bancário, utilizando Java + Spring Boot.



![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

Projeto simples de gerenciamento de clientes e empréstimos de uma instituição bancária, o objetivo desta implementação é aplicar os conhecimentos adquiridos durante as aulas da capacitação Java, oferecidas pela empresa Minsait. O instrutor foi o Gleyses Guimarães, que ministrou aulas desde o básico da linguagem, fundamentos do paradigma de programação Orientada à Objetos e desenvolvimento de uma API Rest de livraria.



Para o desenvolvimento do projeto, foram seguidas algumas etapas, estas são: 



  - Utilização do Spring Boot initializr

  - Modelagem de dados para criação das classes e entidades

  - Desenvolvimento do sistema de CRUD de clientes, utilizando arquitetura REST. (POST, GET, PUT e DELETE)

  - Desenvolvimento do sistema CRUD de empréstimos ( Associação direta com clientes, com os verbos POST, GET e DELETE)

  - Aplicação das regras de negócio, seguindo os requisitos descritos no documento de [Requisitos]()

  - Finalização das validações necessárias

  - Testes via Postman

  - Criação da documentação da API, utilizando swagger e javadoc


## Tecnologias

  - ``JAVA 17 - Linguagem de programação (JDK 1.8).``
  - ``Spring - Framework MVC.``
  - ``Apache Maven 3.8.6 - Gerenciador de dependências.``
  - ``IntelliJ - IDE para desenvolvimento.``
  - ``H2 Database - Banco de dados relacional em memória.``
  - ``Swagger 3.0.0 - Documentação de APIs REST.``

  Dependências do projeto disponíveis no [pom.xml](https://github.com/cleberpereiradev/api-gerenciamento-emprestimos/blob/master/pom.xml)
  
## Banco de dados


  Para acessar o banco de dados H2:
  
  ### http://localhost:8080/h2-console
  
  |Entrada|Valor|
  |:-------:|:-----:|
  |Driver class|org.h2.Driver|
  |JDBC URL|jdbc:h2:mem:banco|
  |Username|cleber|
  |Password|em branco|

  

## Endpoints

As endpoints disponíveis para consulta e testes são:

  ### http://localhost:8080/api/v1/banco
  
  |Método    | Endpoint                          | Retorno                                    |
  |----------|-----------------------------------|--------------------------------------------|
  |POST :    |  "/api/v1/banco/clientes"         |   Adiciona um cliente ao sistema           |
  |GET :     |  "/api/v1/banco/clientes"         |   Retorna uma lista de todos os clientes   |
  |GET :     |  "/api/v1/banco/clientes/:cpf"    |   Retorna o cliente com o cpf requisitado  |
  |PUT :     |  "/api/v1/banco/clientes/:cpf"    |   Altera o cliente com o cpf requisitado   |
  |DELETE :  |  "/api/v1/banco/clientes/:cpf"    |   Remove o cliente com o cpf requisitado   |
  
  ### http://localhost:8080/api/v1/:cpf/emprestimos

  | Método     | Endpoint                                          | Retorno                                                                                |
  |------------|---------------------------------------------------|----------------------------------------------------------------------------------------|
  | POST:      |  "/api/v1/banco/clientes/:cpf/emprestimos"        |   Adiciona um empréstimo associado ao cpf do cliente                                   |
  | GET:       |  "/api/v1/banco/clientes/:cpf/emprestimos"        |   Retorna uma lista de empréstimos do cliente com cpf requisitado                      |
  | GET:       |  "/api/v1/banco/clientes/:cpf/emprestimos/:id"    |   Retorna o empréstimo do cliente com o cpf requisitado, referente ao id do empréstimo |
  | DELETE:    |  "/api/v1/banco/clientes/:cpf/:id"                |   Remove o empréstimo do cliente com o cpf requisitado, referente ao id do empréstimo  |


 
  
  
