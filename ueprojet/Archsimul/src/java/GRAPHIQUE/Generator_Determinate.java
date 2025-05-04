package GRAPHIQUE;
import GRAPHIQUE_IG.*;
import Logique.Principale.*;
import Logique.Input_output.*;
import java.awt.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Taskbar.State;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Generator_Determinate extends AbstractComponent {

    private State currentState = State.OFF; // OFF par défaut
    private final int inputThreshold = 10;

    public Generator_Determinate() {
        super("GENERATOR", 0, 1); // 0 entrée, 1 sortie
        setOpaque(false);

        /*Composant logique associé*/
        determinateGenerator genLogique = new determinateGenerator("GENERATOR#", getX(), getY(), StateMapper.toLogicState(currentState));
        setComposant(genLogique);

        if (!outputPorts.isEmpty()) {
            outputPorts.get(0).setState(currentState);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    toggleState();
                } else {
                    Point clickPoint = e.getPoint();
                    for (Port port : inputPorts) {
                        if (port.getPosition().distance(clickPoint) <= inputThreshold) {
                            triggerConnection();
                            break;
                        }
                    }
                }
            }
        });
    }

    public void toggleState() {
        switch (currentState) {
            case NORMAL -> currentState = State.OFF;
            case OFF -> currentState = State.ERROR;
            case ERROR -> currentState = State.INDETERMINATE;
            case INDETERMINATE -> currentState = State.NORMAL;
        }

        Composant c = getComposant();
        if (c instanceof determinateGenerator dg) {
            dg.setInputs(0, StateMapper.toLogicState(currentState));
            dg.evaluate();
        }

        if (!outputPorts.isEmpty()) {
            outputPorts.get(0).setState(currentState);
        }

        repaint();
    }

    public void triggerConnection() {
        System.out.println("Création d'une ligne depuis le port d'entrée !");
    }

    public State getOutputState() {
        if (!outputPorts.isEmpty()) {
            return outputPorts.get(0).getState();
        }
        return State.INDETERMINATE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);
        g2.drawRect(10, 10, 80, 50);

        // Couleur de fond selon l'état
        switch (currentState) {
            case NORMAL -> g2.setColor(new Color(0, 200, 0, 100)); // Vert
            case OFF -> g2.setColor(new Color(0, 0, 200, 100));     // Bleu
            case ERROR -> g2.setColor(new Color(200, 0, 0, 100));   // Rouge
            case INDETERMINATE -> g2.setColor(new Color(150, 150, 150, 100)); // Gris
        }

        g2.fillRect(10, 10, 80, 50);

        // Affichage du texte
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.setColor(Color.BLACK);
        String text;
        switch (currentState) {
            case NORMAL:
                text="ON";
                break;
            case OFF:
                text="OFF";
                break;
            case ERROR:
                text="ERR";
                break;
            default:
                text="UNK";
                break;
        };

        int textWidth = g2.getFontMetrics().stringWidth(text);
        int textX = 10 + (80 - textWidth) / 2;
        int textY = 10 + (50 + g2.getFontMetrics().getAscent()) / 2 - 5;
        g2.drawString(text, textX, textY);
    }
}


