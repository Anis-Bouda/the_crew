package GRAPHIQUE_IG;

import GRAPHIQUE.AbstractComponent;
import Logique.wiring.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Transistor_ig extends AbstractComponent {

    public Transistor_ig() {
        super("TRANSISTOR", 2, 1);  // 2 entrées, 1 sortie
        setOpaque(false);
        setPreferredSize(new Dimension(100, 100));

        Transistor transistorLogique = new Transistor("TRANSISTOR#" + System.currentTimeMillis(), getX(), getY());
        setComposant(transistorLogique);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);
    
        int w = getWidth();
        int h = getHeight();
        int cx = w / 2;
        int cy = h / 2;
    
        g2.drawOval(cx - 30, cy - 30, 60, 60);
    
       
        g2.drawLine(cx, cy - 30, cx, cy + 30);
    
    
        g2.drawLine(cx - 30, cy - 10, cx - 50, cy - 10);
        g2.setColor(Color.RED);
        g2.fillOval(cx - 55, cy - 15, 10, 10); // rouge
        g2.setColor(Color.BLACK);
    
        // Entrée 1 (en bas à gauche)
        g2.drawLine(cx - 30, cy + 10, cx - 50, cy + 10);
        g2.setColor(Color.RED);
        g2.fillOval(cx - 55, cy + 5, 10, 10); // rouge
        g2.setColor(Color.BLACK);
    
        // Sortie (à droite)
        g2.drawLine(cx + 30, cy, cx + 50, cy);
    
    
        // Flèche vers le bas (au centre bas)
        int[] arrowX = {cx - 5, cx + 5, cx};
        int[] arrowY = {cy + 25, cy + 25, cy + 35};
        g2.fillPolygon(arrowX, arrowY, 3);
    
        g2.dispose();
    }
    
}
