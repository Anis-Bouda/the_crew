package GRAPHIQUE;

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
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import Logique.Principale.State;

public class MyGate_ig3 extends AbstractComponent {
    private static final int W = 70, H = 80;
    private final List<State> inputStates;
    private State outputState = State.UNKNOWN;
    private final Map<String, State> truthTable = new HashMap<>();

    public MyGate_ig3(String xmlFilePath) {
        super("MyGate3", 3, 1);
        loadTruthTable(xmlFilePath);
        inputStates = new ArrayList<>(Collections.nCopies(3, State.UNKNOWN));
        setPreferredSize(new Dimension(W, H));
        updatePortPositions();

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                for (int i = 0; i < 3; i++) {
                    if (getInputPorts().get(i).distance(p) < 10) {
                        State next = inputStates.get(i)==State.TRUE?State.FALSE:State.TRUE;
                        setInputState(i, next);
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        // entrées
        g2.setColor(java.awt.Color.BLUE);
        for (int i=0; i<3; i++) {
            Point in = getInputPorts().get(i);
            if (inputStates.get(i)==State.TRUE) g2.fillOval(in.x-4,in.y-4,8,8);
            else                                g2.drawOval(in.x-4,in.y-4,8,8);
        }
        // sortie
        Point out = getOutputPorts().get(0);
        g2.setColor(java.awt.Color.RED);
        if (outputState==State.TRUE) g2.fillOval(out.x-4,out.y-4,8,8);
        else                          g2.drawOval(out.x-4,out.y-4,8,8);
    }

    public void setInputState(int idx, State s) {
        inputStates.set(idx, s);
        recompute();
        repaint();
    }

    private void loadTruthTable(String xmlFilePath) {
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                               .newDocumentBuilder()
                               .parse(new File(xmlFilePath));
            doc.getDocumentElement().normalize();
            String txt = doc.getElementsByTagName("truthTable")
                            .item(0).getTextContent().trim();
            for (String ln: txt.split("\\r?\\n")) {
                String[] p = ln.trim().split("=");
                if (p.length==2) {
                    State v = p[1].trim().equals("1")?State.TRUE
                             : p[1].trim().equals("0")?State.FALSE
                             : State.UNKNOWN;
                    truthTable.put(p[0].trim(), v);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void recompute() {
        StringBuilder key = new StringBuilder();
        for (State s : inputStates) {
            if (s==State.UNKNOWN) {
                outputState = State.UNKNOWN;
                return;
            }
            key.append(s==State.TRUE?'1':'0');
        }
        outputState = truthTable.getOrDefault(key.toString(), State.UNKNOWN);
    }

    @Override
    public String getComponentName() { return "MyGate3"; }
}
