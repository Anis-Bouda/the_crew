package GRAPHIQUE ;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;

public abstract class AbstractComponent5 extends AbstractComponent { 

    public AbstractComponent5(String componentName, int numInputs, int numOutputs) {
        super(componentName, numInputs, numOutputs); 
        this.componentName = componentName;
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        
        setPreferredSize(new Dimension(170, 150));
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
            }
        });

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                int startX = (int) getClientProperty("startX");
                int startY = (int) getClientProperty("startY");

                int newX = getX() + e.getX() - startX;
                int newY = getY() + e.getY() - startY;

                setLocation(newX, newY); // Update the component's position
                updatePortPositions(); // Recalculate ports' positions

                repaint(); // Redraw the component
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
            int xPos3 = 10;  // Tous les ports d'entrée sont à gauche (x = 0)
            int yPos3 = i * spacing+20;  // Positionner les ports avec un espacement vertical
            inputPorts.add(new Port(new java.awt.Point(xPos3, yPos3)));
        }

        // Positionner les ports de sortie (output)
        for (int i = 0; i < numOutputs; i++) {
            int xPos = 160;  // Tous les ports de sortie sont à gauche (x = 0)
            int yPos = 100;  // Positionner les ports de sortie en bas à gauche
            outputPorts.add(new Port(new java.awt.Point(xPos, yPos)));
        }
    }
}
