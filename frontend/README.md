# Frontend #

Primeiramente, clone o repositório do projeto via [`git`](http://git-scm.com) e acesse o diretório de frontend:

```
git clone git@bitbucket.org:falvojr/entelgy-challenge.git
cd frontend
```

## Estrutura ##

O projeto possui a seguinte estrutura de pastas

```
app/                    --> todo o código-fonte da aplicação.
  assets/app.css        --> arquivo de estilo padrão, com apenas algumas customizações de estilos.
  src/                  --> módulos da aplicação.
     candidates/        --> pacote com os recursos do domínio de candidatos.
  templates/            --> pacote com templates HTML.
  index.html            --> página principal da nossa SPA (Single Page Application).
package.json            --> descreve o projeto, informando a url do repositório, versão, dependências, dentre outras coisas.
```

## Pré-Requisitos ##

#### Node.js ####
```
curl -sL https://deb.nodesource.com/setup_4.x | sudo -E bash -
sudo apt-get install -y nodejs
```
#### NPM ####

```
npm install npm -g
```

## Dependências ##

Instale as dependências do projeto via [`npm`](https://github.com/npm/npm).
Isso instalará o AngularJS, Angular Material e todas as ferramentas definidas no arquivo `package.json`: 

```
npm install
```

*O comando acima deve gerar a pasta node_modules, que contém os pacotes NPM para das dependências do projeto.*

## Execução ##

O projeto vem preconfigurado com um pequeno servidor web, uma ferramenta Node.js chamada [`live-server`](https://github.com/tapio/live-server).

```
node ./node_modules/live-server/live-server.js . --open=app
```

*O comando acima deve servir a aplicação localmente e abrir o browser padrão no endereço da aplicação.*