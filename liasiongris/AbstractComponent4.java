import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;

public abstract class AbstractComponent4 extends AbstractComponent { 


    public AbstractComponent4(String componentName, int numInputs, int numOutputs) {
        super(componentName, numInputs, numOutputs); 
        this.componentName = componentName;
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        
        setPreferredSize(new Dimension(350, 350));
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

        
        int spacing = 10; 

        for (int i = 0; i < 2; i++) {
            int xPos = 25; 
            int yPos = i * spacing+30;  
            inputPorts.add(new Port(new java.awt.Point(xPos, yPos)));
        }
        int xPos2 = 25; 
        int yPos2 = 2 * spacing+40;  
        inputPorts.add(new Port(new java.awt.Point(xPos2, yPos2)));
        
        for (int i = 3; i < numInputs; i++) {
            int xPos3 = 25; 
            int yPos3 = i * spacing+50;  
            inputPorts.add(new Port(new java.awt.Point(xPos3, yPos3)));
        }

        
        for (int i = 0; i < 2; i++) {
            int xPos = 270; 
            int yPos = 60;  
            outputPorts.add(new Port(new java.awt.Point(xPos, yPos)));
        }
    }



}
