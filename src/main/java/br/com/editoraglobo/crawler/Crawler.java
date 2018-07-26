package br.com.editoraglobo.crawler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.editoraglobo.model.DescriptionAbstract;
import br.com.editoraglobo.model.vo.DescriptionAnchorVO;
import br.com.editoraglobo.model.vo.DescriptionVO;
import br.com.editoraglobo.model.vo.ItemVO;

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
					List<DescriptionAbstract> listDescriptionContent = new ArrayList<DescriptionAbstract>();
					
					item.setTitle(itemElement.select("title").text());
					item.setLink(itemElement.select("link").text());
					Elements linkElements = descriptionParse.select("ul a");
					
					crawlerParagraphTag(descriptionParse, listDescriptionContent);
					crawlerImageTag(descriptionParse, listDescriptionContent);
					crawlerAnchorTag(linkElements, listDescriptionContent);
					
					item.setDescription(listDescriptionContent);
					listItems.add(item);
				}
				
			}			
		} catch (Exception e) {
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
	private static void crawlerAnchorTag(Elements anchorElements, List<DescriptionAbstract> listDescriptionContent) {
		LOGGER.debug("RASTREANDO TAGS <a>");
		
		List<String> sourceAnchor = new ArrayList<String>();
		DescriptionAnchorVO descriptionContent = new DescriptionAnchorVO();
		
		descriptionContent.setType("link");
		for (Element anchorElement : anchorElements) {
			if(!anchorElement.text().equals("") && !anchorElement.attr("href").equals("")) {
				sourceAnchor.add(anchorElement.attr("href"));
			}
		}
		descriptionContent.setContent(sourceAnchor);
		listDescriptionContent.add(descriptionContent);
	}

	/**
	 * Método responsável por obter o source contidos na tag "img" (image).
	 * @param descriptionParse
	 * @param listDescriptionContent
	 */
	private static void crawlerImageTag(Document descriptionParse, List<DescriptionAbstract> listDescriptionContent) {
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
	private static void crawlerParagraphTag(Document descriptionParse, List<DescriptionAbstract> listDescriptionContent) {
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
