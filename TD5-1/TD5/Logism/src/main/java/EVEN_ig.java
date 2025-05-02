import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

public class EVEN_ig extends AbstractComponent {
    private final int width = 35;
    private final int height = 42;
    private final int offsetY = 5;

    // Liste pour stocker l'état des entrées
    private java.util.List<State> inputStates;
    
    public EVEN_ig() {
        super("EVEN PARITY", 5, 1);
        setOpaque(false);

        // Initialiser la liste d'entrées 
        inputStates = new ArrayList<>();
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY() + offsetY);

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port d'entrée EVEN PARITY sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("✅ Port de sortie EVEN PARITY sélectionné !");
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
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        int x = 8, y = 4 + offsetY;
        int w = width, h = height;

        g2.draw(new Arc2D.Double(x, y, w, h, 0, 360, Arc2D.OPEN));

        g2.drawLine(x - 15, y + (int)(0.2 * h), x, y + (int)(0.2 * h));
        g2.drawLine(x - 15, y + (int)(0.5 * h), x, y + (int)(0.5 * h));
        g2.drawLine(x - 15, y + (int)(0.8 * h), x, y + (int)(0.8 * h));

        g2.drawLine(x + w, y + h / 2, x + w + 20, y + h / 2);
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics metrics = g2.getFontMetrics();
        String text1 = "2k+1";
        int text1Width = metrics.stringWidth(text1);
        int text1Height = metrics.getHeight();
        int text1X = x + (w - text1Width) / 2;
        int text1Y = y + (h - text1Height) / 2 - text1Height / 2;  // Position "DEMUX"
        g2.drawString(text1, text1X, text1Y+10);
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
    public void updateOutput(State output) {
        System.out.println("🖥️ Sortie mise à jour dans la vue : " + output);
        repaint();
    }
    
    public void setInputStates(List<State> states) {
        this.inputStates.clear();
        this.inputStates.addAll(states);
    }
}
