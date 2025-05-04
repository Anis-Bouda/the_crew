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

public class MyGate_ig1 extends AbstractComponent {
    private static final int W = 50, H = 60;
    private final List<State> inputStates;
    private State outputState = State.UNKNOWN;
    private final Map<String, State> truthTable = new HashMap<>();

    public MyGate_ig1(String xmlFilePath) {
        super("MyGate1", 1, 1);
        loadTruthTable(xmlFilePath);
        inputStates = new ArrayList<>(Collections.nCopies(1, State.UNKNOWN));
        setPreferredSize(new Dimension(W, H));
        updatePortPositions();

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                if (getInputPorts().get(0).distance(p) < 10) {
                    // toggle TRUE/FALSE for demo
                    State next = inputStates.get(0)==State.TRUE?State.FALSE:State.TRUE;
                    setInputState(0, next);
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
        // entrée
        Point in = getInputPorts().get(0);
        if (inputStates.get(0)==State.TRUE) g2.fillOval(in.x-4,in.y-4,8,8);
        else                                g2.drawOval(in.x-4,in.y-4,8,8);
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
        String key = inputStates.get(0)==State.TRUE?"1":"0";
        outputState = truthTable.getOrDefault(key, State.UNKNOWN);
    }

    @Override
    public String getComponentName() { return "MyGate1"; }
}
