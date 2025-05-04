
package GRAPHIQUE_IG;

import GRAPHIQUE.AbstractComponent;
import Logique.wiring.Splitter;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.util.List;

public class Splitter_ig extends AbstractComponent {

    private static final int BASE_W = 30;
    private static final int BASE_H = 60;
    private static final int MARGIN = 40;

    private List<Logique.Principale.State> inputStates =
        Arrays.asList(Logique.Principale.State.UNKNOWN);
    private List<Logique.Principale.State> outputStates =
        Arrays.asList(
            Logique.Principale.State.UNKNOWN,
            Logique.Principale.State.UNKNOWN,
            Logique.Principale.State.UNKNOWN,
            Logique.Principale.State.UNKNOWN
        );

    public Splitter_ig() {
        super("Splitter", 1, 4);
        setOpaque(false);
        setPreferredSize(new Dimension(BASE_W + MARGIN + 20, BASE_H + 20));

        // Création du composant logique associé
        Splitter logic = new Splitter("Splitter#" + System.currentTimeMillis(), getX(), getY(),4);
        setComposant(logic);
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

        // Centrage du splitter
        int w = BASE_W;
        int h = BASE_H;
        int x = (getWidth() - w) / 2;
        int y = (getHeight() - h) / 2;

        // Forme trapèze ou triangle représentant un splitter
        Path2D shape = new Path2D.Double();
        shape.moveTo(x, y);
        shape.lineTo(x + w, y + h / 2);
        shape.lineTo(x, y + h);
        shape.closePath();
        g2.draw(shape);

        // Ligne d'entrée (à gauche)
        g2.drawLine(x - 20, y + h / 2, x, y + h / 2);

        // Lignes de sortie (à droite)
        int spacing = h / 5;
        for (int i = 1; i <= 4; i++) {
            int yOut = y + i * spacing;
            g2.drawLine(x + w, y + h / 2, x + w + 20, yOut);
        }

        g2.dispose();
    }

    public void setInputState(Logique.Principale.State s) {
        inputStates.set(0, s);
    }

    public Logique.Principale.State getInputState() {
        return inputStates.get(0);
    }

    public void updateOutputs(List<Logique.Principale.State> newStates) {
        for (int i = 0; i < outputStates.size() && i < newStates.size(); i++) {
            outputStates.set(i, newStates.get(i));
        }
        repaint();
    }
}
