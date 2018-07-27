package br.com.editoraglobo.configuration.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Método responsável por realizar uma configuração básica de segurança para
 * acesso as URIs providas pelo sistema. USER: admin PASS: pass
 * 
 * @author Eduardo Martins
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Bean
	UserDetailsService users() {
		return new InMemoryUserDetailsManager(Collections.singleton(User.withUsername("admin").roles("ADMIN").password("{noop}pass").build()));
	}
}