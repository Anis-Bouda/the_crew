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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;


import Logique.Principale.*;
// Import de la porte logique Clock 
import Logique.wiring.Clock;

public class Clock_ig extends AbstractComponent {
    private final int diameter = 35;  // Diamètre du cercle représentant l'horloge
    private final int offsetY = 5;    // Décalage vertical
    private javax.swing.JButton runButton;
    private javax.swing.JButton stopButton;


    public Clock_ig() {
        // Pour une horloge, nous choisissons 0 entrée et 1 sortie
        super("CLOCK", 0, 1);
        setOpaque(false);
        
        runButton = new javax.swing.JButton();
        runButton.setToolTipText("Cliquez ici pour lancé l'horloge");
        stopButton = new javax.swing.JButton();
        stopButton.setToolTipText("Cliquez ici pour arrêter l'horloge");
        
        runButton.setBackground(Color.GREEN);
        stopButton.setBackground(Color.RED);
        
        runButton.setBorderPainted(false);
        stopButton.setBorderPainted(false);

        runButton.setFocusPainted(false);
        stopButton.setFocusPainted(false);
        
        /*garentir que la couleur est visible*/
        runButton.setContentAreaFilled(true);
        stopButton.setContentAreaFilled(true);
        /*garentir que la couleur est visible*/
        runButton.setOpaque(true);
        stopButton.setOpaque(true);

        runButton.setBounds(5, 45,10, 10); // Position relative au composant
        stopButton.setBounds(25, 45,10, 10);

        runButton.setVisible(false);
        stopButton.setVisible(false);

        // Ajout des boutons au composant Clock_ig
        setLayout(null); // Positionnement absolu
        add(runButton);
        add(stopButton);


        /* Création du composant logique associé */
        // On suppose que la classe Clock existe dans Logique.gates
        Clock clockLogique = new Clock("Clock"+System.currentTimeMillis(),getX(),getY(),1000);
        setComposant(clockLogique);
        
        runButton.addActionListener(evt -> {
    ((Clock) getComposant()).start();
        System.out.println("Youpi! Horloge lancée ;)");
        ConnectionManager.updateConnectionsForComponent(this);
        /*Modifier l'état des boutons : Run devient gris et désactivé*/
        runButton.setBackground(Color.GRAY);
        runButton.setEnabled(false);
    
    /*Réactiver et colorier le bouton Stop*/
    stopButton.setBackground(Color.RED);
    stopButton.setEnabled(true);
});

stopButton.addActionListener(evt -> {
    ((Clock) getComposant()).stop();
    System.out.println("Ay Horloge stopée! :(");
    
    /*Modifier l'état des boutons : Stop devient gris et désactivé*/
    stopButton.setBackground(Color.GRAY);
    stopButton.setEnabled(false);
    
    /*Réactiver et colorier le bouton Run*/
    runButton.setBackground(Color.GREEN);
    runButton.setEnabled(true);
});

        // Ajout d'un écouteur de souris pour détecter la sélection du port de sortie
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                runButton.setVisible(true);
                stopButton.setVisible(true);
                repaint();
                java.awt.Point clickPoint = e.getPoint();
                // Ajustement du point cliqué (décalage appliqué)
                clickPoint.translate(getX(), getY() + offsetY);

                // Vérification de la sélection du port de sortie
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("Port de sortie CLOCK sélectionné !");
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Paramétrage pour un rendu antialiasé de qualité
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        // Coordonnées du dessin (positionner le cercle et le texte)
        int x = 5;
        int y = offsetY + 5;
        
        // Dessin d'un cercle représentant l'horloge
        Ellipse2D circle = new Ellipse2D.Double(x, y, diameter, diameter);
        g2.draw(circle);
        
        // Dessin d'une aiguille pour symboliser le mouvement de l'horloge
        // L'aiguille part du centre et va vers le haut
        int centerX = x + diameter / 2;
        int centerY = y + diameter / 2;

        // Aiguille des heures (plus courte)
        double hourAngle = Math.toRadians(30); 
        
        int hourHandLength = diameter / 3;      // Longueur plus courte
        int hourEndX = (int) (centerX + hourHandLength * Math.sin(hourAngle));
        int hourEndY = (int) (centerY - hourHandLength * Math.cos(hourAngle));
        Line2D hourHand = new Line2D.Double(centerX, centerY, hourEndX, hourEndY);

        // Aiguille des minutes (plus longue)
        double minuteAngle = Math.toRadians(120); 
        
        int minuteHandLength = (int) (diameter / 2.0);  // Longueur plus grande
        int minuteEndX = (int) (centerX + minuteHandLength * Math.sin(minuteAngle));
        int minuteEndY = (int) (centerY - minuteHandLength * Math.cos(minuteAngle));
        Line2D minuteHand = new Line2D.Double(centerX, centerY, minuteEndX, minuteEndY);

        // Dessin des aiguilles sur le composant
        g2.draw(hourHand);
        g2.draw(minuteHand);
        // Affichage du label "CLK" à l'intérieur du cercle, centré
        String label = "CLK";
        int stringWidth = g2.getFontMetrics().stringWidth(label);
        int stringHeight = g2.getFontMetrics().getAscent();
        g2.drawString(label, centerX - stringWidth / 2, centerY + stringHeight / 2);
        
        // Dessin de la sortie (trait simple partant du cercle)
        int outputStartX = x + diameter;
        int outputY = centerY;
        int outputEndX = outputStartX + 17 + diameter / 2;
        g2.drawLine(outputStartX, outputY, outputEndX, outputY);
    }
    
    // Pas d'entrées pour l'horloge, donc la liste des états en entrée est vide
    // Une seule sortie, initialisée à UNKNOWN
    private Logique.Principale.State outputState = Logique.Principale.State.UNKNOWN;

    // Méthode pour mettre à jour la sortie dans la vue graphique (par exemple, changer l'aspect en fonction de l'état)
    public void updateOutput(Logique.Principale.State output) {
        this.outputState = output;
        System.out.println(" Sortie Clock mise à jour dans la vue : " + output);
        repaint();
    }
}
