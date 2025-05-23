package GRAPHIQUE_IG;


import Logique.Principale.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import GRAPHIQUE.AbstractComponent;
import Logique.Input_output.Lampe;

public class LED_ig extends AbstractComponent {
    private State state = State.FALSE;  // état visuel de la LED

    public LED_ig(String id, int x, int y) {
        super("LED", 1, 1);             // 1 entrée, 0 sortie
        setBounds(x, y, 40, 40);
        
        /*la création de la porte logique pour le liens  */
         Lampe lampelogique= new Lampe(id, x, y);
         setComposant(lampelogique);
    }

    /** 
     * Appelée automatiquement par Circuit.simulate après composant.evaluate()
     */
    @Override
    public void updateOutputState() {
        //  On lit l'état de l'entrée (ou UNKNOWN si pas de connexion)
        State in = getInputStates().isEmpty()
                   ? State.UNKNOWN
                   : getInputStates().get(0);

        //  Si TRUE → LED allumée, sinon éteinte
        this.state = (in == State.TRUE) ? State.TRUE : State.FALSE;

        //  On peut aussi informer la partie logique si nécessaire
        ((Lampe)getComposant()).addInput(in);

        //  Déclencher le repaint pour redessiner le cercle
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Cercle rouge si TRUE, gris sinon
        g2.setColor(state == State.TRUE ? Color.RED : Color.GRAY);
        g2.fillOval(5, 5, getWidth() - 10, getHeight() - 10);
        // Contour noir
        g2.setColor(Color.BLACK);
        g2.drawOval(5, 5, getWidth() - 10, getHeight() - 10);
    }
}
