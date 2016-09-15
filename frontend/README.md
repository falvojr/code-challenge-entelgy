# Frontend #

Primeiramente, clone o repositório do projeto via [`git`](http://git-scm.com) e acesse o diretório de frontend:

```
git clone git@bitbucket.org:falvojr/entelgy-challenge.git
cd frontend
```

## Dependências ##

Instale as dependências do projeto via [`npm`](https://www.npmjs.org).
Isso instalará o AngularJS, Amgular Material e todas as ferramentas definidas no arquivo `package.json`: 

```
npm install
```

*O comando acima deve gerar a pasta node_modules, que contém os pacotes NPM para das dependências do projeto.*

## Estrutura ##

```
app/                    --> todo o código-fonte da aplicação
  assets/app.css        --> arquivo de estilo padrão (com as customizações necessárias no Angular Material)
  src/                  --> módulos da aplicação
     candidates/        --> pacote com os recursos do domínio de candidatos
	 votes/             --> pacote com os recursos do domínio de votos
  index.html            --> layout da aplicação (template HTML)
```

## Execução ##

O projeto vem pre-configurado com um servidor web de desenvolvimento local. É uma ferramenta Node.js chamada [`live-server`](https://www.npmjs.com/package/live-server).

```
node ./node_modules/live-server/live-server.js . --open=app
```

*O comando acima deve servir a aplicação localmente e abrir o browser padrão no endereço padrão.*