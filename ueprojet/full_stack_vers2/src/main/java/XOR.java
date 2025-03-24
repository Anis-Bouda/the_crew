import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;

public class XOR extends AbstractComponent {
    private final int width = 35;   // Largeur réduite
    private final int height = 42;  // Hauteur réduite
    private final int offsetY = 5;  // Décalage global vers le bas

    public XOR() {
        super("XOR", 2, 1); // 2 entrées, 1 sortie
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickPoint = e.getPoint();
                // Conversion en coordonnées globales en ajoutant le décalage vertical
                clickPoint.translate(getX(), getY() + offsetY);

                for (Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port d'entrée XOR sélectionné !");
                    }
                }
                for (Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port de sortie XOR sélectionné !");
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Active l'anti-aliasing pour un rendu plus lisse
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Configure la transparence de l'arrière-plan
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);
        
        // Position et dimensions de base
        int x = 8, y = 4 + offsetY;
        int w = width, h = height;
        
        // Courbe additionnelle pour la porte XOR (décalée vers la gauche)
        g2.draw(new QuadCurve2D.Double(x - 5, y + h - 7, x + 0.2 * w, y + h / 2, x - 5, y));
        
        // Forme principale de la porte XOR (similaire à la porte OR)
        g2.draw(new CubicCurve2D.Double(x, y, x + 0.7 * w, y, x + w, y + 0.3 * h, x + w, y + h / 2));
        g2.draw(new CubicCurve2D.Double(x + w, y + h / 2, x + w, y + 0.7 * h, x + 0.7 * w, y + h, x, y + h));
        g2.draw(new QuadCurve2D.Double(x, y + h, x + 0.2 * w, y + h / 2, x, y));
        
        // Dessin des fils d'entrée (positionnés proportionnellement)
        g2.drawLine(x - 15, y + (int)(0.35 * h), x, y + (int)(0.35 * h));
        g2.drawLine(x - 15, y + (int)(0.65 * h), x, y + (int)(0.65 * h));
        
        // Dessin du fil de sortie
        g2.drawLine(x + w, y + h / 2, x + w + 20, y + h / 2);
    }
}
