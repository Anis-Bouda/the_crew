import javax.swing.JFrame;

public class MainRAMTest {
    public static void main(String[] args) {
        // 1. Création du modèle (RAM avec un nom et position x, y)
        RAM model = new RAM("RAM1", 100, 100);

        // Initialisation de quelques entrées pour le test
        model.inputs.set(0, State.False);
        model.inputs.set(1, State.False);
        model.inputs.set(2, State.False);
        model.inputs.set(3, State.True);
        model.inputs.set(4, State.False);
        model.inputs.set(5, State.True);
        model.inputs.set(6, State.True);

        // 2. Création de la vue graphique
        RAM_ig view = new RAM_ig();
        view.setInputState(0, State.False);
        view.setInputState(0, State.False);
        view.setInputState(0, State.False);
        view.setInputState(0, State.True);
        view.setInputState(0, State.False);
        view.setInputState(0, State.True);
        view.setInputState(0, State.True);

        // 3. Création du contrôleur
        RAMController controller = new RAMController(model, view);

        // 4. Fenêtre Swing
        JFrame frame = new JFrame("Test RAM 8x8");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400); // tu peux ajuster selon le besoin
        frame.setLayout(null);

        // 5. Positionnement et ajout de la vue
        view.setBounds(30, 30, 300, 300); // x, y, largeur, hauteur
        frame.add(view);
        frame.setVisible(true);

        // 6. Évaluation initiale de la RAM
        controller.evaluateGate();
        model.display();
}}
