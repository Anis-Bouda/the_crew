import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import javax.swing.*;

public class BasculeD_ig extends AbstractComponent6 {
    private final int width = 140;
    private final int height = 40;

    public BasculeD_ig() {
        super("BD", 2, 2);
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY());

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println(" Port d'entrée RAM sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("Port de sortie RAM sélectionné !");
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
        
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        int x = 10, y = 80;
        int w = width-20, h = height;

        // Dessiner un autre rectangle en haut collé au corps de la RAM
        int headerHeight = 80;
        g2.draw(new RoundRectangle2D.Double(x+20, y - headerHeight+10, w-40, headerHeight, 10, 10));
       

        // Signaux de contrôle à l'intérieur du rectangle
        g2.drawString("D", x + 30, y - headerHeight + 30);
        g2.drawString("CLK", x + 30, y - headerHeight + 70);
        g2.drawString("Q", x + 80, y - headerHeight + 35);
     

        // Définir une police plus grande (exemple : Arial, taille 16, en gras)
        g2.setFont(new Font("Arial", Font.BOLD, 16));

        // Dessiner les textes "S" et "R" avec la nouvelle couleur et taille
        g2.drawString("D", x + 55, y - headerHeight + 50);
   
        


     // Dessiner un cercle vide de diamètre 20 centré à (x, y - headerHeight + 65)
        int circleDiameter = 15; // Diamètre du cercle
        int circleX = x+100; // Position horizontale du coin supérieur gauche
        int circleY = y - headerHeight + 65; // Position verticale du coin supérieur gauche

        g2.drawOval(circleX, circleY, circleDiameter, circleDiameter);


        // Dessiner un triangle orienté vers C1 (déplacer vers la gauche)
        int[] xPoints = {x+20 , x+20 , x + 30}; // Position des points horizontaux du triangle
        int[] yPoints = {y - headerHeight +65 , y - headerHeight + 75, y - headerHeight + 70}; // Déplacer vers le haut
        
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        g2.fillPolygon(triangle); // Remplir le triangle avec la couleur noire
        
        // Ajouter trois lignes droites sortantes en haut à gauche 
        int offset = 10; // Espacement entre les lignes
        g2.drawLine(x+20, y - headerHeight+30, x , y - headerHeight+30); // Première ligne droite
        g2.drawLine(x+20, y - headerHeight + 70, x , y - headerHeight + 70); // Entrée 2
        g2.drawLine(x+120, y - headerHeight+30 , x + 100, y - headerHeight+30 ); // Troisième ligne droite
        

    }
    
 // initialisation par defaut 
    private java.util.List<State> inputStates = java.util.Arrays.asList(State.UNKNOWN, State.UNKNOWN);
    private java.util.List<State> outputStates = java.util.Arrays.asList(State.UNKNOWN, State.UNKNOWN);
    
   // Méthode pour définir l'état d'une entrée
    public void setInputState(int index, State s) {
        inputStates.set(index, s);
    }

    // Méthode pour récupérer l'état d'une entrée 
    public State getSelectedInput(int index) {
        return inputStates.get(index);
    }
  
    // Mise à jour de la sortie graphique
    public void updateOutputs(List<State> outputs) {
        this.outputStates = outputs;
        repaint();
        // Affiche les états dans la console
        for (int i = 0; i < outputs.size(); i++) {
            System.out.println(" Sortie " + i + " mise à jour dans la vue : " + outputs.get(i));
        }}
}
