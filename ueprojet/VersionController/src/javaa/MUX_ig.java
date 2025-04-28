import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MUX_ig extends AbstractComponent {
    private final int width = 40;
    private final int height = 50;

    public MUX_ig() {
        super("MUX", 6, 1);
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocus();
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY());

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port d'entrée MUX sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port de sortie MUX sélectionné !");
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

        // Dessiner la forme du multiplexeur (forme ressemblant à un MUX 2x1 de Logisim)
        int[] xPoints = {x, x + w, x + w, x }; // Côté droit plus petit que le côté gauche
        int[] yPoints = {y, y+9, y + h-9, y + h}; // Forme de type trapèze
        g2.drawPolygon(xPoints, yPoints, 4);
        
        // Entrées
        g2.drawLine(x - 15, y + (int)(0.1 * h), x, y + (int)(0.1 * h));
        g2.drawLine(x - 15, y + (int)(0.25 * h), x, y + (int)(0.25 * h));
        g2.drawLine(x - 15, y + (int)(0.45 * h), x, y + (int)(0.45 * h));
        g2.drawLine(x - 15, y + (int)(0.6 * h), x, y + (int)(0.6 * h));
        g2.drawLine(x - 15, y + (int)(0.75 * h), x, y + (int)(0.75 * h));
        g2.drawLine(x - 15, y + (int)(0.95 * h), x, y + (int)(0.95 * h));

        // Ligne de sélection (pour indiquer le signal de sélection)
        g2.drawLine(x - 20, y + h / 2, x - 40, y + h / 2);
        g2.drawString("S", x - 50, y + h / 2 + 5);  // Label du signal de sélection

        // Sortie
        g2.drawLine(x + w, y + h / 2+1, x + w + 29, y + h / 2);

        // Afficher le texte "MUX" et "2x1" au centre avec un saut de ligne
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics metrics = g2.getFontMetrics();
        
        // "MUX"
        String text1 = "MUX";
        int text1Width = metrics.stringWidth(text1);
        int text1Height = metrics.getHeight();
        int text1X = x + (w - text1Width) / 2;
        int text1Y = y + (h - text1Height) / 2 - text1Height / 2;  // Position "MUX"
        g2.drawString(text1, text1X, text1Y+10);
        
        // "2x1" (sous "MUX")
        String text2 = "6x1";
        int text2Width = metrics.stringWidth(text2);
        int text2Y = text1Y + text1Height; // Placer "2x1" sous "MUX"
        int text2X = x + (w - text2Width) / 2;
        g2.drawString(text2, text2X, text2Y+15);
    }
      
    // initialisation par defaut 
    private java.util.List<State> inputStates = java.util.Arrays.asList(State.UNKNOWN, State.UNKNOWN, State.UNKNOWN, State.UNKNOWN, State.UNKNOWN, State.UNKNOWN) ;
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
