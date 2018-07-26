package br.com.editoraglobo.webservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.editoraglobo.crawler.Crawler;
import br.com.editoraglobo.model.vo.ItemVO;
import br.com.editoraglobo.util.CrawlerUtil;

@RestController 
@RequestMapping("/webservice")
public class CrawlerWebService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerWebService.class);
	
	@GetMapping("/crawler")
	@ResponseBody
	public String rastreamento(@RequestParam(value = "uri", required = false) String URI){
        try {
        	if(CrawlerUtil.emptyURI(URI)) {
        		URI = "https://revistaautoesporte.globo.com/rss/ultimas/feed.xml";
        	}
        	
        	List<ItemVO> feed = Crawler.crawlerFeed(URI);
        	
        	//Configuração do Jackson para não exibir propriedades nulas e inserir um valor no ROOT do Json
        	final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true).setSerializationInclusion(Include.NON_NULL); 
        	
        	final ObjectWriter writer = mapper.writer().withRootName("feed");
        	
			return writer.writeValueAsString(feed);
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
       
	}
}
