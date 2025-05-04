package GRAPHIQUE_IG;

import GRAPHIQUE.AbstractComponent;
import Logique.gates.ET;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.Arrays;
import java.util.List;

public class ET_ig extends AbstractComponent {
    private static final int BASE_W = 35;
    private static final int BASE_H = 42;
    private static final int MARGIN = 30;  // ajustez pour laisser de la place aux traits d’entrée/sortie

    private List<Logique.Principale.State> inputStates =
        Arrays.asList(Logique.Principale.State.UNKNOWN,
                      Logique.Principale.State.UNKNOWN);
    private Logique.Principale.State outputState =
        Logique.Principale.State.UNKNOWN;

    public ET_ig() {
        super("AND", 2, 1);
        setOpaque(false);
        // On réserve un peu de marge pour les lignes d’E/S
        setPreferredSize(new Dimension(BASE_W + MARGIN, BASE_H + MARGIN));

         /*Création du composant logique associé*/
    	ET etLogique = new ET("ET#"+System.currentTimeMillis(), getX(), getY());
    	setComposant(etLogique);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Antialiasing et trait
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.BLACK);

        // Rotation autour du centre du composant
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        g2.rotate(Math.toRadians(getRotationAngle()), cx, cy);

        // Déterminer les bornes de dessin
        int w = BASE_W;
        int h = BASE_H;
        int x = (getWidth() - w) / 2;
        int y = (getHeight() - h) / 2;

        // Corps de la porte AND : rectangle gauche
        g2.drawLine(x, y, x + w/2, y);
        g2.drawLine(x, y + h, x + w/2, y + h);
        g2.drawLine(x, y, x, y + h);

        // Arc arrondi à droite
        g2.draw(new Arc2D.Double(
            x + w/2 - 1,   // -1 pour joindre proprement
            y,
            w/2,
            h,
            90, -180,
            Arc2D.OPEN
        ));

        // Tracer les lignes d’entrée
        int inOffset = 15;
        g2.drawLine(
            x - inOffset, 
            y + (int)(0.25 * h),
            x, 
            y + (int)(0.25 * h)
        );
        g2.drawLine(
            x - inOffset,
            y + (int)(0.75 * h),
            x,
            y + (int)(0.75 * h)
        );

        // Tracer la ligne de sortie
        int outOffset = inOffset + (w/2);
        g2.drawLine(
            x + w,
            y + h/2,
            x + w + outOffset,
            y + h/2
        );

        g2.dispose();
    }

    /** Met à jour l’état d’une entrée (appelée depuis ConnectionManager) */
    public void setInputState(int index, Logique.Principale.State s) {
        inputStates.set(index, s);
    }

    /** Retourne l’état courant d’une entrée */
    public Logique.Principale.State getSelectedInput(int index) {
        return inputStates.get(index);
    }

    /** Met à jour et affiche la sortie */
    public void updateOutput(Logique.Principale.State output) {
        this.outputState = output;
        repaint();
    }
}
