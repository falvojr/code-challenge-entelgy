#Comentários#

Nas seções a seguir, irei descrever as decisões mais relevantes do ponto de vista técnico.

## Spring Boot ##

Nunca havia utilizado esse módulo do Spring na prática, mas fiquei bastante impressionado com a organização e facilidades que ele proporciona. 
Além disso, pude utilizar uma arquitetura que se assemelha em muitos aspectos com o MEAN (MongoDB, [ExpressJS](https://github.com/expressjs/express), AngularJS e Node.js), uma abordagem emergente atualmente. 
Em termos praticos o Node.js deu lugar ao Java e o ExpressJS ao Spring.

## MongoDB ##

Com relação ao banco de dados, optei por um NoSQL pois o domínio da aplicação não exigia relacionamentos fortes entre as entidades. 
Com isso, pude obter um ganho considerável de performance, mesmo utilizando uma instância free no [mLab](https://mlab.com).
Caso queira utilizar o MongoDB localmente, basta alterar a constante `MONGODB_CLIENT_URI` no arquivo `MongoConfig.java`.

## CORS Filter ##

Devido à estrutura atual dos projeto, tive de permitir acesso aos métodos GET e PATH em meu CORS Filter. 
Assim, as aplicações (backend e frontend) podems e comunicar mesmo em domínios diferentes.

## Ecoluções ##

#### Make file ####