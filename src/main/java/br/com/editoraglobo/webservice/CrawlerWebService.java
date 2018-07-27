package br.com.editoraglobo.webservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.editoraglobo.crawler.Crawler;
import br.com.editoraglobo.model.vo.ItemVO;
import br.com.editoraglobo.util.CrawlerUtil;

@RestController 
@RequestScope
public class CrawlerWebService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerWebService.class);
	
	@GetMapping("/crawler")
	@ResponseBody
	public String crawlerWebService(@RequestParam(value = "uri", required = false) String URI){
        try {
        	if(CrawlerUtil.emptyURI(URI)) {
        		URI = CrawlerUtil.REVISTA_AUTO_ESPORTE_FEED;
        	}
        	
        	List<ItemVO> feed = Crawler.crawlerFeed(URI);
        	
        	final ObjectWriter writer = CrawlerUtil.writerConfiguration();
        	
			return writer.writeValueAsString(feed);
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
       
	}
}
