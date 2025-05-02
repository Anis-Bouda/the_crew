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

public abstract class AbstractComponent extends JPanel {
    protected List<Port> inputPorts = new ArrayList<>();
    protected List<Port> outputPorts = new ArrayList<>();

    protected String componentName;
    protected int numInputs;
    protected int numOutputs;

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
                final int CLICK_RADIUS = 4; // Zone de détection encore plus précise

                // Clic droit pour supprimer
                if (SwingUtilities.isRightMouseButton(e)) {
                    // Supprimer le composant si un clic droit est effectué
                    setVisible(false);
                    removeComponent();
                } else {
                    // Si clic gauche, gérer les ports
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

        // Ecouteur de mouvement de souris pour changer le curseur
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                boolean overPort = false;

                // Vérifier si la souris est sur un port d'entrée
                for (Port port : inputPorts) {
                    if (port.getPosition().distance(e.getPoint()) < 4) {
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        overPort = true;
                        break;
                    }
                }

                // Vérifier si la souris est sur un port de sortie
                if (!overPort) {
                    for (Port port : outputPorts) {
                        if (port.getPosition().distance(e.getPoint()) < 4) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            overPort = true;
                            break;
                        }
                    }
                }

                // Si la souris n'est sur aucun port, réinitialiser le curseur
                if (!overPort) {
                    setCursor(Cursor.getDefaultCursor());
                }
            }
        });
    }

    public String getComponentName() {
        return componentName;
    }

    protected void updatePortPositions() {
        int width = getPreferredSize().width;
        int height = getPreferredSize().height;

        inputPorts.clear();
        outputPorts.clear();

        for (int i = 0; i < numInputs; i++) {
            int yPos = (i + 1) * height / (numInputs + 1);
            inputPorts.add(new Port(new Point(0, yPos)));
        }

        for (int i = 0; i < numOutputs; i++)
        {
            int yPos = (i + 1) * height / (numOutputs + 1);
            outputPorts.add(new Port(new Point(width, yPos)));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updatePortPositions();

        g.setColor(new Color(0, 0, 0, 0));
        g.drawRect(0, 0, getWidth(), getHeight());

        // Dessiner les ports d'entrée en rouge
        g.setColor(Color.RED);
        for (Port p : inputPorts) {
            Point pos = p.getPosition();
            g.fillOval(pos.x - 3, pos.y - 3, 6, 6);
        }

        // Dessiner les ports de sortie en vert
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

    // Méthode pour supprimer le composant
    private void removeComponent() {
        if (getParent() != null) {
            getParent().remove(this);
            getParent().revalidate();
            getParent().repaint();
            System.out.println("Composant supprimé !");
        }
    }

    public Point getGlobalPortLocation(String portType, int portIndex) {
        int x = getX();
        int y = getY();
        int portX, portY;
        if ("input".equals(portType)) {
            portX = x;
            portY = y + getHeight() / 2;
        } else if ("output".equals(portType)) {
            portX = x + getWidth();
            portY = y + getHeight() / 2;
        } else {
            portX = x;
            portY = y;
        }
        return new Point(portX, portY);
    }
}
