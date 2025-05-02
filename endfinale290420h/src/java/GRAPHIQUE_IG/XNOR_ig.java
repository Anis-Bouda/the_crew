package GRAPHIQUE_IG;
import GRAPHIQUE.*;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;

import Logique.gates.XNOR;

public class XNOR_ig extends AbstractComponent {
    private final int width = 35;   // Largeur réduite
    private final int height = 42;  // Hauteur réduite
    private final int offsetY = 5;  // Décalage global vers le bas

    public XNOR_ig() {
        super("XNOR", 2, 1); // Nom du composant XNOR
        setOpaque(false);
        /*Création du composant logique associé*/
    	XNOR xnorLogique = new XNOR("XNOR#"+System.currentTimeMillis(), getX(), getY());
    	setComposant(xnorLogique);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickPoint = e.getPoint();
                // Conversion en coordonnées globales avec décalage vertical
                clickPoint.translate(getX(), getY() + offsetY);

                for (Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port d'entrée XNOR sélectionné !");
                    }
                }
                for (Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port de sortie XNOR sélectionné !");
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Activation de l'anti-aliasing pour un rendu plus lisse
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Définition de la transparence (arrière-plan)
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);
        
        // Position et dimensions de base adaptées
        int x = 8, y = 4 + offsetY;
        int w = width, h = height;
        
        // Courbe additionnelle pour la porte XNOR (décalée vers la gauche)
        g2.draw(new QuadCurve2D.Double(x - 5, y + h - 7, x + 0.2 * w, y + h / 2, x - 5, y));
        
        // Forme principale de la porte XOR (identique à XOR)
        g2.draw(new CubicCurve2D.Double(x, y, x + 0.7 * w, y, x + w, y + 0.3 * h, x + w, y + h / 2));
        g2.draw(new CubicCurve2D.Double(x + w, y + h / 2, x + w, y + 0.7 * h, x + 0.7 * w, y + h, x, y + h));
        g2.draw(new QuadCurve2D.Double(x, y + h, x + 0.2 * w, y + h / 2, x, y));
        
        // Petit cercle de négation pour XNOR
        int circleRadius = 6;
        int circleX = x + w + 2;
        int circleY = y + h / 2 - circleRadius / 2;
        g2.drawOval(circleX, circleY, circleRadius, circleRadius);
        
        // Dessin des fils d'entrée (positionnés proportionnellement)
        g2.drawLine(x - 15, y + (int)(0.35 * h), x, y + (int)(0.35 * h));
        g2.drawLine(x - 15, y + (int)(0.65 * h), x, y + (int)(0.65 * h));
        
        // Dessin du fil de sortie, ajusté pour partir du cercle
        g2.drawLine(circleX + circleRadius, y + h / 2, x + w + 50, y + h / 2);
    }
    
    // initialisation par defaut 
    private java.util.List<Logique.Principale.State> inputStates = java.util.Arrays.asList(Logique.Principale.State.UNKNOWN, Logique.Principale.State.UNKNOWN);
    private Logique.Principale.State outputState = Logique.Principale.State.UNKNOWN;

    // mettre a jour l'etat 
    public void setInputState(int index, Logique.Principale.State s) {
        inputStates.set(index, s);
    }

    // recuperation de l'etat 
    public Logique.Principale.State getSelectedInput(int index) {
        return inputStates.get(index);
    }

    // mettre a jour la sortie dans la sortie graphique 
    public void updateOutput(Logique.Principale.State output) {
        this.outputState = output;
        System.out.println("🖥️ Sortie mise à jour dans la vue : " + output);
        repaint(); 
    }
}
