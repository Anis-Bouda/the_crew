import javax.swing.JFrame;

public class MainNOT_ETTest {
    public static void main(String[] args) {
        // Création des modèles
        NOT not1_model = new NOT("NOT1", 50, 50);
        NOT not2_model = new NOT("NOT2", 50, 300);
        ET et_model = new ET("ET1", 400, 200);

        // Initialiser les entrées des NOT
        not1_model.inputs.set(0, State.True);
        not2_model.inputs.set(0, State.False);

        not1_model.outputs.set(0, State.UNKNOWN);
        not2_model.outputs.set(0, State.UNKNOWN);

        et_model.inputs.set(0, State.UNKNOWN);
        et_model.inputs.set(1, State.UNKNOWN);
        et_model.outputs.set(0, State.UNKNOWN);

        // Création des vues
        NOT_ig not1_view = new NOT_ig();
        NOT_ig not2_view = new NOT_ig();
        ET_ig et_view = new ET_ig();

        // Définir les entrées dans les vues
        not1_view.setInputState(0, State.True);
        not2_view.setInputState(0, State.False);

        // Création des contrôleurs
        NOTController not1_ctrl = new NOTController(not1_model, not1_view);
        NOTController not2_ctrl = new NOTController(not2_model, not2_view);
        ETController et_ctrl = new ETController(et_model, et_view);

        // Évaluer les NOT
        not1_ctrl.evaluateGate();
        not2_ctrl.evaluateGate();

        // Transférer leurs sorties comme entrées de la porte ET
        et_model.inputs.set(0, not1_model.getOutput(0));
        et_model.inputs.set(1, not2_model.getOutput(0));

        et_view.setInputState(0, not1_model.getOutput(0));
        et_view.setInputState(1, not2_model.getOutput(0));

        // Évaluer la porte ET
        et_ctrl.evaluateGate();

        // Création de la fenêtre Swing
        JFrame frame = new JFrame("Simulation : NOT + NOT -> ET");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLayout(null);

        // Positionnement des vues
        not1_view.setBounds(50, 50, 100, 100);
        not2_view.setBounds(50, 300, 100, 100);
        et_view.setBounds(400, 200, 100, 100);

        // Ajout des vues à la fenêtre
        frame.add(not1_view);
        frame.add(not2_view);
        frame.add(et_view);

        // ✅ Initialisation du circuit logique et du ConnectionManager
        Circuit circuit = Circuit.getInstance(); // ou new Circuit() si pas singleton
        ConnectionManager.setCircuit(circuit);
        ConnectionManager.setLayeredPane(frame.getLayeredPane());

        // Affichage de la fenêtre
        frame.setVisible(true);
    }
}
