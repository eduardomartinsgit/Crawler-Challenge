package br.com.editoraglobo.model;

import java.util.List;

/**
 * Classe responsável por mapear o JSON que possui o conteudo rastreado a partir
 * da URL especificada.
 * 
 * @author Eduardo Martins
 *
 */
public class DescriptionVO {

	private String type;
	private String content;
	private List<String> contents;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getContents() {
		return contents;
	}

	public void setContents(List<String> contents) {
		this.contents = contents;
	}

}
