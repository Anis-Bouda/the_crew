import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class BitFinder extends AbstractComponent {
    private final int width = 50;
    private final int height = 50;
    private final int offsetY = 5;

    public BitFinder() {
        super("BIT FINDER", 1, 1); // 1 entrée, 1 sortie
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY() + offsetY);

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port d'entrée BIT FINDER sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port de sortie BIT FINDER sélectionné !");
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

        // Dessiner un rectangle pour représenter le Bit Finder
        g2.drawRect(x, y, w, h);

        // Étiquette "?" pour indiquer la recherche de bit
        g2.drawString("?", x + w / 2 - 5, y + h / 2 + 5);

        // Entrée
        g2.drawLine(x - 15, y + h / 2, x, y + h / 2);
        
        // Sortie
        g2.drawLine(x + w, y + h / 2, x + w + 20, y + h / 2);
    }
}
