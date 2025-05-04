package GRAPHIQUE;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import Logique.Principale.*;
import Logique.Principale.Composant;

public class MyGate extends Composant {
    private final Map<String, State> truthTable;

    public MyGate(String id, int x, int y, String xmlFilePath) {
        super(id, x, y);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);

        this.truthTable = loadTruthTable(xmlFilePath);
    }

    @Override
    public void evaluate() {
        State a = this.inputs.get(0);
        State b = this.inputs.get(1);

        if (a == State.UNKNOWN || b == State.UNKNOWN) {
            this.outputs.set(0, State.UNKNOWN);
            return;
        }

        String key = (a == State.TRUE ? "1" : "0")
                   + (b == State.TRUE ? "1" : "0");

        State output = truthTable.getOrDefault(key, State.UNKNOWN);
        this.outputs.set(0, output);
        this.state = output;
    }

    private static Map<String, State> loadTruthTable(String xmlFilePath) {
        Map<String, State> truthTable = new HashMap<>();
        try {
            File file = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder        = dbFactory.newDocumentBuilder();
            Document doc                    = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            String tableText = doc.getElementsByTagName("truthTable")
                                  .item(0)
                                  .getTextContent()
                                  .trim();

            // Split sur \r\n ou \n
            for (String line : tableText.split("\\r?\\n")) {
                String[] parts = line.trim().split("=");
                if (parts.length == 2) {
                    String inputCombo = parts[0].trim();
                    String outputChar = parts[1].trim();
                    State outputState;
                    switch (outputChar) {
                        case "0": outputState = State.FALSE;   break;
                        case "1": outputState = State.TRUE;    break;
                        default:  outputState = State.UNKNOWN; break;
                    }
                    truthTable.put(inputCombo, outputState);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return truthTable;
    }
}
