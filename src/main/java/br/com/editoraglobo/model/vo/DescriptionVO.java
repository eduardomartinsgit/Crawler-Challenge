package br.com.editoraglobo.model.vo;

import br.com.editoraglobo.model.DescriptionAbstract;

/**
 * Classe responsável por mapear o JSON que possui o conteudo rastreado a partir
 * da URL especificada.
 * 
 * @author Eduardo Martins
 *
 */
public class DescriptionVO extends DescriptionAbstract{

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
