package GRAPHIQUE_IG;
import GRAPHIQUE.*;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

import Logique.Arithmetics.NEGATOR;

public class Negator_ig extends AbstractComponent {
    private final int width = 40;
    private final int height = 40;
    private final int offsetY = 5;

    public Negator_ig() {
        super("Negator", 2, 2);
        setOpaque(false);

          /*la création de la porte logique pour le liens  */
        NEGATOR Negatorlogique = new NEGATOR("Negator"+System.currentTimeMillis(), 2, getX(),getY());
        setComposant(Negatorlogique);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY() + offsetY);

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println(" Port d'entrée NEGATOR sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println(" Port de sortie NEGATOR sélectionné !");
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
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        int x = 8, y = 4 + offsetY;
        int w = width, h = height;

        // Dessiner le contour du composant (rectangle)
        g2.drawRect(x, y, w, h);

        // Dessiner le symbole de division (ligne horizontale et point au-dessus et en dessous)
        g2.draw(new Line2D.Double(x + 10, y + h / 2, x + w - 10, y + h / 2));
        g2.fillOval(x + w / 2 - 2, y + 10, 4, 4);
        g2.fillOval(x + w / 2 - 2, y + h - 14, 4, 4);

        // Entrées
        g2.drawLine(x - 15, y + (int)(0.3 * h), x, y + (int)(0.3 * h));
        g2.drawLine(x - 15, y + (int)(0.8 * h), x, y + (int)(0.8 * h));

        // Sortie
        g2.drawLine(x + w, y + (int)(0.3 * h), x + w + 29, y + (int)(0.3 * h)); 
        g2.drawLine(x + w, y + (int)(0.8 * h), x + w + 29, y + (int)(0.8 * h)); 
        g2.setFont(new Font("Arial", Font.PLAIN, 15));
        FontMetrics metrics = g2.getFontMetrics();
        
        // "DEMUX"
        String text1 = "Neg";
        int text1Width = metrics.stringWidth(text1);
        int text1Height = metrics.getHeight() -20;
        int text1X = x + (w - text1Width) / 2;
        int text1Y = y + (h - text1Height) / 2 - text1Height / 2;  // Position "DEMUX"
        g2.drawString(text1, text1X, text1Y+10);
    }
}
