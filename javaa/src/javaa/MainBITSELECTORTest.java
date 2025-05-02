import javax.swing.JFrame;

public class MainBITSELECTORTest {
    public static void main(String[] args) {
        // creation du BITSELECTOR dans modele 
        BITSELECTOR model = new BITSELECTOR("BIT1", 100, 100);
        model.setInputState(0, State.False); 
        model.setInputState(1, State.True);  
        model.setInputState(2, State.False);
        model.setInputState(3, State.False); 
        model.setInputState(4, State.True); 
        model.setInputState(5, State.True); 
        model.setInputState(6, State.False); 
        model.setInputState(7, State.False); 

        // creer le bitselector avec l'interface graphique 
        BITSELECTOR_ig view = new BITSELECTOR_ig();
        for (int i = 0; i < 8; i++) {
            view.setInputState(i, model.getInput(i));
        }

        // creation du controller 
        BITSELECTORController controller = new BITSELECTORController(model, view);

        // fenetre swing 
        JFrame frame = new JFrame("Test BITSELECTOR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        // ajouter la vue a la fenetre
        view.setBounds(30, 30, 100, 100);
        frame.add(view);
        frame.setVisible(true);

        // evaluation de la sortie 
        controller.evaluateGate();
    }
}
