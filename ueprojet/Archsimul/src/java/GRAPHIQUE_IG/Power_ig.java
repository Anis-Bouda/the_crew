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

import Logique.gates.*;
import Logique.wiring.Power;

public class Power_ig extends AbstractComponent {
    private final int diameter = 40;  // Taille du cercle
    private final int offsetY = 5;    // Décalage vertical

    public Power_ig() {
        // Composant sans entrée et avec 1 sortie
        super("POWER", 0, 1);
        setOpaque(false);

        // Création du composant logique associé, supposé présent dans Logique.gates
        Power powerLogic = new Power("POWER#" + System.currentTimeMillis(), getX(), getY());
        setComposant(powerLogic);

        // Gestion d'événement sur le port de sortie
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY() + offsetY);

                for (Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("Port de sortie POWER sélectionné !");
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Activation de l'antialiasing pour un meilleur rendu
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(2));

        // Dessin du cercle plein pour représenter la source d'alimentation
        int x = 5;
        int y = offsetY;
        g2.setColor(Color.BLACK);
        g2.fillOval(x, y, diameter, diameter);

        // Dessin du symbole "+" au centre
        g2.setColor(Color.WHITE);
        int centerX = x + diameter / 2;
        int centerY = y + diameter / 2;
        int gap = 6;
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(centerX, y + gap, centerX, y + diameter - gap);
        g2.drawLine(x + gap, centerY, x + diameter - gap, centerY);

        // Dessin de la ligne de sortie, partant du côté droit du cercle
        int outputStartX = x + diameter;
        int outputY = centerY + 4 ;
        int outputEndX = outputStartX + 40;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(outputStartX, outputY, outputEndX, outputY);
    }
    
    // Méthode de mise à jour de la sortie graphique (à compléter selon la logique souhaitée)
    public void updateOutput(Logique.Principale.State output) {
        // Par exemple, on pourrait modifier l'aspect (couleurs, etc.) selon l'état.
        System.out.println(" Sortie Power mise à jour dans la vue : " + output);
        repaint();
    }
}
