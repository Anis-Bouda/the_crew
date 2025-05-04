package GRAPHIQUE;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;

public class ReadFirstConnectionState {

    public static String getFirstConnectionState(String filePath) {
        try {
            // Chemin vers le fichier XML passé en paramètre
            File xmlFile = new File(filePath);

            // Vérifie si le fichier existe
            if (!xmlFile.exists()) {
                System.out.println("Le fichier " + filePath + " n'existe pas.");
                return null; // Si le fichier n'existe pas, retourne null
            }

            // Initialisation du parseur
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parsing du fichier
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Accéder à tous les éléments <component>
            NodeList components = doc.getElementsByTagName("component");

            if (components.getLength() == 0) {
                System.out.println("Aucune balise <component> trouvée dans le fichier.");
                return null; // Si aucune balise <component> n'existe
            }

            // Prendre le premier <component>
            Element firstComponent = (Element) components.item(0);

            // Lire la valeur de <state>
            String state = firstComponent.getElementsByTagName("state").item(0).getTextContent();

            // Afficher le state
            System.out.println("State du premier composant : " + state);

            return state;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la lecture du fichier XML : " + e.getMessage());
            return null; // Retourne null en cas d'erreur
        }
    }
}
