import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.nio.Buffer;

import javax.swing.*;
import gates.*;

public class BUFFER_ig extends AbstractComponent {
    private final int width = 60;  // Largeur du triangle
    private final int height = 60; // Hauteur du triangle

    public BUFFER_ig() {
        super("BUFFER", 1, 1); // 1 entrée, 1 sortie
        setOpaque(false); // Rendre le fond transparent
         /*la création de la porte logique pour le liens  */
        gates.Buffer bufferLogique= new gates.Buffer("BUFFER"+System.currentTimeMillis(),getX(),getY());
        setComposant(bufferLogique);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY()); // ✅ Convertir en coordonnées globales

                // Vérification de la sélection des ports d'entrée et de sortie
                for (java.awt.Point port : getInputPorts()) { 
                    if (port.distance(clickPoint) < 10) { 
                        System.out.println("✅ Port d'entrée BUFFER sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) { 
                    if (port.distance(clickPoint) < 10) { 
                        System.out.println("Port de sortie BUFFER sélectionné !");
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
        
        // Définir les couleurs et l'épaisseur des traits
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        int x = 10, y = 0;
        int w = width, h = height;

        // 🔺 Dessiner le triangle de la porte BUFFER
        Path2D triangle = new Path2D.Double();
        triangle.moveTo(x, y);                 // Coin supérieur gauche
        triangle.lineTo(x + w, y + h / 2);      // Pointe droite
        triangle.lineTo(x, y + h);              // Coin inférieur gauche
        triangle.closePath();
        g2.draw(triangle);

        // ➡️ Entrée (ligne à gauche)
        g2.drawLine(x - 20, y + h / 2, x, y + h / 2);

        // ➡️ Sortie (ligne à droite)
        g2.drawLine(x + w, y + h / 2, x + w + 40, y + h / 2);

        // Définir les ports de la porte
    }
    
 // initialisation par defaut 
    private java.util.List<State> inputStates = java.util.Arrays.asList(State.UNKNOWN) ;
    private State outputState = State.UNKNOWN;

    // mettre a jour l'etat 
    public void setInputState(int index, State s) {
        inputStates.set(index, s);
    }

    // recuperation de l'etat 
    public State getSelectedInput(int index) {
        return inputStates.get(index);
    }

    // mettre a jour la sortie dans la sortie graphique 
    public void updateOutput(State output) {
        this.outputState = output;
        System.out.println("🖥️ Sortie mise à jour dans la vue : " + output);
        repaint(); 
    }
}
