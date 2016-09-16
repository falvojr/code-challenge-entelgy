#Comentários#

Nas seções a seguir, irei descrever as decisões mais relevantes do ponto de vista técnico.

## Spring Boot, Data e REST ##

Nunca havia utilizado esses módulos do Spring juntos, mas fiquei bastante impressionado com a organização e facilidades que ele proporciona. 
Além disso, pude utilizar uma arquitetura que se assemelha em muitos aspectos com o MEAN (MongoDB, [ExpressJS](https://github.com/expressjs/express), AngularJS e Node.js), uma abordagem emergente atualmente. 
Em termos praticos o Node.js deu lugar ao Java e o ExpressJS ao Spring.

## MongoDB ##

Com relação ao banco de dados, optei por um NoSQL pois o domínio da aplicação não exigia relacionamentos fortes entre as entidades. 
Com isso, pude obter um ganho considerável de performance, mesmo utilizando uma instância free no [mLab](https://mlab.com).
Caso queira utilizar o MongoDB localmente, basta alterar a constante `MONGODB_CLIENT_URI` no arquivo `MongoConfig.java`.

## CORS Filter ##

Devido à estrutura atual dos projeto, tive de permitir acesso aos métodos GET e PATH em meu CORS Filter na classe `RestConfig.java`. 
Assim, as aplicações (backend e frontend) podems e comunicar mesmo em domínios diferentes.

## AngularJS e Angular Material ##

Tais escolhas para o frontend se devem a duas coisas:

1. O Angulat Material é uma implementação de referência do Google Material Design, talves um dos padrões mais difundidos atualmente. Além disso, é totalmente responsivo e aderente ao conceito de Mobile First;
2. Já o Angular utilizei porque tenho boa familiaridade com o framework e isso me ajudou em termos de produtividade. Apesar da versão 2 já estar disponível a algum tempo, a migração é significativamente complexa. De qualquer forma, todo código implementado seguiu os guidelines de [John Papa](https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md).

## TODO ##

Como evoluções futuras, pensei nos seguintes itens:

* **Makefile**: arquivo com scripts organizados de modo a facilitar os testes e implantação do projeto; 
* **ECS6**: migrar todo o frontend para o ECMAScript 6, mesmo que para isso fosse necessário utilizar um compilador/tradutor como o [Babel](https://github.com/babel/babel/). Pois a nova versão dessa especificação traz melhorias fantásticas para o JavaScript;
* **i18n**: criar um mecanismo de internacionalização no front e também banckend.