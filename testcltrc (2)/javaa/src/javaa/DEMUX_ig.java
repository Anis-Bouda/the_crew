import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import plexers.DEMUX;

public class DEMUX_ig extends AbstractComponent {
    private final int width = 40;
    private final int height = 50;

    public DEMUX_ig() {
        super("DEMUX", 1, 2);  // Changer le nombre de ports d'entrée et de sortie
        setOpaque(false);

         /*la création de la porte logique pour le liens  */
         DEMUX DEMUXlogique = new DEMUX(TOOL_TIP_TEXT_KEY, UNDEFINED_CONDITION, ALLBITS, ABORT);
         setComposant(DEMUXlogique);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocus();
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY());

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port d'entrée DEMUX sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port de sortie DEMUX sélectionné !");
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

        // Dessiner la forme du démultiplexeur (forme ressemblant à un DEMUX 1x2 de Logisim)
        int[] xPoints = {x, x + w, x + w, x}; // Côté droit plus petit que le côté gauche
        int[] yPoints = {y+9, y , y + h , y + h-9}; // Forme de type trapèze
        g2.drawPolygon(xPoints, yPoints, 4);
        
        // Entrée
        g2.drawLine(x - 20, y + h / 2, x, y + h / 2);  // Entrée 1 (au centre de la forme)

        // Lignes de sorties
        g2.drawLine(x + w, y + 15, x + w + 50, y + 15);  // Sortie 1
        g2.drawLine(x + w, y + h - 12, x + w + 50, y + h - 12);  // Sortie 2

        // Ligne de sélection (pour indiquer le signal de sélection)
        g2.drawLine(x - 20, y + h / 2, x - 40, y + h / 2);
        g2.drawString("S", x - 50, y + h / 2 + 5);  // Label du signal de sélection

        // Afficher le texte "DEMUX" et "1x2" au centre avec un saut de ligne
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics metrics = g2.getFontMetrics();
        
        // "DEMUX"
        String text1 = "DEMUX";
        int text1Width = metrics.stringWidth(text1);
        int text1Height = metrics.getHeight();
        int text1X = x + (w - text1Width) / 2;
        int text1Y = y + (h - text1Height) / 2 - text1Height / 2;  // Position "DEMUX"
        g2.drawString(text1, text1X, text1Y+10);
        
        // "1x2" (sous "DEMUX")
        String text2 = "1x2";
        int text2Width = metrics.stringWidth(text2);
        int text2Y = text1Y + text1Height; // Placer "1x2" sous "DEMUX"
        int text2X = x + (w - text2Width) / 2;
        g2.drawString(text2, text2X, text2Y+15);
    }
}
