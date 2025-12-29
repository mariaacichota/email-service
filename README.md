# email-service
Projeto Spring Boot para envio de e-mails via diferentes serviços de integração (AWS e OCI).
O sistema utiliza injeção de dependências, configuração via propriedades externas e o padrão de projeto Factory para selecionar dinamicamente o serviço de envio.

## Organização do Projeto

```bash
src/
 ├── main/
 │    ├── java/
 │    │    └── com/viasoft/email_service/
 │    │         ├── config/
 │    │         │     └── AppProperties.java        # Classe para mapear propriedades customizadas (ex: mail.integracao)
 │    │         ├── factory/
 │    │         │     └── EmailServiceFactory.java  # Fábrica que retorna o serviço de e-mail (AWS ou OCI) com base na config
 │    │         ├── service/
 │    │         │     ├── EmailService.java          # Interface do serviço de e-mail
 │    │         │     ├── AwsEmailServiceImpl.java   # Implementação do serviço para AWS
 │    │         │     └── OciEmailServiceImpl.java   # Implementação do serviço para OCI
 │    │         ├── controller/
 │    │         │     └── EmailController.java       # Controller REST para expor endpoints de envio de e-mail
 │    │         └── EmailServiceApplication.java     # Classe principal para rodar a aplicação Spring Boot
 │    └── resources/
 │          └── application.properties               # Arquivo de propriedades da aplicação (porta, configs mail.integracao, etc.)
 └── test/
      └── java/
           └── com/viasoft/email_service/
                └── factory/
                     └── EmailServiceFactoryTest.java # Testes unitários para a fábrica de serviços de e-mail
```

## Dependências Utilizadas

### Spring Boot Starter Web
Fornece suporte para desenvolvimento web e REST APIs com Spring MVC.

### Spring Boot Starter Test
Framework para testes unitários e integração, incluindo JUnit 5, Mockito, AssertJ.

### Mockito
Framework para criação de mocks em testes unitários, permitindo simular comportamentos de classes e dependências.

### Spring Context
Gerencia injeção de dependência, ciclo de vida dos beans e configuração da aplicação.

## Conceitos REST Utilizados

A aplicação expõe endpoints REST para envio de e-mails através do EmailController.
A comunicação é realizada via HTTP, utilizando o método POST, com envio e recebimento de dados no formato JSON.

O Factory Pattern é utilizado para selecionar dinamicamente qual serviço interno será responsável pelo envio do e-mail (AWS ou OCI), conforme configuração definida em application.properties.

Além disso, o projeto realiza tratamento de erros no controller, retornando respostas HTTP apropriadas, como:

* "400 Bad Request" para dados inválidos;
* "500 Internal Server Error" para falhas internas.

## Como Rodar o Projeto

### Pré-requisitos

* JDK 17+ instalado;
* Maven;
* IDE (IntelliJ IDEA, Eclipse, ou similares) – para fazer esse projeto, utilizei o IntelliJ!

### Passos para rodar
1. Clone o repositório:
   
```bash
https://github.com/mariaacichota/email-service.git
```

2. Faça o build da aplicação:
   
```bash
./mvnw clean install
```

3. Execute a aplicação:
   
```bash
./mvnw spring-boot:run
```

### Para rodar os testes
1. Para rodar os testes feitos com JUnit, use:
   
```bash
./mvnw test
```

_Observação: a aplicação irá rodar na porta `8080` – conforme configurado em `application.properties`._

## Testando a API com Postman

Aplicação REST criada tem um endpoint que recebe dados para envio de email, com apenas uma requisição, sem alterar o objeto de entrada, dependendo da configuração setada em application.properties o objeto deve ser adaptado para novas classes, também deve ser serializado e impresso no console. Portanto, para testá-la no Postman, você deve:

1. Criar uma nova requisição do tipo `POST`;
2. Utilize a URL `http://localhost:8080/api/email`;
3. Vá até a aba `Body`, selecione raw e tipo `JSON`;
4. Utilize o `JSON` a seguir:

```bash
{
  "recipientEmail": "destinatario@teste.com",
  "recipientName": "João da Silva",
  "senderEmail": "remetente@empresa.com",
  "subject": "Assunto de Teste",
  "content": "Conteúdo do e-mail para envio"
}
```

5. Clique em Send;
6. Verifique o status da requisição:
* Se a requisição ocorrer com sucesso irá retornar status 204;
* Se a requisição falhar, irá retornar os erros com status 400 ou 500.

### Configuração via `application.properties`

As configurações da aplicação estão setadas como:
```bash
spring.application.name=email-service
mail.integracao=AWS
server.port=8080
```
Para testar integração OCI, basta trocar `mail.integracao=OCI`. Se desejar, também pode alterar o valor da porta.

## Resumo do Projeto

* O controller expõe o endpoint `/api/email` via `POST` para enviar e-mail, um DTO é usado para receber dados JSON e então o serviço correto é escolhido pela `EmailServiceFactory`, que usa o valor `mail.integracao`;
* Os testes de integração usam `TestRestTemplate` para simular requisição REST real.
* Os erros são tratados e retornam HTTP `400` ou `500` conforme o caso.

Esse projeto é baseado na arquitetura de camadas, com componentes desacoplados, seguindo as lógicas dos princípios SOLID e Clean Code que garantem um bom funcionamento e legibilidade do código. As camadas do projeto se descrevem de tal forma que:
* `controller`: expõe endpoints REST.
* `service`: centraliza regras de negócio.
* `factory`: separa lógica de escolha de implementação.
* `dto`: abstração para entrada/saída de dados.
* `config`: configuração isolada (como AppProperties).
* `enum`: define integração permitida (AWS/OCI).

Seguindo os princípios SOLID, cada classe tem uma única responsabilidade clara; o código é aberto à extensão, fechado para modificação; o princípio de substituição é usado, onde AwsEmailServiceImpl e OciEmailServiceImpl herdam de EmailService e podem ser utilizadas no lugar deste; há uma interface única, adequada ao caso; é utilizada a injeção de dependência pelo Spring e via construtor em testes. Além disso, o Design Pattern que pode ser encontrado no projeto se apresenta como:
* `Factory Pattern`: EmailServiceFactory escolhe implementação baseada em configuração;
* `Strategy Pattern`: cada EmailServiceImpl lida com a adaptação da mensagem para um formato específico (AWS ou OCI);
* `DTO Pattern`: usado para abstrair e validar dados de entrada.

## Colaboradores  
<table>
  <tr>
    <td align="center"><a href="https://github.com/mariaacichota"><img src="https://avatars.githubusercontent.com/mariaacichota" width="80px;" alt="Maria Cichota"/><br /><sub><b>Maria Cichota</b></sub></a><br /><a href="#" title="Code">💻🛠️</a></td>
  </tr>
</table>
