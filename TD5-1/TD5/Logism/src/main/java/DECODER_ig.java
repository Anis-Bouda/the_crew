import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class DECODER_ig extends AbstractComponent {
    private final int width = 50;  // Largeur légèrement réduite
    private final int height = 50; // Maintenir la hauteur compacte

    // Liste pour stocker l'état des entrées
    private java.util.List<State> inputStates;
    // liste pour stocker les sorties 
    private java.util.List<State> outputStates;
    
    public DECODER_ig() {
        super("DECODER", 1, 4);  // Changer le nombre de ports d'entrée et de sortie
        setOpaque(false);

        inputStates = new ArrayList<>();
        this.outputStates = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
        	// par defaut 
            outputStates.add(State.UNKNOWN); 
        }
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocus();
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY());

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port d'entrée DECODER sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port de sortie DECODER sélectionné !");
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

        // Dessiner la forme du décodeur (forme ressemblant à un DECODER 1x4 de Logisim)
        int[] xPoints = {x, x + w, x + w, x}; // Côté droit plus petit que le côté gauche
        int[] yPoints = {y, y + 6, y + h - 6, y + h}; // Forme de type trapèze plus petit
        g2.drawPolygon(xPoints, yPoints, 4);

        // Entrée
        g2.drawLine(x - 15, y + h / 2, x, y + h / 2);  // Entrée 1 (au centre de la forme)

        // Lignes de sorties
        g2.drawLine(x + w, y + 10, x + w + 15, y + 10);   // Sortie 1
        g2.drawLine(x + w, y + 20, x + w + 15, y + 20);   // Sortie 2
        g2.drawLine(x + w, y + 30, x + w + 15, y + 30);   // Sortie 3
        g2.drawLine(x + w, y + 40, x + w + 15, y + 40);   // Sortie 4

        // Ligne de sélection (pour indiquer le signal de sélection)
        g2.drawLine(x - 15, y + h / 2, x - 30, y + h / 2);
        g2.drawString("S", x - 35, y + h / 2 + 5);  // Label du signal de sélection

        // Afficher le texte "DECODER" et "1x4" au centre avec un saut de ligne
        g2.setFont(new Font("Arial", Font.PLAIN, 10)); // Réduire la taille de la police
        FontMetrics metrics = g2.getFontMetrics();

        // "DECODER"
        String text1 = "DECODER";
        int text1Width = metrics.stringWidth(text1);
        int text1Height = metrics.getHeight();
        int text1X = x + (w - text1Width) / 2;
        int text1Y = y + (h - text1Height) / 2 - text1Height / 2;  // Position "DECODER"
        g2.drawString(text1, text1X, text1Y + 5);

        // "1x4" (sous "DECODER")
        String text2 = "1x4";
        int text2Width = metrics.stringWidth(text2);
        int text2Y = text1Y + text1Height; // Placer "1x4" sous "DECODER"
        int text2X = x + (w - text2Width) / 2;
        g2.drawString(text2, text2X, text2Y + 10);
    }
    
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
    
    public void setInputStates(List<State> states) {
        this.inputStates.clear();
        this.inputStates.addAll(states);
    }
}
