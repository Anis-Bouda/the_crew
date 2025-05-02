import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ENCODER extends AbstractComponent2 {
    private final int width = 60;  // Largeur de l'encodeur
    private final int height = 90; // Hauteur de l'encodeur

    public ENCODER() {
        super("ENCODER", 8, 1);  // Encoder 8 entrées et 1 sortie
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocus();
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY());

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port d'entrée ENCODER sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port de sortie ENCODER sélectionné !");
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

        // Dessiner la forme de l'encodeur (rectangle)
        g2.drawRect(x, y, w, h);

        // Entrées (8 entrées sur le côté gauche)
        int portSpacing = (h / 8) + 1 / 2;  // Espacement entre les ports
        for (int i = 1; i <= 8; i++) {
            // Déplacer les ports d'entrée un peu plus en haut
            g2.drawLine(x - 10, y + i * portSpacing - 4, x, y + i * portSpacing - 4);  // Entrées
        }

        // Sortie (1 sortie à droite)
        g2.drawLine(x + w, y + h / 2 +2, x + w + 20, y + h / 2 +2);  // Sortie, déplacée aussi

        // Afficher le texte "ENCODER" et "8x1" au centre avec un saut de ligne
        g2.setFont(new Font("Arial", Font.PLAIN, 12)); // Réduire la taille de la police
        FontMetrics metrics = g2.getFontMetrics();

        // "ENCODER"
        String text1 = "ENCODER";
        int text1Width = metrics.stringWidth(text1);
        int text1Height = metrics.getHeight();
        int text1X = x + (w - text1Width) / 2;
        int text1Y = y + (h - text1Height) / 2 - text1Height / 2;  // Position "ENCODER"
        g2.drawString(text1, text1X, text1Y);

        // "8x1" (sous "ENCODER")
        String text2 = "8x1";
        int text2Width = metrics.stringWidth(text2);
        int text2Y = text1Y + text1Height; // Placer "8x1" sous "ENCODER"
        int text2X = x + (w - text2Width) / 2;
        g2.drawString(text2, text2X, text2Y + 15);
    }
}
