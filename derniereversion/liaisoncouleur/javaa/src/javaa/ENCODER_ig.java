import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import plexers.Encodeur;

public class ENCODER_ig extends AbstractComponent2 {
    private final int width = 60;  // Largeur de l'encodeur
    private final int height = 90; // Hauteur de l'encodeur
    
    public ENCODER_ig() {
        super("ENCODER", 4, 2);  // Encoder 4 entrées et 2 sorties
        setOpaque(false);
        
         /*la création de la porte logique pour le liens  */
         Encodeur encodeurlogique = new Encodeur(TOOL_TIP_TEXT_KEY, ALLBITS, ABORT);
         setComposant(encodeurlogique);
         
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)  {
                requestFocus();
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY());

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port d'entrée ENCODER sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port de sortie ENCODER sélectionné !");
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

        int x = 10, y = 4;
        int w = width, h = height;

        // Dessiner la forme de l'encodeur (rectangle)
        g2.drawRect(x, y, w, h);

       // Dessiner les 4 ports d'entrée (gauche)
        int portSpacing = h / 5;
        for (int i = 0; i < 4; i++) {
            int portY = y + (i + 1) * portSpacing;
            g2.drawLine(x - 10, portY, x, portY);
        }

        // Dessiner les 2 ports de sortie (droite)
        int outSpacing = h / 3;
        for (int i = 0; i < 2; i++) {
            int portY = y + (i + 1) * outSpacing;
            g2.drawLine(x + w, portY, x + w + 20, portY);
        }
        
        // Afficher le texte "ENCODER" et "4x2" au centre avec un saut de ligne
        g2.setFont(new Font("Arial", Font.PLAIN, 12)); // Réduire la taille de la police
        FontMetrics metrics = g2.getFontMetrics();

        // "ENCODER"
        String text1 = "ENCODER";
        int text1Width = metrics.stringWidth(text1);
        int text1Height = metrics.getHeight();
        int text1X = x + (w - text1Width) / 2;
        int text1Y = y + (h - text1Height) / 2 - text1Height / 2;  // Position "ENCODER"
        g2.drawString(text1, text1X, text1Y);

        // "4x2" (sous "ENCODER")
        String text2 = "4x2";
        int text2Width = metrics.stringWidth(text2);
        int text2Y = text1Y + text1Height; // Placer "8x1" sous "ENCODER"
        int text2X = x + (w - text2Width) / 2;
        g2.drawString(text2, text2X, text2Y + 15);
    }
    
    // initialisation par defaut 
    private java.util.List<State> inputStates = java.util.Arrays.asList(State.UNKNOWN, State.UNKNOWN, State.UNKNOWN, State.UNKNOWN);
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
            System.out.println("🖥️ Sortie " + i + " mise à jour dans la vue : " + outputs.get(i));
        }}
}
