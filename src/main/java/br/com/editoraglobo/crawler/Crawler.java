package br.com.editoraglobo.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.editoraglobo.model.DescriptionVO;
import br.com.editoraglobo.model.ItemVO;

/**
 * Classe responsável por prover método de rastreamento(crawler) de conteudo a partir de uma URL
 * @author Eduardo Martins
 *
 */
public class Crawler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Crawler.class);

	public static List<ItemVO> rastreamentoFeed(String URL_FEED){
		
		Document documento;
		List<ItemVO> listaObjetos = new ArrayList<ItemVO>();
		try {
			documento = Jsoup.connect(URL_FEED).get();
			Elements items = documento.select("item");
			
			for (Element item : items) {
				if(item != null) {
					
					ItemVO elemento = new ItemVO();
					Element descriptionUnparse = item.selectFirst("description");
					Document descriptionParse = Jsoup.parse(descriptionUnparse.text());
					List<DescriptionVO> listaContent = new ArrayList<DescriptionVO>();
					
					elemento.setTitle(item.select("title").text());
					elemento.setLink(item.select("link").text());
					
					
					LOGGER.debug("RASTREANDO TAGS <p>");
					
					Elements elementosTexto = descriptionParse.select("p");
					
					for (Element texto : elementosTexto) {
						if(!texto.text().equals("")) {
							DescriptionVO content = new DescriptionVO();
							content.setType("text");
							listaContent.add(content);
						}
					}
					
					LOGGER.debug("RASTREANDO TAGS <img>");
					
					Elements elementosImagem = descriptionParse.select("img");
					
					for (Element imagem : elementosImagem) {
						if(!imagem.text().equals("")) {
							DescriptionVO content = new DescriptionVO();
							content.setType("image");
							content.setContent(imagem.attr("src"));
							listaContent.add(content);
						}					
					}
					
					LOGGER.debug("RASTREANDO TAGS <a>");
					
					DescriptionVO content = new DescriptionVO();
					content.setType("link");
					
					listaContent.add(content);
					
					elemento.setDescription(listaContent);
					listaObjetos.add(elemento);
				}
				
			}			
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return listaObjetos;
	}
}
