package GRAPHIQUE;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class ReadInputCount {
    /**
     * Lit le nombre d'entrées dans le fichier XML de définition du composant.
     * Cherche la balise <inputs> sous la racine <component>.
     *
     * @param filename Chemin vers le fichier XML (par ex. "custom_components2/MyGate.xml")
     * @return le nombre d'entrées, ou 2 si la balise est introuvable ou mal formée
     */
	public static int getInputCount(String filename) {
	    try {
	        File file = new File(filename);
	        DocumentBuilder builder = DocumentBuilderFactory
	                                    .newInstance()
	                                    .newDocumentBuilder();
	        Document doc = builder.parse(file);
	        Element root = doc.getDocumentElement();
	        NodeList inputsList = root.getElementsByTagName("inputs");
	        if (inputsList.getLength() > 0) {
	            String txt = inputsList.item(0).getTextContent().trim();
	            return Integer.parseInt(txt);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    // Par défaut si erreur ou balise manquante
	    return 2;
	}

}
