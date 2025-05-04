package GRAPHIQUE ;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

import Logique.Memory.Register;

public class RNG extends AbstractComponent5 {
    private final int width = 170;
    private final int height = 40;

    public RNG() {
        super("RNG", 3, 1);
        setOpaque(false);

         /*la création de la porte logique pour le liens  */
         Register Registerlogique = new Register(TOOL_TIP_TEXT_KEY, UNDEFINED_CONDITION, ALLBITS, ABORT);
         setComposant(Registerlogique);
        
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

        int x = 10, y = 80;
        int w = width-20, h = height;

        // Dessiner un autre rectangle en haut collé au corps de la RAM
        int headerHeight = 80;
        g2.draw(new RoundRectangle2D.Double(x+20, y - headerHeight, w-40, headerHeight, 10, 10));
        g2.drawString("RNG8", x + 50, y - headerHeight + 15);

        // Signaux de contrôle à l'intérieur du rectangle
        g2.drawString("R", x + 30, y - headerHeight + 25);
        g2.drawString("EN", x + 30, y - headerHeight + 35);
        g2.drawString("73", x + 60, y - headerHeight + 105);
   
        
        // Dessiner le corps de la RAM (rectangle arrondi)
        g2.draw(new RoundRectangle2D.Double(x, y, w, h, 10, 10));


        

        // Dessiner un triangle orienté vers C1 (déplacer vers la gauche)
        int[] xPoints = {x+20 , x+20 , x + 30}; // Position des points horizontaux du triangle
        int[] yPoints = {y - headerHeight + 35, y - headerHeight + 45, y - headerHeight + 40}; // Déplacer vers le haut
        
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        g2.fillPolygon(triangle); // Remplir le triangle avec la couleur noire
        
        // Ajouter trois lignes droites sortantes en haut à gauche 
        int offset = 10; // Espacement entre les lignes
        g2.drawLine(x+20, y - headerHeight+20, x , y - headerHeight+20); // Première ligne droite
        g2.drawLine(x+20, y - headerHeight + 30, x , y - headerHeight + 30); // Deuxième ligne droite
        g2.drawLine(x+20, y - headerHeight + 40, x , y - headerHeight + 40); // Troisième ligne droite
        

    }
}
