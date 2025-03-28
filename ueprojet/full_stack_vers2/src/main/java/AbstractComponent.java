import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComponent extends JPanel {
    protected List<java.awt.Point> inputPorts = new ArrayList<>();
    protected List<java.awt.Point> outputPorts = new ArrayList<>();

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
                    // Si clic gauche, gérer les ports comme avant
                    for (int i = 0; i < inputPorts.size(); i++) {
                        java.awt.Point port = inputPorts.get(i);
                        if (port.distance(e.getPoint()) < CLICK_RADIUS) {
                            java.awt.Point globalPort = new java.awt.Point(getX() + port.x, getY() + port.y);
                            ConnectionManager.portClicked(AbstractComponent.this, "input", i, globalPort);
                            return;
                        }
                    }

                    for (int i = 0; i < outputPorts.size(); i++) {
                        java.awt.Point port = outputPorts.get(i);
                        if (port.distance(e.getPoint()) < CLICK_RADIUS) {
                            java.awt.Point globalPort = new java.awt.Point(getX() + port.x, getY() + port.y);
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
                for (java.awt.Point port : inputPorts) {
                    if (port.distance(e.getPoint()) < 4) {
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Curseur pointeur
                        overPort = true;
                        break;
                    }
                }

                // Vérifier si la souris est sur un port de sortie
                if (!overPort) {
                    for (java.awt.Point port : outputPorts) {
                        if (port.distance(e.getPoint()) < 4) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Curseur pointeur
                            overPort = true;
                            break;
                        }
                    }
                }

                // Si la souris n'est sur aucun port, réinitialiser le curseur à son état normal
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
            inputPorts.add(new java.awt.Point(0, yPos));
        }

        for (int i = 0; i < numOutputs; i++) {
            int yPos = (i + 1) * height / (numOutputs + 1);
            outputPorts.add(new java.awt.Point(width, yPos));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updatePortPositions();

        g.setColor(new Color(0, 0, 0, 0));
        g.drawRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.RED);
        for (java.awt.Point p : inputPorts) {
            g.fillOval(p.x - 3, p.y - 3, 6, 6); // Réduction de la taille des ports
        }

        g.setColor(Color.GREEN);
        for (java.awt.Point p : outputPorts) {
            g.fillOval(p.x - 3, p.y - 3, 6, 6);
        }
    }

    public List<java.awt.Point> getInputPorts() {
        List<java.awt.Point> globalPositions = new ArrayList<>();
        for (java.awt.Point p : inputPorts) {
            globalPositions.add(new java.awt.Point(getX() + p.x, getY() + p.y));
        }
        return globalPositions;
    }

    public List<java.awt.Point> getOutputPorts() {
        List<java.awt.Point> globalPositions = new ArrayList<>();
        for (java.awt.Point p : outputPorts) {
            globalPositions.add(new java.awt.Point(getX() + p.x, getY() + p.y));
        }
        return globalPositions;
    }

    // Méthode pour supprimer le composant
    private void removeComponent() {
        // Retirer le composant de son conteneur parent
        if (getParent() != null) {
            // Supprimer immédiatement le composant
            getParent().remove(this);
            getParent().revalidate();  // Recalcule la mise en page
            getParent().repaint();  // Redessine immédiatement le parent
            System.out.println("Composant supprimé !");
        }
    }
    public java.awt.Point getGlobalPortLocation(String portType, int portIndex) {
    	int x = getX();
        int y = getY();
        int portX, portY;
        if ("input".equals(portType)) {
            portX = x; // côté gauche
            portY = y + getHeight() / 2;
        } else if ("output".equals(portType)) {
            portX = x + getWidth(); // côté droit
            portY = y + getHeight() / 2;
        } else {
            portX = x;
            portY = y;
        }
        return new java.awt.Point(portX, portY);
    }
    
}
