import javax.swing.JFrame;

public class MainROMTest {
    public static void main(String[] args) {
    	// creer une ROM
    	 State[][] romData = {
    	            {State.False, State.False, State.False, State.False}, // Adresse 0
    	            {State.True, State.False, State.False, State.False},  // Adresse 1
    	            {State.False, State.True, State.False, State.False},  // Adresse 2
    	            {State.True, State.True, State.False, State.False},   // Adresse 3
    	            {State.False, State.False, State.True, State.False},  // Adresse 4
    	            {State.True, State.False, State.True, State.False},   // Adresse 5
    	            {State.False, State.True, State.True, State.False},   // Adresse 6
    	            {State.True, State.True, State.True, State.False}     // Adresse 7
    	        };

        // 1. Création du modèle (RAM avec un nom et position x, y)
        ROM model = new ROM("ROM1", 100, 100, romData);

        // Initialisation de quelques entrées pour le test
        model.inputs.set(0, State.False);
        model.inputs.set(1, State.False);
        model.inputs.set(2, State.False);

        // 2. Création de la vue graphique
        ROM_ig view = new ROM_ig();
        view.setInputState(0, State.False);
        view.setInputState(1, State.False);
        view.setInputState(2, State.False);

        // 3. Création du contrôleur
        ROMController controller = new ROMController(model, view);

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
