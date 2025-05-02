import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;

public abstract class AbstractComponent2 extends AbstractComponent { 
    // Rest of your code...

    public AbstractComponent2(String componentName, int numInputs, int numOutputs) {
        super(componentName, numInputs, numOutputs); 
        this.componentName = componentName;
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        
        setPreferredSize(new Dimension(80, 100));
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

                setLocation(newX, newY); // Update the component's position
                updatePortPositions(); // Recalculate ports' positions

                repaint(); // Redraw the component
            }
        });
    }

    // Your existing methods for painting and port management
}
