package GRAPHIQUE_IG;

import GRAPHIQUE.AbstractComponent;
import Logique.gates.NAND;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.Arrays;
import java.util.List;

public class NAND_ig extends AbstractComponent {
    private static final int BASE_W   = 35; // largeur de la porte AND
    private static final int BASE_H   = 42; // hauteur de la porte AND
    private static final int MARGIN_X = 40; // marge horizontale pour les fils
    private static final int MARGIN_Y = 10; // marge verticale

    // États des entrées et sortie
    private List<Logique.Principale.State> inputStates =
        Arrays.asList(Logique.Principale.State.UNKNOWN,
                      Logique.Principale.State.UNKNOWN);
    private Logique.Principale.State outputState =
        Logique.Principale.State.UNKNOWN;

    public NAND_ig() {
        super("NAND", 2, 1);
        setOpaque(false);
        // Réserver la place pour dessiner les fils d’E/S
        setPreferredSize(new Dimension(BASE_W + MARGIN_X, BASE_H + MARGIN_Y));

        /*Création du composant logique associé*/
    	NAND nandLogique = new NAND("NAND#"+System.currentTimeMillis(), getX(), getY());
    	setComposant(nandLogique);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Antialiasing et style de trait
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.BLACK);

        // Application de la rotation centrée
        int cx = getWidth()  / 2;
        int cy = getHeight() / 2;
        g2.rotate(Math.toRadians(getRotationAngle()), cx, cy);

        // Zone de dessin centrée
        int w = BASE_W;
        int h = BASE_H;
        int x = (getWidth()  - w) / 2;
        int y = (getHeight() - h) / 2;

        //  Corps de la porte AND (moitié gauche + arc)
        g2.drawLine(x,      y,      x + w/2, y);
        g2.drawLine(x,      y + h,  x + w/2, y + h);
        g2.drawLine(x,      y,      x,       y + h);
        g2.draw(new Arc2D.Double(
            x + w/2 - 1, y,
            w/2,         h,
            90,         -180,
            Arc2D.OPEN
        ));

        // Cercle de négation (NAND)
        int r = 6;
        int cxCircle = x + w + 2;
        int cyCircle = y + h/2 - r/2;
        g2.drawOval(cxCircle, cyCircle, r, r);

        // Fils d’entrée (à gauche)
        int inOffset = MARGIN_X / 2;
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

        //  Fil de sortie (à droite depuis le cercle)
        g2.drawLine(
            cxCircle + r,
            y + h/2,
            cxCircle + r + inOffset,
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
