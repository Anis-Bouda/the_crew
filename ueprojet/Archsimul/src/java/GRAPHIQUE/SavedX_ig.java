package GRAPHIQUE;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Logique.Principale.State;

public class SavedX_ig extends AbstractComponent {

    private final int width = 50;
    private final int height = 60;

    private List<State> inputStates;
    private State outputState;

    /**
     * @param numInputs nombre de générateurs ON/OFF à représenter
     */
    public SavedX_ig(int numInputs) {
        super("saved_x", numInputs, 1);
        this.inputStates = new ArrayList<>(Collections.nCopies(numInputs, State.UNKNOWN));
        this.outputState = State.UNKNOWN;

        setPreferredSize(new Dimension(width, height));
        updatePortPositions();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickPoint = e.getPoint();
                for (int i = 0; i < getInputPorts().size(); i++) {
                    if (getInputPorts().get(i).distance(clickPoint) < 10) {
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

        g2.setColor(Color.BLUE);
        for (int i = 0; i < inputStates.size(); i++) {
            Point port = getInputPorts().get(i);
            if (inputStates.get(i) == State.TRUE)
                g2.fillOval((int) port.getX() - 4, (int) port.getY() - 4, 8, 8);
            else
                g2.drawOval((int) port.getX() - 4, (int) port.getY() - 4, 8, 8);
        }

        g2.setColor(Color.RED);
        Point outPort = getOutputPorts().get(0);
        if (outputState == State.TRUE)
            g2.fillOval((int) outPort.getX() - 4, (int) outPort.getY() - 4, 8, 8);
        else
            g2.drawOval((int) outPort.getX() - 4, (int) outPort.getY() - 4, 8, 8);
    }

    public void setInputState(int index, State s) {
        if (index >= 0 && index < inputStates.size()) {
            inputStates.set(index, s);
        }
    }

    public State getInputState(int index) {
        if (index >= 0 && index < inputStates.size()) {
            return inputStates.get(index);
        }
        return State.UNKNOWN;
    }

    public void updateOutputState(State output) {
        this.outputState = output;
        System.out.println(" Sortie mise à jour dans la vue : " + output);
        repaint();
    }

    @Override
    public String getComponentName() {
        return "saved_x";
    }
} 
