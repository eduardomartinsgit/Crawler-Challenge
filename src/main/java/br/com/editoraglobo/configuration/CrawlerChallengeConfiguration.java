package br.com.editoraglobo.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.editoraglobo.crawler.Crawler;

/**
 * Classe responsável pela inicialização do Spring Boot e suas configurações através de annotações.
 * @author Eduardo Martins
 *
 */
@SpringBootApplication(scanBasePackages = {"br.com.editoraglobo"})
public class CrawlerChallengeConfiguration {
	public static void main(String[] args) {
		Crawler.crawlerFeed("https://revistaautoesporte.globo.com/rss/ultimas/feed.xml");
		SpringApplication.run(CrawlerChallengeConfiguration.class, args);
	}
}