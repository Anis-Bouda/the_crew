package GRAPHIQUE_IG;

import GRAPHIQUE.AbstractComponent;
import Logique.gates.XNOR;

import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import java.util.Arrays;
import java.util.List;

public class XNOR_ig extends AbstractComponent {
    private static final int BASE_W   = 35;  // largeur de la porte
    private static final int BASE_H   = 42;  // hauteur de la porte
    private static final int MARGIN_X = 40;  // marge horizontale pour les fils
    private static final int MARGIN_Y = 10;  // marge verticale

    // États des entrées et sortie
    private List<Logique.Principale.State> inputStates =
        Arrays.asList(Logique.Principale.State.UNKNOWN,
                      Logique.Principale.State.UNKNOWN);
    private Logique.Principale.State outputState =
        Logique.Principale.State.UNKNOWN;

    public XNOR_ig() {
        super("XNOR", 2, 1);
        setOpaque(false);
        // Espace réservé autour de la forme pour les fils d’E/S
        setPreferredSize(new Dimension(BASE_W + MARGIN_X, BASE_H + MARGIN_Y));

        /*Création du composant logique associé*/
    	XNOR xnorLogique = new XNOR("XNOR#"+System.currentTimeMillis(), getX(), getY());
    	setComposant(xnorLogique);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Lissage et style
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.BLACK);

        // Rotation centrée
        int cx = getWidth()  / 2;
        int cy = getHeight() / 2;
        g2.rotate(Math.toRadians(getRotationAngle()), cx, cy);

        // Détermination de la zone de dessin
        int w = BASE_W;
        int h = BASE_H;
        int x = (getWidth()  - w) / 2;
        int y = (getHeight() - h) / 2;

        //  Courbe additionnelle pour XNOR (décalée à gauche)
        g2.draw(new QuadCurve2D.Double(
            x - 5,
            y + h - 7,
            x + 0.2 * w,
            y + h / 2,
            x - 5,
            y
        ));

        //  Forme principale (même base que XOR/OR)
        g2.draw(new CubicCurve2D.Double(
            x, y,
            x + 0.7 * w, y,
            x + w, y + 0.3 * h,
            x + w, y + h / 2
        ));
        g2.draw(new CubicCurve2D.Double(
            x + w, y + h / 2,
            x + w, y + 0.7 * h,
            x + 0.7 * w, y + h,
            x, y + h
        ));
        g2.draw(new QuadCurve2D.Double(
            x, y + h,
            x + 0.2 * w, y + h / 2,
            x, y
        ));

        // Cercle de négation pour XNOR
        int circleR = 6;
        int circleX = x + w + 2;
        int circleY = y + h/2 - circleR/2;
        g2.drawOval(circleX, circleY, circleR, circleR);

        //  Fils d’entrée (à gauche)
        int inOffset = MARGIN_X / 2;
        g2.drawLine(
            x - inOffset,
            y + (int)(0.35 * h),
            x,
            y + (int)(0.35 * h)
        );
        g2.drawLine(
            x - inOffset,
            y + (int)(0.65 * h),
            x,
            y + (int)(0.65 * h)
        );

        //  Fil de sortie (à droite, depuis le cercle)
        g2.drawLine(
            circleX + circleR,
            y + h/2,
            circleX + circleR + inOffset,
            y + h/2
        );

        g2.dispose();
    }

    /** Met à jour l’état d’une entrée (appelé par ConnectionManager) */
    public void setInputState(int index, Logique.Principale.State s) {
        inputStates.set(index, s);
    }

    /** Récupère l’état courant d’une entrée */
    public Logique.Principale.State getSelectedInput(int index) {
        return inputStates.get(index);
    }

    /** Met à jour et redessine la sortie */
    public void updateOutput(Logique.Principale.State output) {
        this.outputState = output;
        repaint();
    }
}
