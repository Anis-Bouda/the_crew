package GRAPHIQUE_IG;
import GRAPHIQUE.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Taskbar.State;

import Logique.Input_output.Lampe;

public class LED_ig extends AbstractComponent {
    // L'état courant de la LED
    private State state;

    // Constructeur : LED avec 1 entrée et 0 sortie
    public LED_ig(String id, int x, int y) {
        super("LED", 1, 0);  // 1 port d'entrée, 0 port de sortie
        setBounds(x, y, 40, 40); // Taille de la LED
        this.state = State.INDETERMINATE; // État initial
    
         /*la création de la porte logique pour le liens  */
         Lampe lampelogique= new Lampe(id, x, y);
         setComposant(lampelogique);
    
    }

    /**
     * Méthode d'évaluation de la LED.
     * On évalue l'état de l'entrée et on définit l'état de la LED :
     * - Si l'entrée est NORMAL, la LED s'allume (rouge).
     * - Sinon, la LED est éteinte (gris).
     */
    public void evaluate(State inputState) {
        if (inputState == State.NORMAL) {
            this.state = State.NORMAL;
        } else {
            this.state = State.OFF;
        }
        repaint(); // Mettre à jour l'affichage
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Dessiner les ports (défini dans AbstractComponent)
        Graphics2D g2d = (Graphics2D) g;

        // Choisir la couleur en fonction de l'état de la LED
        if (this.state == State.NORMAL) {
            g2d.setColor(Color.RED);  // LED allumée
        } else {
            g2d.setColor(Color.GRAY); // LED éteinte
        }

        // Dessiner la LED comme un cercle
        g2d.fillOval(5, 5, getWidth() - 10, getHeight() - 10);

        // Dessiner le port d'entrée en rouge
        if (!inputPorts.isEmpty()) {
            Port inputPort = inputPorts.get(0);
            g2d.setColor(Color.RED);
            g2d.fillOval(inputPort.getPosition().x - 3, inputPort.getPosition().y - 3, 6, 6);
        }

        // Optionnel : Dessiner un contour noir autour de la LED
        g2d.setColor(Color.BLACK);
        g2d.drawOval(5, 5, getWidth() - 10, getHeight() - 10);
    }
}
