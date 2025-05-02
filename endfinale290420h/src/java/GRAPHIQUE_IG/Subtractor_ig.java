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

import Logique.Arithmetics.SUB;




public class Subtractor_ig extends AbstractComponent {
    private final int width = 40;
    private final int height = 40;
    private final int offsetY = 5;

    public Subtractor_ig() {
        super("NEGATOR", 5, 2);
        setOpaque(false);

         /*la création de la porte logique pour le liens  */
         SUB SUBlogique =new SUB("SUB#"+System.currentTimeMillis(),2 , getX(), getY());
         setComposant(SUBlogique);

         
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

        g2.drawRect(x, y, w, h);
        g2.drawOval(x + w - 5, y + h / 2 - 3, 6, 6);
        g2.drawLine(x - 15, y + (int)(0.1 * h), x, y + (int)(0.1 * h));
        g2.drawLine(x - 15, y + (int)(0.3 * h), x, y + (int)(0.3 * h));
        g2.drawLine(x - 15, y + (int)(0.5 * h), x, y + (int)(0.5 * h));
        g2.drawLine(x - 15, y + (int)(0.8 * h), x, y + (int)(0.8 * h));
        g2.drawLine(x - 15, y + (int)(1 * h), x, y + (int)(1 * h));
        g2.drawLine(x + w, y + (int)(0.3 * h), x + w + 29, y + (int)(0.3 * h)); 
        g2.drawLine(x + w, y + (int)(0.8 * h), x + w + 29, y + (int)(0.8 * h)); 
        g2.setFont(new Font("Arial", Font.PLAIN, 25));
        FontMetrics metrics = g2.getFontMetrics();
        
        // "DEMUX"
        String text1 = "-";
        int text1Width = metrics.stringWidth(text1);
        int text1Height = metrics.getHeight() -20 ;
        int text1X = x + (w - text1Width) / 2;
        int text1Y = y + (h - text1Height) / 2 - text1Height / 2;  // Position "DEMUX"
        g2.drawString(text1, text1X, text1Y+10);
    }
}
