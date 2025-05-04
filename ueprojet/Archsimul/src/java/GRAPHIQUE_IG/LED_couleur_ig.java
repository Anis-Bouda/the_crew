package GRAPHIQUE_IG;

import Logique.Principale.State;
import Logique.Input_output.Lampe;
import GRAPHIQUE.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Classe graphique représentant une lampe (LED) qui change de couleur
 * en fonction de l'état logique :
 * - TRUE    → vert
 * - FALSE   → bleu
 * - ERROR   → rouge
 * - UNKNOWN → gris
 */
public class LED_couleur_ig extends AbstractComponent {
    private State state = State.UNKNOWN;  // État visuel actuel de la LED

    public LED_couleur_ig(String id, int x, int y) {
        super("Lampe_Couleur", 1, 1);  // 1 entrée, 1 sortie
        setBounds(x, y, 40, 40);       // Position et taille
        Lampe lampeLogique = new Lampe(id, x, y);
        setComposant(lampeLogique);
    }

    @Override
    public void updateOutputState() {
        State in = getInputStates().isEmpty()
                   ? State.UNKNOWN
                   : getInputStates().get(0);

        this.state = in;
        ((Lampe)getComposant()).addInput(in);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Choisir la couleur selon l’état
        Color color;
        switch (state) {
            case TRUE:
                color = Color.GREEN;
                break;
            case FALSE:
                color = Color.BLUE;
                break;
            case ERROR:
                color = Color.RED;
                break;
            default:
                color = Color.GRAY;
        }

        g2.setColor(color);
        g2.fillOval(5, 5, getWidth() - 10, getHeight() - 10);
        g2.setColor(Color.BLACK);
        g2.drawOval(5, 5, getWidth() - 10, getHeight() - 10);
    }
}
