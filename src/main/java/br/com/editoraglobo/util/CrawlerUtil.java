package br.com.editoraglobo.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Classe responsável por conter os métodos utilitarios utilizados em toda aplicação.
 * @author Eduar
 *
 */
public class CrawlerUtil {

	public static final String REVISTA_AUTO_ESPORTE_FEED = "https://revistaautoesporte.globo.com/rss/ultimas/feed.xml";
	
	/**
	 * Método responsável por verificar se uma URI está vazia.
	 * @param URI
	 * @return
	 */
	public static boolean emptyURI(String URI) {
		if ((URI == null) || (URI.equals("") || (URI.equals("\"\"") || (URI.equals("\'\'"))))) {
			return true;
		} 
		return false;
	}
	
	/**
	 * Método responsável por configurar o retorno do JSON.
	 * @return
	 */
	public static ObjectWriter writerConfiguration() {
		//Configuração do Jackson para não exibir propriedades nulas e inserir um valor no ROOT do Json
		final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true).setSerializationInclusion(Include.NON_NULL); 
		
		final ObjectWriter writer = mapper.writer().withRootName("feed");
		return writer;
	}	
	
}
