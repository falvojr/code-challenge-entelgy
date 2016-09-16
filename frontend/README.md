# Frontend #

Primeiramente, clone o repositório do projeto via Git e acesse o diretório de `frontend`:

#### SSH ####
```
git clone git@bitbucket.org:falvojr/entelgy-challenge.git && cd frontend
```
#### HTTPS ####
```
git clone https://falvojr@bitbucket.org/falvojr/entelgy-challenge.git && cd frontend
```

## Estrutura ##

Em termos estruturais, o frontend foi estruturado da seguinte maneira:

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

Com o Node.js e NPM devidamente instalados, basta executar o comando a seguir para baixar as dependências definidas no arquivo `package.json`: 

```
npm install
```

*O comando irá gerar a pasta node_modules, que armazenará todas as dependências do projeto.*

## Execução ##

Para auxiliar na execução, o projeto vem preconfigurado com um pequeno servidor web, uma ferramenta Node.js chamada [`live-server`](https://github.com/tapio/live-server).

```
node ./node_modules/live-server/live-server.js . --open=app
```

*O comando acima irá subir a aplicação localmente e abrir o browser no endereço da aplicação.*