package GRAPHIQUE_IG;

import GRAPHIQUE.*;
import GRAPHIQUE.Point;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

import Logique.Memory.BasculeT;

public class BasculeT_ig extends AbstractComponent6 {
    private final int width = 140;
    private final int height = 40;

    public BasculeT_ig() {
        super("BT", 2, 2);
        setOpaque(false);

         /*la création de la porte logique */
        BasculeT Bst = new BasculeT("BST#"+System.currentTimeMillis(), getX(), getY());
        setComposant(Bst);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY());

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("Port d'entrée Bascule T sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("Port de sortie Bascule T sélectionné !");
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
        int w = width - 20;
        int headerHeight = 80;

        g2.draw(new RoundRectangle2D.Double(x + 20, y - headerHeight + 10, w - 40, headerHeight, 10, 10));

        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("T", x + 30, y - headerHeight + 30);
        g2.drawString("CLK", x + 30, y - headerHeight + 70);
        g2.drawString("Q", x + 80, y - headerHeight + 35);
        g2.drawString("T", x + 55, y - headerHeight + 50);

        int circleDiameter = 15;
        int circleX = x + 100;
        int circleY = y - headerHeight + 65;
        g2.drawOval(circleX, circleY, circleDiameter, circleDiameter);

        int[] xPoints = {x + 20, x + 20, x + 30};
        int[] yPoints = {y - headerHeight + 65, y - headerHeight + 75, y - headerHeight + 70};
        g2.fillPolygon(new Polygon(xPoints, yPoints, 3));

        g2.drawLine(x + 20, y - headerHeight + 30, x, y - headerHeight + 30);
        g2.drawLine(x + 20, y - headerHeight + 70, x, y - headerHeight + 70);
        g2.drawLine(x + 120, y - headerHeight + 30, x + 100, y - headerHeight + 30);
    }

    // initialisation par defaut 
    private java.util.List<Logique.Principale.State> inputStates = java.util.Arrays.asList(Logique.Principale.State.UNKNOWN, Logique.Principale.State.UNKNOWN);
    private java.util.List<Logique.Principale.State> outputStates = java.util.Arrays.asList(Logique.Principale.State.UNKNOWN, Logique.Principale.State.UNKNOWN);
    
   // Méthode pour définir l'état d'une entrée
    public void setInputState(int index, Logique.Principale.State s) {
        inputStates.set(index, s);
    }

    // Méthode pour récupérer l'état d'une entrée 
    public Logique.Principale.State getSelectedInput(int index) {
        return inputStates.get(index);
    }
  
    // Mise à jour de la sortie graphique
    public void updateOutputs(List<Logique.Principale.State> outputs) {
        this.outputStates = outputs;
        repaint();
        // Affiche les états dans la console
        for (int i = 0; i < outputs.size(); i++) {
            System.out.println(" Sortie " + i + " mise à jour dans la vue : " + outputs.get(i));
        }}
}
