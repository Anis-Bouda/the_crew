import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.List;

public class MainEVENTest {
    public static void main(String[] args) {
        // creation du modele
        int n = 5;
        EVEN model = new EVEN("EVEN1", n, 100, 100);
        model.setInputState(0, State.True);
        model.setInputState(1, State.False);
        model.setInputState(2, State.True);
        model.setInputState(3, State.False);
        model.setInputState(4, State.True);  

        // creation de even dans l'interface graphique 
        EVEN_ig view = new EVEN_ig();
        List<State> inputStates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            inputStates.add(model.getInput(i));
        }
        view.setInputStates(inputStates);

        // creation du controller 
        EVENController controller = new EVENController(model, view);
        
        // fenetre swing 
        JFrame frame = new JFrame("Test EVEN Parity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        // ajouter la vue a la fenetre 
        view.setBounds(30, 30, 150, 100);
        frame.add(view);
        frame.setVisible(true);

        // evaluation 
        controller.evaluateGate();
    }
}
