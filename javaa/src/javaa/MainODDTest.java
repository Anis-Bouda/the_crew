import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.List;

public class MainODDTest {
    public static void main(String[] args) {
        // creation du modeele 
        ODD model = new ODD("ODD1", 100, 100);
        model.setInputState(0, State.True);
        model.setInputState(1, State.False);
        model.setInputState(2, State.True);
        model.setInputState(3, State.False);
        model.setInputState(4, State.False); 

        // creation de odd dans l'interface graphique 
        ODD_ig view = new ODD_ig();
        view.setInputState(0, State.True);
        view.setInputState(1, State.False);
        view.setInputState(2, State.True);
        view.setInputState(3, State.False);
        view.setInputState(4, State.False);

        // creation du controller 
        ODDController controller = new ODDController(model, view);
        
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
