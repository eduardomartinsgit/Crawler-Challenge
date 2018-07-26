package br.com.editoraglobo.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
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

	/**
	 * Método responsável por realizar o rastreamento e capturação dos dados a partir de uma URL de RSS(FEED).
	 * @param URL_FEED
	 * @return
	 */
	public static List<ItemVO> crawlerFeed(String URL_FEED){
		
		List<ItemVO> listItems = new ArrayList<ItemVO>();
		try {
			Document document  = Jsoup.connect(URL_FEED).get();
			Elements itemElements = document.select("item");
			
			for (Element itemElement : itemElements) {
				if(itemElement != null) {
					
					ItemVO item = new ItemVO();
					Element descriptionUnparse = itemElement.selectFirst("description");
					Document descriptionParse = Jsoup.parse(descriptionUnparse.text());
					List<DescriptionVO> listDescriptionContent = new ArrayList<DescriptionVO>();
					
					item.setTitle(itemElement.select("title").text());
					item.setLink(itemElement.select("link").text());
					
					
					crawlerParagraphTag(descriptionParse, listDescriptionContent);
					crawlerImageTag(descriptionParse, listDescriptionContent);
					
					Elements linkElements = descriptionParse.select("ul a");
					List<String> listDescriptionAnchor = new ArrayList<String>();
					DescriptionVO content = crawlerAnchorTag(linkElements, listDescriptionAnchor);
					
					content.setContents(listDescriptionAnchor);
					listDescriptionContent.add(content);
					
					item.setDescription(listDescriptionContent);
					listItems.add(item);
				}
				
			}			
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return listItems;
	}

	/**
	 * Método responsável por obter o link contidos na tag "a" (anchor).
	 * @param anchorElements
	 * @param listDescriptionAnchor
	 * @return
	 */
	private static DescriptionVO crawlerAnchorTag(Elements anchorElements, List<String> listDescriptionAnchor) {
		LOGGER.debug("RASTREANDO TAGS <a>");
		
		DescriptionVO descriptionContent = new DescriptionVO();
		descriptionContent.setType("link");
		for (Element anchorElement : anchorElements) {
			if(!anchorElement.text().equals("") && !anchorElement.attr("href").equals("")) {
				listDescriptionAnchor.add(anchorElement.attr("href"));
			}
		}
		return descriptionContent;
	}

	/**
	 * Método responsável por obter o source contidos na tag "img" (image).
	 * @param descriptionParse
	 * @param listDescriptionContent
	 */
	private static void crawlerImageTag(Document descriptionParse, List<DescriptionVO> listDescriptionContent) {
		LOGGER.debug("RASTREANDO TAGS <img>");
		
		Elements imageElements = descriptionParse.select("img");
		for (Element imageElement : imageElements) {
			if(!imageElement.text().equals("")) {
				DescriptionVO descriptionContent = new DescriptionVO();
				descriptionContent.setType("image");
				descriptionContent.setContent(imageElement.attr("src"));
				listDescriptionContent.add(descriptionContent);
			}					
		}
	}

	/**
	 * Método responsável por obter o texto contidos na tag "p" (paragraph).
	 * @param descriptionParse
	 * @param listDescriptionContent
	 */
	private static void crawlerParagraphTag(Document descriptionParse, List<DescriptionVO> listDescriptionContent) {
		LOGGER.debug("RASTREANDO TAGS <p>");
		
		Elements paragraphElements = descriptionParse.select("p");
		for (Element paragraphElement : paragraphElements) {
			if(!paragraphElement.text().equals("")) {
				DescriptionVO descriptionContent = new DescriptionVO();
				descriptionContent.setType("text");
				descriptionContent.setContent(StringEscapeUtils.unescapeHtml4(paragraphElement.text()));
				listDescriptionContent.add(descriptionContent);
			}
		}
	}
}
