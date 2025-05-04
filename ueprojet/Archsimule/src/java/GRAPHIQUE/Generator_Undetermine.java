package GRAPHIQUE ;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Taskbar.State;

public class Generator_Undetermine extends AbstractComponent {
    private State currentState = State.INDETERMINATE;

    public Generator_Undetermine() {
        super("GEN_UNDEF", 0, 1); // 0 entrée, 1 sortie
        setOpaque(false);
        updateOutputState();
    }

    // Permet au code externe (comme le main) de modifier l’état du générateur
    public void setState(State newState) {
        this.currentState = newState;
        updateOutputState();
        repaint();
    }

    public State getOutputState() {
        if (!outputPorts.isEmpty()) {
            return outputPorts.get(0).getState();
        }
        return State.INDETERMINATE;
    }
    /* 
    private void updateOutputState() {
        if (!outputPorts.isEmpty()) {
            outputPorts.get(0).setState(currentState);
        }
    }
    */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Couleur de fond en fonction de l’état
        Color fillColor;
        switch (currentState) {
            case NORMAL:
                fillColor = new Color(0, 200, 0, 100); // vert
                break;
            case OFF:
                fillColor = new Color(200, 0, 0, 100); // rouge
                break;
            case ERROR:
                fillColor = new Color(255, 140, 0, 100); // orange
                break;
            default:
                fillColor = new Color(150, 150, 150, 100); // gris pour indéterminé
                break;
        }

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(10, 10, 100, 50);
        g2.setColor(fillColor);
        g2.fillRect(10, 10, 100, 50);

        // Afficher le texte de l’état
        g2.setFont(new Font("Arial", Font.BOLD, 10));
        g2.setColor(Color.BLACK);
        String text = currentState.toString();
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int textX = 10 + (80 - textWidth) / 2;
        int textY = 10 + (50 + g2.getFontMetrics().getAscent()) / 2 - 5;
        g2.drawString(text, textX, textY);
    }
}
