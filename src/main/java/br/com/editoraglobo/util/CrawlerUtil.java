package br.com.editoraglobo.util;

/**
 * Classe responsável por conter os métodos utilitarios utilizados em toda aplicação.
 * @author Eduar
 *
 */
public class CrawlerUtil {

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
}
