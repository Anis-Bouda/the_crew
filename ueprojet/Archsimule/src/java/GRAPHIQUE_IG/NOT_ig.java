package GRAPHIQUE_IG;
import GRAPHIQUE.*;
import Logique.gates.NON;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.util.List;

public class NOT_ig extends AbstractComponent {
    private final int width = 50;  // Largeur du triangle
    private final int height = 60; // Hauteur du triangle
    private final int offsetY = 0; // Décalage global si nécessaire

    public NOT_ig() {
        super("NOT", 1, 1); // 1 entrée, 1 sortie
        setOpaque(false); // Fond transparent
        
         /*la création de la porte logique pour le liens  */
        NON nonLogique = new NON("NOT#" + System.currentTimeMillis(), getX(), getY());
        setComposant(nonLogique);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY() + offsetY);

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("Port d'entrée NOT sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println(" Port de sortie NOT sélectionné !");
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

        int x = 10, y = offsetY;
        int w = width, h = height;

        // Triangle NOT
        Path2D triangle = new Path2D.Double();
        triangle.moveTo(x, y);                 // Coin haut gauche
        triangle.lineTo(x + w, y + h / 2);     // Pointe
        triangle.lineTo(x, y + h);             // Coin bas gauche
        triangle.closePath();
        g2.draw(triangle);

        //  Petit cercle à la sortie
        int circleRadius = 6;
        int circleX = x + w + 2;
        int circleY = y + h / 2 - circleRadius / 2;
        g2.drawOval(circleX, circleY, circleRadius, circleRadius);

        //  Entrée
        g2.drawLine(x - 20, y + h / 2, x, y + h / 2);

        //  Sortie
        g2.drawLine(circleX + circleRadius, y + h / 2, circleX + 40, y + h / 2);
    }

    // initialisation par defaut 
    private List<Logique.Principale.State> inputStates = Arrays.asList(Logique.Principale.State.UNKNOWN);  
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
