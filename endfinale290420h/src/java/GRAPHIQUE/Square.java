package GRAPHIQUE ;
import javax.swing.*;
import java.awt.*;

public class Square extends JPanel {

    private static final int SQUARE_SIZE = 50;  // Taille du carré

    public Square() {
        // Définir les propriétés du panneau
        setBackground(Color.WHITE);  // Fond blanc
        setPreferredSize(new Dimension(40, 40));  // Taille de la zone d'affichage
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // S'assurer que le composant est bien redessiné

        // Récupérer le graphique 2D pour un rendu plus précis
        Graphics2D g2d = (Graphics2D) g;

        // Définir la couleur de la bordure du carré
        g2d.setColor(Color.RED);  // Bordure rouge

        // Dessiner un carré avec une bordure rouge (intérieur transparent)
        int squareX = (getWidth() - SQUARE_SIZE) / 2;  // Centrer horizontalement
        int squareY = (getHeight() - SQUARE_SIZE) / 2;  // Centrer verticalement
        
        
        
        g2d.drawRect(squareX, squareY, SQUARE_SIZE, SQUARE_SIZE);  // Dessiner uniquement la bordure
    }
}
