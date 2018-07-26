package br.com.editoraglobo.configuration;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import br.com.editoraglobo.crawler.Crawler;

/**
 * Classe responsável pela inicialização do Spring Boot e suas configurações através de annotações.
 * @author Eduardo Martins
 *
 */
@SpringBootApplication
public class CrawlerChallengeConfiguration {
	public static void main(String[] args) {
		Crawler.crawlerFeed("https://revistaautoesporte.globo.com/rss/ultimas/feed.xml");
		SpringApplication.run(CrawlerChallengeConfiguration.class, args);
	}
}

@Configuration
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Bean
	UserDetailsService users() {
		return new InMemoryUserDetailsManager(Collections.singleton(User.withUsername("admin").roles("ADMIN").password("{noop}pass").build()));
	}
}