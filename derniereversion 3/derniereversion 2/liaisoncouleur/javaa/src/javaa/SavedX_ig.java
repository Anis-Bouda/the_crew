
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Arrays;

public class SavedX_ig extends AbstractComponent {

    private final int width = 50;
    private final int height = 60;

    private List<State> inputStates = Arrays.asList(State.UNKNOWN);
    private State outputState = State.UNKNOWN;

    public SavedX_ig(String in, String out) {
        super("saved_x", 1, 1);
        setOpaque(false);

        SavedX savedX = new SavedX("saved_x" + System.currentTimeMillis(), getX(), getY(), in, out);
        setComposant(savedX);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY());

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("Port d'entrée SavedX sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println("Port de sortie SavedX sélectionné !");
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

        int x = 10, y = 0;
        int w = width, h = height;

        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, w, h);

        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, w, h);

        g2.drawLine(x - 20, y + h / 2, x, y + h / 2); // Entrée
        g2.drawLine(x + w, y + h / 2, x + w + 40, y + h / 2); // Sortie
    }

    public void setInputState(int index, State s) {
        inputStates.set(index, s);
    }

    public State getSelectedInput(int index) {
        return inputStates.get(index);
    }

    public void updateOutput(State output) {
        this.outputState = output;
        System.out.println("🖥️ Sortie mise à jour dans la vue : " + output);
        repaint();
    }
}
