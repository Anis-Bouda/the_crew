import javax.swing.*;

public class MainLEDTest {
    public static void main(String[] args) {
        // 🔹 Créer le modèle logique
        LED model = new LED("lampe1", 100, 100);
        model.setInputState(State.True);  // essaie aussi avec False ou UNKNOWN

        // 🔹 Créer la vue graphique
        LED_ig view = new LED_ig("lampe1", 200, 200);

        // 🔹 Créer le contrôleur
        LEDController controller = new LEDController(model, view);

        // 🔹 Créer la fenêtre
        JFrame frame = new JFrame("Test Lampe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        // 🔹 Ajouter la vue
        frame.add(view);

        // 🔹 Afficher la vue
        frame.setVisible(true);

        // 🔹 Évaluer et mettre à jour l'affichage
        controller.evaluateGate();
    }
}
