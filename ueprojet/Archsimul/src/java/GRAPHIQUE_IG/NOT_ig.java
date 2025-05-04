package GRAPHIQUE_IG;

import GRAPHIQUE.AbstractComponent;
import Logique.gates.NON;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.util.List;

public class NOT_ig extends AbstractComponent {
    private static final int BASE_W = 50;   // largeur du triangle
    private static final int BASE_H = 60;   // hauteur du triangle
    private static final int MARGIN = 40;   // marge pour les fils

    // états GUI
    private List<Logique.Principale.State> inputStates =
        Arrays.asList(Logique.Principale.State.UNKNOWN);
    private Logique.Principale.State outputState =
        Logique.Principale.State.UNKNOWN;

    public NOT_ig() {
        super("NOT", 1, 1);
        setOpaque(false);

        // taille initiale pour rotationAngle = 0
        setPreferredSize(new Dimension(BASE_W + MARGIN, BASE_H));

        /*la création de la porte logique pour le liens  */
        NON nonLogique = new NON("NOT#" + System.currentTimeMillis(), getX(), getY());
        setComposant(nonLogique);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // préparation du Graphics2D 
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(
                            AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        // rotation autour du centre du panel
        int cx = getWidth()  / 2;
        int cy = getHeight() / 2;
        g2.rotate(Math.toRadians(getRotationAngle()), cx, cy);

        // dessin centré du NOT 
        int w = BASE_W, h = BASE_H;
        int x = (getWidth()  - w) / 2;
        int y = (getHeight() - h) / 2;

        // triangle
        Path2D triangle = new Path2D.Double();
        triangle.moveTo(x,     y);
        triangle.lineTo(x + w, y + h/2);
        triangle.lineTo(x,     y + h);
        triangle.closePath();
        g2.draw(triangle);

        // cercle
        int r = 6;
        int cxC = x + w + 2;
        int cyC = y + h/2 - r/2;
        g2.drawOval(cxC, cyC, r, r);

        // fils entrée / sortie
        g2.drawLine(x - MARGIN/2,    y + h/2, x,          y + h/2);
        g2.drawLine(cxC + r,         y + h/2, cxC + MARGIN, y + h/2);

        g2.dispose();
    }

    // Rien à ajouter pour rotateComponent() : c’est pris en charge
    // par AbstractComponent via Alt + clic gauche

    // gestion états/logique inchangée
   
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
