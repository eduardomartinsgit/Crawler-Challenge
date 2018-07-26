package br.com.editoraglobo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por mapear os itens capturados através do rastreamento do
 * RSS.
 * 
 * @author Eduardo Martins
 *
 */
public class ItemVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4042512768994569453L;
	private String title;
	private String link;
	private List<DescriptionVO> description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<DescriptionVO> getDescription() {
		return description;
	}

	public void setDescription(List<DescriptionVO> description) {
		this.description = description;
	}

}
