import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import javax.swing.*;

public class CMPT_ig extends AbstractComponent4 {
    private final int width = 200;
    private final int height = 200;

    public CMPT_ig() {
        super("CMPT", 4, 2);
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY());

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port d'entrée RAM sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port de sortie RAM sélectionné !");
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
        
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        int x = 50, y = 80;
        int w = width, h = height;

        // Dessiner un autre rectangle en haut collé au corps de la RAM
        int headerHeight = 100;
        g2.draw(new RoundRectangle2D.Double(x, y - headerHeight+30, w, headerHeight, 10, 10));
        g2.drawString("CTR8", x + 80, y - headerHeight + 50);

        // Signaux de contrôle à l'intérieur du rectangle
        g2.drawString("R", x + 10, y - headerHeight + 55);
        g2.drawString("M1 [load]", x + 10, y - headerHeight + 65);
        g2.drawString("M2 [count]", x + 10, y - headerHeight + 75);
        g2.drawString("M3 [up]", x + 10, y - headerHeight + 85);
        g2.drawString("M4 [down]", x + 10, y - headerHeight + 95);
        g2.drawString("G5", x + 10, y - headerHeight + 105);
        g2.drawString("2,3,5+/C6", x + 10, y - headerHeight + 115);
        g2.drawString("2,4,5-", x + 10, y - headerHeight + 125);
        
        g2.drawString("00", x + 120, y - headerHeight + 65);
        g2.drawString("3CT=0xFF", x + 135, y - headerHeight + 85);
        g2.drawString("4CT=0", x + 155, y - headerHeight + 95);
        g2.drawLine(x+200, y - headerHeight + 80, x + 220, y - headerHeight + 80); 
        g2.drawLine(x+200, y - headerHeight + 90, x + 210, y - headerHeight + 90);
        g2.drawLine(x +210, y - headerHeight + 90, x + 210, y - headerHeight + 80);
        
     
        // Dessiner le corps de la RAM (rectangle arrondi)
        g2.draw(new RoundRectangle2D.Double(x, y+30, w, h, 20, 20));

        // Dessiner les entrées (adresse et données) à l'intérieur avec les lettres entre les lignes
        for (int i = 0; i < 16; i+=2) {
            int yOffset = y + 25 + i * 10+30;
            int yOffset2 = y + 5 + i * 10+30;
            g2.drawLine(x + 5, yOffset, x + 20, yOffset);
            g2.drawString("1,6D" , x + 25, yOffset + 5);
            g2.drawLine(x, yOffset2 + 20, x - 20, yOffset2 + 5);
        }

        // Dessiner les sorties (données) à l'intérieur avec les lettres entre les lignes
        for (int i = 0; i < 16; i+=2) {
            int yOffset = y + 25 + i * 10+30;
            int yOffset2 = y + 5 + i * 10+30;
            g2.drawLine(x + w - 20, yOffset, x + w - 5, yOffset);
       
            g2.drawLine(x + w, yOffset2 + 20, x + w + 20, yOffset2 + 5);
        }

        // Dessiner un triangle orienté vers C1 (déplacer vers la gauche)
        int[] xPoints = {x , x , x + 10}; // Position des points horizontaux du triangle
        int[] yPoints = {y - headerHeight + 105, y - headerHeight + 115, y - headerHeight + 110}; // Déplacer vers le haut
        
        int[] xPoints2 = {x , x , x + 10}; // Position des points horizontaux du triangle
        int[] yPoints2 = {y - headerHeight + 115, y - headerHeight + 125, y - headerHeight + 120}; 
        
        
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        Polygon triangle2 = new Polygon(xPoints2, yPoints2, 3);
        g2.fillPolygon(triangle);
        g2.fillPolygon(triangle2);// Remplir le triangle avec la couleur noire
        
        // Ajouter 5 lignes droites sortantes en haut à gauche 
        int offset = 10; // Espacement entre les lignes
        g2.drawLine(x, y - headerHeight+50, x - 20, y - headerHeight+50); // Première ligne droite
        g2.drawLine(x, y - headerHeight + 60, x - 20, y - headerHeight + 60); // Deuxième ligne droite
        g2.drawLine(x, y - headerHeight + 70, x - 10, y - headerHeight + 70);
        g2.drawLine(x - 10, y - headerHeight + 70, x - 10, y - headerHeight + 60);

        
        g2.drawLine(x, y - headerHeight + 80, x - 20, y - headerHeight + 80); 
        g2.drawLine(x, y - headerHeight + 90, x - 10, y - headerHeight + 90);
        g2.drawLine(x - 10, y - headerHeight + 90, x - 10, y - headerHeight + 80);
        
        
        g2.drawLine(x, y - headerHeight + 100, x - 20, y - headerHeight + 100);
        
        g2.drawLine(x, y - headerHeight + 110, x - 20, y - headerHeight + 110);
        g2.drawLine(x, y - headerHeight + 120, x - 10, y - headerHeight + 120);
        g2.drawLine(x - 10, y - headerHeight + 110, x - 10, y - headerHeight + 120);
        // Dessiner deux lignes verticales longues à l'extérieur du carré en bas avec une petite déviation en haut
        int lineHeight = h - 285;
        
        // Relier les lignes verticales aux autres lignes internes par des lignes inclinées
        g2.drawLine(x - 20, y + h - 160, x - 20, y + h + lineHeight + 65);
        g2.drawLine(x + w + 20, y + h - 160, x + w + 20, y + h + lineHeight + 65);
    }
    
   // initialisation par defaut 
    private java.util.List<State> inputStates = java.util.Arrays.asList(State.UNKNOWN, State.UNKNOWN, State.UNKNOWN, State.UNKNOWN);
    private java.util.List<State> outputStates = java.util.Arrays.asList(State.UNKNOWN, State.UNKNOWN);
    
   // Méthode pour définir l'état d'une entrée
    public void setInputState(int index, State s) {
        inputStates.set(index, s);
    }

    // Méthode pour récupérer l'état d'une entrée 
    public State getSelectedInput(int index) {
        return inputStates.get(index);
    }
  
    // Mise à jour de la sortie graphique
    public void updateOutputs(List<State> outputs) {
        this.outputStates = outputs;
        repaint();
        // Affiche les états dans la console
        for (int i = 0; i < outputs.size(); i++) {
            System.out.println("🖥️ Sortie " + i + " mise à jour dans la vue : " + outputs.get(i));
        }}
}
