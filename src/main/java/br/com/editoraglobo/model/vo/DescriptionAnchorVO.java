package br.com.editoraglobo.model.vo;

import java.util.List;

import br.com.editoraglobo.model.DescriptionAbstract;

/**
 * Classe responsável por mapear o JSON que possui o conteudo rastreado a partir
 * da URL especificada.
 * 
 * @author Eduardo Martins
 *
 */
public class DescriptionAnchorVO extends DescriptionAbstract {

	private List<String> content;

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}

}
