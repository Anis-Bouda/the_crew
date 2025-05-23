package GRAPHIQUE;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class OutPut extends AbstractComponent {
    private final int width = 40;
    private final int height = 40;
    private final int offsetY = 5;

    // Indicateur pour l'état de la LED (allumée ou éteinte)
    private boolean ledState = false;

    public OutPut() {
        super("OutPut", 1, 0); 
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Inverser l'état de la LED à chaque clic
                ledState = !ledState;
                System.out.println(" LED " + (ledState ? "allumée" : "éteinte"));
                repaint();  // Repeindre le composant pour afficher l'état actuel de la LED
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
        
        int x = 8, y = 4 + offsetY;

        // Dessiner le cercle représentant la LED
        if (ledState) {
            // LED allumée : couleur verte
            g2.setColor(Color.GREEN);
        } else {
            // LED éteinte : couleur grise
            g2.setColor(Color.GRAY);
        }

        g2.fill(new Ellipse2D.Double(x, y, width, height));  // Dessiner le cercle de la LED

        // Ajouter un contour noir autour de la LED
        g2.setColor(Color.BLACK);
        g2.draw(new Ellipse2D.Double(x, y, width, height));
        
        // Dessiner le port de sortie (connecté à la LED) à gauche
        g2.setColor(Color.BLACK);
        g2.drawLine(x - 20, y + height / 2, x, y + height / 2);  // Port de sortie à gauche de la LED
    }
}
