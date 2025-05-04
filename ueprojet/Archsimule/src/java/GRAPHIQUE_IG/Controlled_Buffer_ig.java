package GRAPHIQUE_IG;
import Logique.gates.ControlledBuffer;
import GRAPHIQUE.AbstractComponent;
import java.awt.*;
import java.awt.geom.*;




public class Controlled_Buffer_ig extends AbstractComponent{

    public Controlled_Buffer_ig() {
        super("ControlledBuffer", 2, 1);
        setOpaque(false);
        setPreferredSize(new Dimension(80, 60));  // important pour le dessin
    
    
        ControlledBuffer Controlled_Buffer_logique = new ControlledBuffer("ControlledBuffer#"+System.currentTimeMillis(), getY(), getX());
        setComposant(Controlled_Buffer_logique);
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        g2.rotate(Math.toRadians(getRotationAngle()), cx, cy);

        int w = 40;
        int h = 40;
        int x = (getWidth() - w) / 2;
        int y = (getHeight() - h) / 2;

        
        Path2D triangle = new Path2D.Double();
        triangle.moveTo(x, y);
        triangle.lineTo(x, y + h);
        triangle.lineTo(x + w, y + h / 2);
        triangle.closePath();
        g2.draw(triangle);

        // Cercle d'inversion
        int circleRadius = 14;
        g2.draw(new Ellipse2D.Double(x + w - 2, y + h / 2 - circleRadius / 2.0, circleRadius, circleRadius));

      
        g2.drawLine(x - 22, y + (int)(0.3 * h), x, y + (int)(0.3 * h));

        // Entrée contrôle (bas)
        g2.drawLine(x - 22, y + (int)(0.7 * h), x, y + (int)(0.7 * h));

        // Sortie
        g2.drawLine(x + w + circleRadius, y + h / 2, x + w + circleRadius + 20, y + h / 2);

        g2.dispose();
    }
}
