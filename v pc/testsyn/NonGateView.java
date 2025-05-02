import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NonGateView extends JFrame {
    private JComboBox<String> inputSelector;
    private JLabel outputLabel;
    private JButton evaluateButton;

    public NonGateView() {
        setTitle("Porte NON (NOT Gate)");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // Ajout des composants
        add(new JLabel("Entrée:"));
        inputSelector = new JComboBox<>(new String[]{"UNKNOWN","ERROR", "TRUE", "FALSE"});
        add(inputSelector);

        add(new JLabel("Sortie:"));
        outputLabel = new JLabel("UNKNOWN");
        add(outputLabel);

        evaluateButton = new JButton("Évaluer");
        add(evaluateButton);
    }

    public State getSelectedInput() {
        String selected = (String) inputSelector.getSelectedItem();
        switch (selected) {
            case "TRUE":
                return State.TRUE;
            case "FALSE":
                return State.FALSE;
            default:
                return State.UNKNOWN;
        }
    }

    public void updateOutput(State output) {
        outputLabel.setText(output.toString());
    }

    public void addEvaluateListener(ActionListener listener) {
        evaluateButton.addActionListener(listener);
    }
}