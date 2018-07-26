package br.com.editoraglobo.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe responsável pela inicialização do Spring Boot e suas configurações através de annotações.
 * @author Eduardo Martins
 *
 */
@SpringBootApplication
public class CrawlerChallengeConfiguration {
	public static void main(String[] args) {
		SpringApplication.run(CrawlerChallengeConfiguration.class, args);
	}
}
