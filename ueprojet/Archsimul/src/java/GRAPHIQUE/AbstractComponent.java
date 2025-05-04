package GRAPHIQUE;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import Logique.Principale.*;

public abstract class AbstractComponent extends JPanel {
    protected List<Port> inputPorts = new ArrayList<>();
    protected List<Port> outputPorts = new ArrayList<>();

    protected String componentName;
    protected int numInputs;
    protected int numOutputs;
    protected Composant composant;
    private int rotationAngle = 0; // en degrés, 0, 90, 180, 270

    public AbstractComponent(String componentName, int numInputs, int numOutputs) {
        this.componentName = componentName;
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;

        setPreferredSize(new Dimension(80, 60));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
        setLayout(null);

        updatePortPositions();

        // Gestion du clic et du changement de curseur
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final int CLICK_RADIUS = 4;
                // Alt + clic gauche tourne dans le sens horaire
                if (SwingUtilities.isLeftMouseButton(e) && e.isAltDown()) {
                    rotateComponent();
                    return;
                }

                // Clic droit pour supprimer
                if (SwingUtilities.isRightMouseButton(e)) {
                    setVisible(false);
                    removeComponent();
                } else {
                    // Clic sur ports
                    for (int i = 0; i < inputPorts.size(); i++) {
                        Port port = inputPorts.get(i);
                        Point pos = port.getPosition();
                        if (pos.distance(e.getPoint()) < CLICK_RADIUS) {
                            Point globalPort = new Point(getX() + pos.x, getY() + pos.y);
                            ConnectionManager.portClicked(AbstractComponent.this, "input", i, globalPort);
                            return;
                        }
                    }
                    for (int i = 0; i < outputPorts.size(); i++) {
                        Port port = outputPorts.get(i);
                        Point pos = port.getPosition();
                        if (pos.distance(e.getPoint()) < CLICK_RADIUS) {
                            Point globalPort = new Point(getX() + pos.x, getY() + pos.y);
                            ConnectionManager.portClicked(AbstractComponent.this, "output", i, globalPort);
                            return;
                        }
                    }
                }
            }
        });

        // Curseur au survol des ports
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                boolean overPort = false;
                for (Port port : inputPorts) {
                    if (port.getPosition().distance(e.getPoint()) < 4) {
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        overPort = true;
                        break;
                    }
                }
                if (!overPort) {
                    for (Port port : outputPorts) {
                        if (port.getPosition().distance(e.getPoint()) < 4) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            overPort = true;
                            break;
                        }
                    }
                }
                if (!overPort) {
                    setCursor(Cursor.getDefaultCursor());
                }
            }
        });
    }

    public String getComponentName() {
        return componentName;
    }

    /**
     * Tourne le composant de 90° dans le sens horaire
     */
    /**
     * Tourne le composant de 90° dans le sens anti-horaire
     */
    protected void rotateComponent() {
        // On soustrait 90° (équivalent à +270° modulo 360)
        rotationAngle = (rotationAngle + 270) % 360;

        // On échange largeur/hauteur si nécessaire
        Dimension pref = getPreferredSize();
        if (rotationAngle == 90 || rotationAngle == 270) {
            setPreferredSize(new Dimension(pref.height, pref.width));
        } else {
            setPreferredSize(new Dimension(pref.width, pref.height));
        }

        // Assurer la mise à jour des ports
        updatePortPositions();

        revalidate();
        repaint();
        ConnectionManager.updateConnectionsForComponent(this);

    }


    public int getRotationAngle() {
        return rotationAngle;
    }


    protected void updatePortPositions() {
        inputPorts.clear();
        outputPorts.clear();

        int w = getWidth();
        int h = getHeight();

        // on garde l’angle tel quel pour plus de clarté
        int angle = rotationAngle % 360;

        switch (angle) {
            case 0:
                // entrées à gauche, sorties à droite
                for (int i = 0; i < numInputs; i++)
                    inputPorts.add(new Port(new Point(0, (i + 1) * h / (numInputs + 1))));
                for (int i = 0; i < numOutputs; i++)
                    outputPorts.add(new Port(new Point(w, (i + 1) * h / (numOutputs + 1))));
                break;

            case 90:
                // composant tourné de 90° (sens horaire) :
                // inputs en haut (y=0), outputs en bas (y=h)
                for (int i = 0; i < numInputs; i++)
                    inputPorts.add(new Port(new Point((i + 1) * w / (numInputs + 1), 0)));
                for (int i = 0; i < numOutputs; i++)
                    outputPorts.add(new Port(new Point((i + 1) * w / (numOutputs + 1), h)));
                break;

            case 180:
                // entrées à droite, sorties à gauche
                for (int i = 0; i < numInputs; i++)
                    inputPorts.add(new Port(new Point(w, (i + 1) * h / (numInputs + 1))));
                for (int i = 0; i < numOutputs; i++)
                    outputPorts.add(new Port(new Point(0, (i + 1) * h / (numOutputs + 1))));
                break;

            case 270:
                // composant tourné de 270° (ou -90°) :
                // inputs en bas (y=h), outputs en haut (y=0)
                for (int i = 0; i < numInputs; i++)
                    inputPorts.add(new Port(new Point((i + 1) * w / (numInputs + 1), h)));
                for (int i = 0; i < numOutputs; i++)
                    outputPorts.add(new Port(new Point((i + 1) * w / (numOutputs + 1), 0)));
                break;
        }
    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updatePortPositions();
        g.setColor(new Color(0, 0, 0, 0));
        g.drawRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.RED);
        for (Port p : inputPorts) {
            Point pos = p.getPosition();
            g.fillOval(pos.x - 3, pos.y - 3, 6, 6);
        }

        g.setColor(Color.GREEN);
        for (Port p : outputPorts) {
            Point pos = p.getPosition();
            g.fillOval(pos.x - 3, pos.y - 3, 6, 6);
        }
    }

    public List<Point> getInputPorts() {
        List<Point> globalPositions = new ArrayList<>();
        for (Port p : inputPorts) {
            Point pos = p.getPosition();
            globalPositions.add(new Point(getX() + pos.x, getY() + pos.y));
        }
        return globalPositions;
    }

    public List<Point> getOutputPorts() {
        List<Point> globalPositions = new ArrayList<>();
        for (Port p : outputPorts) {
            Point pos = p.getPosition();
            globalPositions.add(new Point(getX() + pos.x, getY() + pos.y));
        }
        return globalPositions;
    }

    private void removeComponent() {
        if (getParent() != null) {
            getParent().remove(this);
            getParent().revalidate();
            getParent().repaint();
            System.out.println("Composant supprimé !");
        }
    }

    public Point getGlobalPortLocation(String portType, int portIndex) {
        Point localPortPosition;
        if ("input".equals(portType)) {
            localPortPosition = inputPorts.get(portIndex).getPosition();
        } else if ("output".equals(portType)) {
            localPortPosition = outputPorts.get(portIndex).getPosition();
        } else {
            return null;
        }
        return SwingUtilities.convertPoint(this, localPortPosition, getParent());
    }

    public void setComposant(Composant composant) {
        this.composant = composant;
    }

    public Composant getComposant() {
        return composant;
    }

    public List<Logique.Principale.State> getInputStates() {
        List<Logique.Principale.State> states = new ArrayList<>();
        for (int i = 0; i < inputPorts.size(); i++) {
            Logique.Principale.State inputState = Logique.Principale.State.UNKNOWN;
            for (Connection conn : ConnectionManager.connections) {
                if (conn.compTarget == this && conn.portTypeTarget.equals("input") && conn.portIndexTarget == i) {
                    if (conn.filLogic != null) {
                        inputState = conn.filLogic.getValue();
                    }
                    break;
                }
            }
            states.add(inputState);
        }
        return states;
    }

    public void setInputsStates(List<Logique.Principale.State> states) {
        Composant logic = getComposant();
        if (logic != null) {
            for (int i = 0; i < states.size(); i++) {
                logic.setInputs(i, states.get(i));
            }
        }
    }

    public void updateOutputState() {
        Composant logic = getComposant();
        if (logic != null) {
            List<Logique.Principale.State> outputs = logic.getOutputs();
            for (int i = 0; i < outputs.size(); i++) {
                outputPorts.get(i).setState(StateMapper.toGuiState(outputs.get(i)));
            }
        }
    }

    public AbstractComponent cloneComponent() {
        try {
            AbstractComponent clone = this.getClass().getDeclaredConstructor().newInstance();
            clone.componentName = this.componentName /*+ "_copy"+System.currentTimeMillis()*/;
            clone.numInputs = this.numInputs;
            clone.numOutputs = this.numOutputs;
            clone.setLocation(this.getX() + 20, this.getY() + 20);
            clone.updatePortPositions();
            return clone;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}