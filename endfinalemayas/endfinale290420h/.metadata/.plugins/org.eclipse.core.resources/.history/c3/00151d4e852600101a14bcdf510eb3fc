import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class XMLToJavaGenerator {

    /**
     * Méthode pour générer le fichier Java à partir du fichier XML.
     * @param xmlFileName Le nom du fichier XML d'entrée.
     * @throws Exception Si le fichier XML est invalide ou si une erreur survient lors de la génération du fichier Java.
     */
    public static void generateJavaFromXML(String xmlFileName) throws Exception {
        // Fichier XML d'entrée
        File xmlFile = new File(xmlFileName);

        // Parse le fichier XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);

        // Récupération des composants et connexions
        NodeList composants = document.getElementsByTagName("composant");
        NodeList connexions = document.getElementsByTagName("connexion");

        // Création du fichier Java de sortie
        StringBuilder javaCode = new StringBuilder();
        javaCode.append("import gates.*;\n\n");
        javaCode.append("public class GeneratedCircuit {\n");
        javaCode.append("    public static void main(String[] args) {\n");

        // Déclaration des composants
        Map<String, String> idToComponent = new HashMap<>();
        for (int i = 0; i < composants.getLength(); i++) {
            Node node = composants.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String type = element.getAttribute("type");
                String id = element.getAttribute("id");
                String x = element.getAttribute("x");
                String y = element.getAttribute("y");

                // Déclaration du composant dans le code Java
                javaCode.append("        " + type + " " + id + " = new " + type + "();\n");
                javaCode.append("        " + id + ".setBounds(" + x + ", " + y + ", 100, 100);\n");
            }
        }

        // Connexion des composants
        for (int i = 0; i < connexions.getLength(); i++) {
            Node node = connexions.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String source = element.getAttribute("source");
                String sourcePort = element.getAttribute("sourcePort");
                String target = element.getAttribute("target");
                String targetPort = element.getAttribute("targetPort");

                // Connexion des composants dans le code Java
                javaCode.append("        connect(" + source + ", " + sourcePort + ", " + target + ", " + targetPort + ");\n");
            }
        }

        // Fin du fichier Java
        javaCode.append("    }\n\n");
        javaCode.append("    private static void connect(AbstractComponent from, int outIndex, AbstractComponent to, int inIndex) {\n");
        javaCode.append("        Composant source = from.getComposant();\n");
        javaCode.append("        Composant target = to.getComposant();\n");
        javaCode.append("        source.getSortie(outIndex).connecter(target.getEntree(inIndex));\n");
        javaCode.append("        State outputState = source.evaluer();\n");
        javaCode.append("        to.setInputState(inIndex, outputState);\n");
        javaCode.append("        to.updateOutput(target.evaluer());\n");
        javaCode.append("    }\n");
        javaCode.append("}\n");

        // Écriture dans le fichier .java
        FileWriter fileWriter = new FileWriter("GeneratedCircuit.java");
        fileWriter.write(javaCode.toString());
        fileWriter.close();

        System.out.println("Fichier Java généré : GeneratedCircuit.java");
    }
}
