import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import javax.swing.*;

public class NOT extends AbstractComponent {
    private final int width = 50;  // Largeur du triangle
    private final int height = 60; // Hauteur du triangle

    public NOT() {
        super("NOT", 1, 1); // 1 entrée, 1 sortie
        setOpaque(false); // Rendre le fond transparent

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY()); // ✅ Convertir en coordonnées globales

                for (java.awt.Point port : getInputPorts()) { 
                    if (port.distance(clickPoint) < 10) { 
                        System.out.println("✅ Port d'entrée NOT sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) { 
                    if (port.distance(clickPoint) < 10) { 
                        System.out.println("✅ Port de sortie NOT sélectionné !");
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
      
        // Définition des couleurs et de l'épaisseur des traits
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        int x = 10, y = 0;
        int w = width, h = height;

        // 🔺 Dessiner le triangle de la porte NOT
        Path2D triangle = new Path2D.Double();
        triangle.moveTo(x, y);                 // Coin supérieur gauche
        triangle.lineTo(x + w, y + h / 2);      // Pointe droite
        triangle.lineTo(x, y + h);              // Coin inférieur gauche
        triangle.closePath();
        g2.draw(triangle);

        // ⚪ Dessiner le petit cercle de sortie (inversion logique)
        int circleRadius = 6;
        int circleX = x + w + 2;
        int circleY = y + h / 2 - circleRadius / 2;
        g2.drawOval(circleX, circleY, circleRadius, circleRadius);

        // ➡️ Entrée (ligne à gauche)
        g2.drawLine(x - 20, y + h / 2, x, y + h / 2);

        // ➡️ Sortie (ligne à droite)
        g2.drawLine(circleX + circleRadius, y + h / 2, circleX + 40, y + h / 2);

       // Port de sortie
    }
}
