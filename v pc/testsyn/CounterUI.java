
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CounterUI extends JFrame {
    private JLabel counterLabel;
    private JButton clockButton;
    private JButton resetButton;
    private JToggleButton directionButton;

    public CounterUI() {
        setTitle("Simulateur de Compteur");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        counterLabel = new JLabel("Valeur : 0000", SwingConstants.CENTER);
        add(counterLabel);

        clockButton = new JButton("Horloge");
        add(clockButton);

        resetButton = new JButton("Réinitialiser");
        add(resetButton);

        directionButton = new JToggleButton("↑ / ↓");
        add(directionButton);

        setVisible(true);
    }

    public void setCounterLabel(String text) {
        counterLabel.setText(text);
    }

    public void addClockListener(ActionListener listener) {
        clockButton.addActionListener(listener);
    }

    public void addResetListener(ActionListener listener) {
        resetButton.addActionListener(listener);
    }

    public void addDirectionListener(ActionListener listener) {
        directionButton.addActionListener(listener);
    }

    public boolean getDirectionState() {
        return directionButton.isSelected();
    }
}