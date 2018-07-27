
## Desafio Desenvolvedor Java
> **Desenvolvedor:** Eduardo Martins - [LinkedIn
](https://www.linkedin.com/in/eduardo-martins-849534110/)

<br>
<br>
Criar um crawler que leia [este feed](http://revistaautoesporte.globo.com/rss/ultimas/feed.xml) e retorne um json estruturado
<br>
<br>
<br>

|| Tecnologias Utilizadas|
|--|--|
| Linguagem de Programação| Java |
| Framework MVC| Spring Boot Web |
| REST Web Services| Spring Boot Web |
| Criação de Arquivos de Log| Log4J|
| Ferramenta de Automação de Compilação| Apache Maven|
| Container de Servlet/Servidor de Aplicação| Apache Tomcat |
| Ambiente de Desenvolvimento(IDE)| Eclipse |
| Segurança| Spring Boot Security |
| Utilitarios| Jsoup |
| | Apache Commons |
| | Spring Boot Devtools |

# Configurações Necessárias
#### Dependencias do Maven

```xml 
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.jsoup/jsoup -->
		<dependency>
			<groupId>org.jsoup</groupId>
			<artifactId>jsoup</artifactId>
			<version>1.11.2</version>
		</dependency>		
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
			<version>3.1</version><!--$NO-MVN-MAN-VER$ -->
		</dependency>
	</dependencies>
```
#### Servlet Container/Servidor de Aplicação

    Apache TomCat embarcado no Spring Boot

#### Ambiente de Desenvolvimento(IDE)

    Eclipse JEE Oxygen

#### WebServices

    @GET
    - Parametro: uri (Caso o parâmetro seja nulo, será utilizado como default a URI https://revistaautoesporte.globo.com/rss/ultimas/feed.xml)
    http://localhost:8080/crawler  - Retorna o JSON estruturado conforme a especificação.

#### Configuração da Aplicação no Eclipse

    O exemplo a seguir pode ser utilizado para configurar a aplicação em seu Eclipse.
	Após realizar o download do projeto compactado em um arquivo.zip, 
    extraia o arquivo no local de sua preferência.
     
    Realize a importação do projeto maven para sua workspace.
    
    Após a importação do projeto, localize a classe (Atalho do Eclipse: Control + Shift + R) CrawlerChallengeConfiguration.
    Clique com o botão direito do mouse dentro da classe e selecione: Run As -> Java Application.

![inicialização do projeto](https://uploaddeimagens.com.br/images/001/531/410/original/Crawler.png?1532653873)