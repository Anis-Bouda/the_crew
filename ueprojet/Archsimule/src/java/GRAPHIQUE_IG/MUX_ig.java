package GRAPHIQUE_IG;
import GRAPHIQUE.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import Logique.plexers.MUX;

public class MUX_ig extends AbstractComponent {
    private final int width = 40;
    private final int height = 50;

    public MUX_ig() {
        super("MUX", 2, 1);
        setOpaque(false);

         /*la création de la porte logique pour le liens  */
         MUX Muxlogique = new MUX(TOOL_TIP_TEXT_KEY, UNDEFINED_CONDITION, ALLBITS, ABORT);
         setComposant(Muxlogique);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocus();
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY());

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("Port d'entrée MUX sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("Port de sortie MUX sélectionné !");
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

        int x = 10, y = 4; 
        int w = width, h = height;

        // Dessiner la forme du multiplexeur (forme ressemblant à un MUX 2x1 de Logisim)
        int[] xPoints = {x, x + w, x + w, x }; // Côté droit plus petit que le côté gauche
        int[] yPoints = {y, y+9, y + h-9, y + h}; // Forme de type trapèze
        g2.drawPolygon(xPoints, yPoints, 4);
        
        // Entrées
        g2.drawLine(x - 20, y + 15, x, y + 15);  // Entrée 1
        g2.drawLine(x - 20, y + h - 12, x, y + h - 12);  // Entrée 2

        // Ligne de sélection (pour indiquer le signal de sélection)
        g2.drawLine(x - 20, y + h / 2, x - 40, y + h / 2);
        g2.drawString("S", x - 50, y + h / 2 + 5);  // Label du signal de sélection

        // Sortie
        g2.drawLine(x + w, y + h / 2+1, x + w + 50, y + h / 2);

        // Afficher le texte "MUX" et "2x1" au centre avec un saut de ligne
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics metrics = g2.getFontMetrics();
        
        // "MUX"
        String text1 = "MUX";
        int text1Width = metrics.stringWidth(text1);
        int text1Height = metrics.getHeight();
        int text1X = x + (w - text1Width) / 2;
        int text1Y = y + (h - text1Height) / 2 - text1Height / 2;  // Position "MUX"
        g2.drawString(text1, text1X, text1Y+10);
        
        // "2x1" (sous "MUX")
        String text2 = "2x1";
        int text2Width = metrics.stringWidth(text2);
        int text2Y = text1Y + text1Height; // Placer "2x1" sous "MUX"
        int text2X = x + (w - text2Width) / 2;
        g2.drawString(text2, text2X, text2Y+15);
    }
}
