import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

import Memory.RAM;

public class RAM_ig extends AbstractComponent3 {
    private final int width = 170;
    private final int height = 150;

    public RAM_ig() {
        super("RAM", 7, 3);
        setOpaque(false);

         /*la création de la porte logique pour le liens  */
         RAM RAMlogique = new RAM(TOOL_TIP_TEXT_KEY, FRAMEBITS, ERROR, ALLBITS, ABORT);
         setComposant(RAMlogique);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY());

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("Port d'entrée RAM sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println(" Port de sortie RAM sélectionné !");
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

        int x = 50, y = 50;
        int w = width, h = height;

        // Dessiner un autre rectangle en haut collé au corps de la RAM
        int headerHeight = 50;
        g2.draw(new RoundRectangle2D.Double(x, y - headerHeight, w, headerHeight, 10, 10));
        g2.drawString("RAM  8 x 3", x + 30, y - headerHeight + 15);

        // Signaux de contrôle à l'intérieur du rectangle
        g2.drawString("M3 [Write enable]", x + 10, y - headerHeight + 25);
        g2.drawString("M2 [Output enable]", x + 10, y - headerHeight + 35);
        g2.drawString("C1", x + 10, y - headerHeight + 45);
        
        // Dessiner le corps de la RAM (rectangle arrondi)
        g2.draw(new RoundRectangle2D.Double(x, y, w, h, 20, 20));

        // Dessiner les entrées (adresse et données) à l'intérieur avec les lettres entre les lignes
        for (int i = 0; i < 7; i++) {
            int yOffset = y + 25 + i * 15;
            int yOffset2 = y + 5 + i * 15;
            g2.drawLine(x + 5, yOffset, x + 20, yOffset);
            g2.drawString("A,1,3" + i, x + 25, yOffset + 5);
            g2.drawLine(x, yOffset2 + 20, x - 20, yOffset2 + 5);
        }

        // Dessiner les sorties (données) à l'intérieur avec les lettres entre les lignes
        for (int i = 0; i < 3; i++) {
            int yOffset = y + 25 + i * 15;
            int yOffset2 = y + 5 + i * 15;
            g2.drawLine(x + w - 20, yOffset, x + w - 5, yOffset);
            g2.drawString("A,1,2" + i, x + w - 65, yOffset + 5);
            g2.drawLine(x + w, yOffset2 + 20, x + w + 20, yOffset2 + 5);
        }

        // Dessiner un triangle orienté vers C1 (déplacer vers la gauche)
        int[] xPoints = {x , x , x + 10}; // Position des points horizontaux du triangle
        int[] yPoints = {y - headerHeight + 35, y - headerHeight + 45, y - headerHeight + 40}; // Déplacer vers le haut
        
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        g2.fillPolygon(triangle); // Remplir le triangle avec la couleur noire
        
        // Ajouter trois lignes droites sortantes en haut à gauche 
        int offset = 10; // Espacement entre les lignes
        g2.drawLine(x, y - headerHeight+20, x - 20, y - headerHeight+20); // Première ligne droite
        g2.drawLine(x, y - headerHeight + 30, x - 20, y - headerHeight + 30); // Deuxième ligne droite
        g2.drawLine(x, y - headerHeight + 40, x - 20, y - headerHeight + 40); // Troisième ligne droite
        
        // Dessiner deux lignes verticales longues à l'extérieur du carré en bas avec une petite déviation en haut
        int lineHeight = h - 285;
        
        // Relier les lignes verticales aux autres lignes internes par des lignes inclinées
        g2.drawLine(x - 20, y + h - 140, x - 20, y + h + lineHeight + 100);
        g2.drawLine(x + w + 20, y + h - 140, x + w + 20, y + h + lineHeight + 100);
    }
    
   // initialisation par defaut 
    private java.util.List<State> inputStates = new java.util.ArrayList<>(java.util.Collections.nCopies(7, State.UNKNOWN));
    private java.util.List<State> outputStates = new java.util.ArrayList<>(java.util.Collections.nCopies(3, State.UNKNOWN));
    
    // mettre a jour l'etat 
    public void setInputState(int index, State s) {
        inputStates.set(index, s);
    }

    // recuperation de l'etat 
    public State getSelectedInput(int index) {
        return inputStates.get(index);
    }

    public void updateOutput(int index, State output) {
        outputStates.set(index, output);
        System.out.println("🖥️ Sortie dans la vue : " + output);
        repaint();
    }

}
