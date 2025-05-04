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
import Logique.wiring.Ground;


public class Ground_ig extends AbstractComponent {
    private final int width = 40;   // Largeur pour le dessin de la masse
    private final int height = 40;  // Hauteur pour le dessin
    private final int offsetY = 15;  // Décalage vertical

    public Ground_ig() {
        // Composant sans entrée et avec 1 sortie
        super("GROUND", 1, 0);
        setOpaque(false);

        // Création du composant logique associé, supposé présent dans Logique.gates
        Ground groundLogic = new Ground("GROUND#" + System.currentTimeMillis(), getX(), getY());
        setComposant(groundLogic);

        // Gestion d'événement sur le port de sortie
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY() + offsetY);

                for (Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("Port de sortie GROUND sélectionné !");
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Activation de l'antialiasing pour un rendu de qualité
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        // Définition des coordonnées de base pour le dessin
        int x = 5;
        int y = offsetY;

        // Première ligne horizontale (ligne de base)
        g2.drawLine(x, y, x + width, y);

        // Dessin du symbole de masse : trois traits horizontaux de largeur décroissante
        int centerX = x + width / 2;
        g2.drawLine(centerX - 10, y + 10, centerX + 10, y + 10);
        g2.drawLine(centerX - 7, y + 15, centerX + 7, y + 15);
        g2.drawLine(centerX - 4, y + 20, centerX + 4, y + 20);

        // Dessin de la ligne de sortie, partant de la gauche du symbole ground
        int outputStartX = x;
        int outputY = y;  // Aligné sur la première ligne horizontale
        int outputEndX = outputStartX - 20;
        g2.drawLine(outputStartX, outputY, outputEndX, outputY);
    }
    
    // Méthode de mise à jour de la sortie graphique (à compléter selon la logique souhaitée)
    public void updateOutput(Logique.Principale.State output) {
        // Par exemple, modifier l'aspect en fonction de l'état.
        System.out.println(" Sortie Ground mise à jour dans la vue : " + output);
        repaint();
    }
}
