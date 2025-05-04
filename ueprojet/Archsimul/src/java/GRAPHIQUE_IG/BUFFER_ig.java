package GRAPHIQUE_IG;

import GRAPHIQUE.AbstractComponent;
import Logique.gates.Buffer;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Collections;
import java.util.List;

public class BUFFER_ig extends AbstractComponent {
    private static final int BASE_W   = 60;  // largeur du triangle
    private static final int BASE_H   = 60;  // hauteur du triangle
    private static final int MARGIN_X = 40;  // marge horizontale pour les lignes d’E/S
    private static final int MARGIN_Y = 10;  // marge verticale

    // États des entrées/sortie
    private List<Logique.Principale.State> inputStates =
        Collections.singletonList(Logique.Principale.State.UNKNOWN);
    private Logique.Principale.State outputState =
        Logique.Principale.State.UNKNOWN;

    public BUFFER_ig() {
        super("BUFFER", 1, 1);
        setOpaque(false);
        // On réserve un peu de place autour du triangle pour dessiner les lignes
        setPreferredSize(new Dimension(BASE_W + MARGIN_X, BASE_H + MARGIN_Y));

        /*la création de la porte logique pour le liens  */
         Buffer bufferLogique= new Logique.gates.Buffer("BUFFER#"+System.currentTimeMillis(),getX(),getY());
        setComposant(bufferLogique);
   
        }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Antialiasing et style de trait
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.BLACK);

        // Rotation autour du centre du composant
        int cx = getWidth()  / 2;
        int cy = getHeight() / 2;
        g2.rotate(Math.toRadians(getRotationAngle()), cx, cy);

        // Positionner le triangle au centre
        int w = BASE_W;
        int h = BASE_H;
        int x = (getWidth()  - w) / 2;
        int y = (getHeight() - h) / 2;

        // Dessiner le triangle de la porte BUFFER
        Path2D triangle = new Path2D.Double();
        triangle.moveTo(x,       y);       // coin supérieur gauche
        triangle.lineTo(x + w,   y + h/2); // pointe droite
        triangle.lineTo(x,       y + h);   // coin inférieur gauche
        triangle.closePath();
        g2.draw(triangle);

        // Ligne d’entrée (à gauche)
        g2.drawLine(
            x - MARGIN_X/2,
            y + h/2,
            x,
            y + h/2
        );

        // Ligne de sortie (à droite)
        g2.drawLine(
            x + w,
            y + h/2,
            x + w + MARGIN_X/2,
            y + h/2
        );

        g2.dispose();
    }

    /** Met à jour l’état d’une entrée */
    public void setInputState(int index, Logique.Principale.State s) {
        inputStates.set(index, s);
    }

    /** Récupère l’état courant d’une entrée */
    public Logique.Principale.State getSelectedInput(int index) {
        return inputStates.get(index);
    }

    /** Met à jour et redessine la sortie */
    public void updateOutput(Logique.Principale.State output) {
        outputState = output;
        repaint();
    }
}
