package GRAPHIQUE;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import Logique.Principale.Composant;
import Logique.Principale.State;

public class MyGate1 extends Composant {
    private final Map<String, State> truthTable;

    public MyGate1(String id, int x, int y, String xmlFilePath) {
        super(id, x, y);
        this.addInput(State.UNKNOWN);        // une seule entrée
        this.addOutput(State.UNKNOWN);
        this.truthTable = loadTruthTable(xmlFilePath);
    }

    @Override
    public void evaluate() {
        State a = this.inputs.get(0);
        if (a == State.UNKNOWN) {
            this.outputs.set(0, State.UNKNOWN);
            return;
        }
        String key = (a == State.TRUE ? "1" : "0");
        State out = truthTable.getOrDefault(key, State.UNKNOWN);
        this.outputs.set(0, out);
        this.state = out;
    }

    private static Map<String, State> loadTruthTable(String xmlFilePath) {
        Map<String, State> table = new HashMap<>();
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                               .newDocumentBuilder()
                               .parse(new File(xmlFilePath));
            doc.getDocumentElement().normalize();
            String text = doc.getElementsByTagName("truthTable")
                             .item(0).getTextContent().trim();
            for (String line : text.split("\\r?\\n")) {
                String[] p = line.trim().split("=");
                if (p.length == 2) {
                    String in  = p[0].trim();
                    String out = p[1].trim();
                    State s = out.equals("1") ? State.TRUE
                             : out.equals("0") ? State.FALSE
                             : State.UNKNOWN;
                    table.put(in, s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }
}
