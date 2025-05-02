package GRAPHIQUE_IG;
import GRAPHIQUE.*;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import Logique.Arithmetics.BitFINDER;

public class BitFinder_ig extends AbstractComponent {
    private final int width = 50;
    private final int height = 50;
    private final int offsetY = 5;
    
    public BitFinder_ig() {
        super("BIT FINDER", 5, 1); // 5 entrée, 1 sortie
        setOpaque(false);
        
         /*la création de la porte logique */
         BitFINDER bitFlogique = new BitFINDER("biteFinder#"+System.currentTimeMillis(), 5, 1, getY(), getX());
         setComposant(bitFlogique);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY() + offsetY);

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println(" Port d'entrée BIT FINDER sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println(" Port de sortie BIT FINDER sélectionné !");
                    }
                }
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
        g2.setColor(Color.BLACK);

        int x = 8, y = 4 + offsetY;
        int w = width, h = height;
        

        // Dessiner un rectangle pour représenter le Bit Finder
        g2.drawRect(x, y, w, h);

        // Étiquette "?" pour indiquer la recherche de bit
        g2.drawString("?", x + w / 2 - 5, y + h / 2 + 5);

       // Entrée
       g2.drawLine(x - 15, y + (int)(0.05 * h), x, y + (int)(0.05 * h));
       g2.drawLine(x - 15, y + (int)(0.2 * h), x, y + (int)(0.2 * h));
       g2.drawLine(x - 15, y + (int)(0.4 * h), x, y + (int)(0.4 * h));
       g2.drawLine(x - 15, y + (int)(0.65 * h), x, y + (int)(0.65 * h));
       g2.drawLine(x - 15, y + (int)(0.8 * h), x, y + (int)(0.8 * h));
       
       // Sortie
       g2.drawLine(x + w, y + h / 3, x + w + 20, y + h / 3);
    }
    
    // initialisation par defaut 
    private java.util.List<Logique.Principale.State> inputStates = java.util.Arrays.asList(Logique.Principale.State.UNKNOWN, Logique.Principale.State.UNKNOWN, Logique.Principale.State.UNKNOWN, Logique.Principale.State.UNKNOWN, Logique.Principale.State.UNKNOWN) ;
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
        System.out.println(" Sortie mise à jour dans la vue : " + output);
        repaint(); 
    }

}

