package GRAPHIQUE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import Logique.Principale.State;



/**
 * Porte logique graphique dont la vérité est chargée depuis un XML.
 */
public class MyGate_ig extends AbstractComponent {
    private static final int WIDTH  = 50;
    private static final int HEIGHT = 60;

    private final List<State> inputStates;
    private State outputState;

    // Table de vérité : clé binaire ("01"), valeur TRUE/FALSE
    private final Map<String, State> truthTable = new HashMap<>();

    /**
     * @param numInputs   nombre d'entrées
     * @param xmlFilePath chemin vers le XML contenant la table de vérité
     */
    public MyGate_ig(int numInputs, String xmlFilePath) {
        super("MyGate", numInputs, 1);

        // Charge la table de vérité depuis le XML
        loadTruthTable(xmlFilePath);

        this.inputStates = new ArrayList<>(Collections.nCopies(numInputs, State.UNKNOWN));
        this.outputState  = State.UNKNOWN;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        updatePortPositions();

        // Permet de cliquer graphiquement sur les ports d'entrée
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                for (int i = 0; i < getInputPorts().size(); i++) {
                    if (getInputPorts().get(i).distance(p) < 10) {
                        System.out.println("Port d'entrée " + i + " sélectionné !");
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dessine les cercles d'entrée
        g2.setColor(Color.BLUE);
        for (int i = 0; i < inputStates.size(); i++) {
            Point port = getInputPorts().get(i);
            if (inputStates.get(i) == State.TRUE) {
                g2.fillOval(port.x - 4, port.y - 4, 8, 8);
            } else {
                g2.drawOval(port.x - 4, port.y - 4, 8, 8);
            }
        }

        // Dessine le cercle de sortie
        g2.setColor(Color.RED);
        Point out = getOutputPorts().get(0);
        if (outputState == State.TRUE) {
            g2.fillOval(out.x - 4, out.y - 4, 8, 8);
        } else {
            g2.drawOval(out.x - 4, out.y - 4, 8, 8);
        }
    }

    /**
     * Met à jour l'état d'une entrée graphique et recalcule la sortie.
     */
    public void setInputState(int index, State s) {
        if (index < 0 || index >= inputStates.size()) return;
        inputStates.set(index, s);
        recomputeOutput();
        repaint();
    }

    //  MÉTHODES PRIVÉES POUR LA LOGIQUE XML 

    /**
     * Charge la table de vérité depuis le fichier XML.
     */
    private void loadTruthTable(String xmlFilePath) {
        try {
            File file = new File(xmlFilePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db        = dbf.newDocumentBuilder();
            Document doc             = db.parse(file);
            doc.getDocumentElement().normalize();

            String tableText = doc.getElementsByTagName("truthTable")
                                  .item(0)
                                  .getTextContent()
                                  .trim();

            for (String line : tableText.split("\\r?\\n")) {
                String[] parts = line.trim().split("=");
                if (parts.length == 2) {
                    String combo = parts[0].trim();
                    State  val   = parts[1].trim().equals("1") ? State.TRUE
                                  : parts[1].trim().equals("0") ? State.FALSE
                                  : State.UNKNOWN;
                    truthTable.put(combo, val);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Recalcule la sortie selon la table de vérité.
     */
    private void recomputeOutput() {
        StringBuilder key = new StringBuilder();
        for (State st : inputStates) {
            if (st == State.UNKNOWN) {
                outputState = State.UNKNOWN;
                return;
            }
            key.append(st == State.TRUE ? '1' : '0');
        }
        outputState = truthTable.getOrDefault(key.toString(), State.UNKNOWN);
    }

    @Override
    public String getComponentName() {
        return "MyGate";
    }
}
