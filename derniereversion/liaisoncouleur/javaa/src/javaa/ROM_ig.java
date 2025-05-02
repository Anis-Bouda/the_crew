import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class ROM_ig extends AbstractComponent3 {
    private final int width = 170;
    private final int height = 150;

    public ROM_ig() {
        super("ROM", 0, 0);
        setOpaque(false);

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
                        System.out.println("Port de sortie RAM sélectionné !");
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
        g2.drawString("ROM 256 x 8", x + 30, y - headerHeight + 15);


        
        // Dessiner le corps de la RAM (rectangle arrondi)
        g2.draw(new RoundRectangle2D.Double(x, y, w, h, 20, 20));


        // Dessiner les sorties (données) à l'intérieur avec les lettres entre les lignes
        for (int i = 0; i < 8; i++) {
            int yOffset = y + 25 + i * 15;
            int yOffset2 = y + 5 + i * 15;
            g2.drawLine(x + w - 20, yOffset, x + w - 5, yOffset);
            g2.drawString("A" + i, x + w - 40, yOffset + 5);
            g2.drawLine(x + w, yOffset2 + 20, x + w + 20, yOffset2 + 5);
        }

        // Dessiner un triangle orienté vers C1 (déplacer vers la gauche)
        int[] xPoints = {x , x , x + 10}; // Position des points horizontaux du triangle
        int[] yPoints = {y - headerHeight + 35, y - headerHeight + 45, y - headerHeight + 40}; // Déplacer vers le haut
        
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        g2.fillPolygon(triangle); // Remplir le triangle avec la couleur noire
        
 
        // Dessiner deux lignes verticales longues à l'extérieur du carré en bas avec une petite déviation en haut
        int lineHeight = h - 285;
        
        // Relier les lignes verticales aux autres lignes internes par des lignes inclinées

        g2.drawLine(x + w + 20, y + h - 140, x + w + 20, y + h + lineHeight + 100);
    }
}
