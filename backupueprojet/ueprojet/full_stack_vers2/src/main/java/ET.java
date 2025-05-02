import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;

public class ET extends AbstractComponent {
    private final int width = 35;  // Largeur réduite
    private final int height = 42; // Hauteur réduite
    private final int offsetY = 5; // Décalage global vers le bas

    public ET() {
        super("AND", 2, 1); // 2 entrées, 1 sortie
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY() + offsetY);

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port d'entrée AND sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port de sortie AND sélectionné !");
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

        // Dessiner la moitié gauche du rectangle
        g2.drawLine(x, y, x + w / 2, y);
        g2.drawLine(x, y + h, x + w / 2, y + h);
        g2.drawLine(x, y, x, y + h);

        // Dessiner l'arc arrondi (moitié droite de la porte AND)
        g2.draw(new Arc2D.Double(x + w / 2 - 1, y, w / 2, h, 90, -180, Arc2D.OPEN));

        // Entrées
        g2.drawLine(x - 15, y + (int)(0.25 * h), x, y + (int)(0.25 * h));
        g2.drawLine(x - 15, y + (int)(0.75 * h), x, y + (int)(0.75 * h));

        // Sortie
        g2.drawLine(x + w, y + h / 2, x + w + 17 + (int)(0.5 * w), y + h / 2);
    }
}
