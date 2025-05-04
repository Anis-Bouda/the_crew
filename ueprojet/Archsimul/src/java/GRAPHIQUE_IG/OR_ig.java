package GRAPHIQUE_IG;

import GRAPHIQUE.AbstractComponent;
import Logique.gates.OU;

import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import java.util.Arrays;
import java.util.List;

public class OR_ig extends AbstractComponent {
    private static final int BASE_W = 35;
    private static final int BASE_H = 42;
    private static final int MARGIN = 40;

    private List<Logique.Principale.State> inputStates =
        Arrays.asList(Logique.Principale.State.UNKNOWN, Logique.Principale.State.UNKNOWN);
    private Logique.Principale.State outputState =
        Logique.Principale.State.UNKNOWN;

    public OR_ig() {
        super("OU", 2, 1);
        setOpaque(false);
        setPreferredSize(new Dimension(BASE_W + MARGIN + 30, BASE_H + 10));

        /*Création du composant logique associé*/
    	OU ouLogique = new OU("OU#"+System.currentTimeMillis(), getX(), getY());
    	setComposant(ouLogique);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        // Rotation centrée
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        g2.rotate(Math.toRadians(getRotationAngle()), cx, cy);

        // Centrage du OR
        int w = BASE_W, h = BASE_H;
        int x = (getWidth() - w) / 2;
        int y = (getHeight() - h) / 2;

        // Forme du OR
        g2.draw(new CubicCurve2D.Double(x, y, x + 0.7 * w, y, x + w, y + 0.3 * h, x + w, y + h / 2));
        g2.draw(new CubicCurve2D.Double(x + w, y + h / 2, x + w, y + 0.7 * h, x + 0.7 * w, y + h, x, y + h));
        g2.draw(new QuadCurve2D.Double(x, y + h, x + 0.2 * w, y + h / 2, x, y));

        // Entrées
        g2.drawLine(x - 15, y + (int)(0.25 * h), x, y + (int)(0.25 * h));
        g2.drawLine(x - 15, y + (int)(0.75 * h), x, y + (int)(0.75 * h));

        // Sortie
        g2.drawLine(x + w, y + h / 2, x + w + 17 + (int)(0.5 * w), y + h / 2);

        g2.dispose();
    }

    public void setInputState(int index, Logique.Principale.State s) {
        inputStates.set(index, s);
    }

    public Logique.Principale.State getSelectedInput(int index) {
        return inputStates.get(index);
    }

    public void updateOutput(Logique.Principale.State output) {
        this.outputState = output;
        repaint();
    }
}
