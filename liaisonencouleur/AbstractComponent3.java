import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;

public abstract class AbstractComponent3 extends AbstractComponent { 

    public AbstractComponent3(String componentName, int numInputs, int numOutputs) {
        super(componentName, numInputs, numOutputs); 
        this.componentName = componentName;
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        
        setPreferredSize(new Dimension(260, 260));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
        setLayout(null);
        
        updatePortPositions(); // Initialize the ports

        // Adding mouse listener to drag the component
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                // Save the initial position of the mouse for dragging
                putClientProperty("startX", e.getX());
                putClientProperty("startY", e.getY());
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                super.mouseClicked(e);
                // Your existing logic for port click handling
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

        // Définir l'espacement entre les ports
        int spacing = 10;  // Espacement de 10 pixels entre les ports

        // Positionner les ports d'entrée (input)
        for (int i = 0; i < numInputs; i++) {
            int xPos = 25;  // Position horizontale fixe pour les entrées
            int yPos = i * spacing + 20;  // Position verticale avec espacement
            inputPorts.add(new Port(new java.awt.Point(xPos, yPos)));
        }

        // Positionner les ports de sortie (output)
        for (int i = 0; i < numOutputs; i++) {
            int xPos = 0;  // Position horizontale fixe pour les sorties (à gauche)
            int yPos = height - (numOutputs - i) * spacing;  // Positionner en bas avec espacement
            outputPorts.add(new Port(new java.awt.Point(xPos, yPos)));
        }
    }




}
