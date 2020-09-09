# Twt-Git-Service

* Projeto criado com a intenção de cruzar as informações
 encontradas nos repositórios do Git e mensagens relacionadas no Twitter,
 a partir de uma palavra chave.
 
* Endpoints

  
  Utiliza a palavra chave do arquivo de propriedades
  * /search-info/get-tweets
  
  Utiliza a palavra chave passada por parâmetro
  * /search-info/get-tweets/{classification}   
 
 
 
* Tecnologias necessárias para rodar o projeto:
  * Java 1.8
  * Maven
* Necessário também um token para utilização da API do Twitter

(Dentro da pasta do projeto)
* Pode ser instalado através do seguinte comando: `mvn clean install`
* E para executar, o seguinte comando: `spring-boot:run`

* Todas as configurações estão no arquivo `application.properties`
