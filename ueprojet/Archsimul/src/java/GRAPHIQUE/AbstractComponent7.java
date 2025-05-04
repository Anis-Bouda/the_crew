package GRAPHIQUE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.BorderFactory;

public abstract class AbstractComponent7 extends AbstractComponent {

    public AbstractComponent7(String componentName, int numInputs, int numOutputs) {
        super(componentName, numInputs, numOutputs);
        this.componentName = componentName;
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;

        setPreferredSize(new Dimension(170, 150));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
        setLayout(null);

        updatePortPositions();

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                putClientProperty("startX", e.getX());
                putClientProperty("startY", e.getY());
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                super.mouseClicked(e);
                // Logique de clic sur ports
            }
        });

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                int startX = (int) getClientProperty("startX");
                int startY = (int) getClientProperty("startY");

                int newX = getX() + e.getX() - startX;
                int newY = getY() + e.getY() - startY;

                setLocation(newX, newY);
                updatePortPositions();
                repaint();
            }
        });
    }

    @Override
    protected void updatePortPositions() {
        int width = getPreferredSize().width;
        int height = getPreferredSize().height;

        inputPorts.clear();
        outputPorts.clear();

        // Positionnement des 4 entrées à gauche
        for (int i = 0; i < numInputs; i++) {
            int x = 0;
            int y = 20 + i * 25;
            inputPorts.add(new Port(new Point(x, y)));
        }

        // Positionnement des 7 sorties à droite (7 segments A à G)
        for (int i = 0; i < numOutputs; i++) {
            int x = width - 10;
            int y = 10 + i * 18;
            outputPorts.add(new Port(new Point(x, y)));
        }
    }
}

