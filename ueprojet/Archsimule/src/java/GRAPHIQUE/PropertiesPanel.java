package GRAPHIQUE ;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PropertiesPanel extends JPanel {
    private JTextField xField, yField, componentField; // Champs pour stocker X, Y et le composant

    public PropertiesPanel() {
        setLayout(new GridLayout(3, 2)); // 3 lignes, 2 colonnes
        setBorder(BorderFactory.createTitledBorder(" Propriétés"));

        //  Labels et champs pour stocker les informations
        add(new JLabel("X :"));
        xField = new JTextField();
        xField.setEditable(false); // Empêche la modification manuelle
        add(xField);

        add(new JLabel("Y :"));
        yField = new JTextField();
        yField.setEditable(false);
        add(yField);

        add(new JLabel("Composant :"));
        componentField = new JTextField();
        componentField.setEditable(false);
        add(componentField);
    }

    //  Méthode pour mettre à jour les valeurs des champs
    public void updateProperties(int x, int y, String componentName) {
        xField.setText(String.valueOf(x));
        yField.setText(String.valueOf(y));
        componentField.setText(componentName);
    }
}
