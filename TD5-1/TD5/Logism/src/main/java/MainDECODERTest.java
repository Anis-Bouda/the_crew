import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainDECODERTest {
    public static void main(String[] args) {
        // creation de decodeur dans model 
        DECODER model = new DECODER("DECODER1", 100, 100);
        model.inputs.set(0, State.False);  
        model.inputs.set(1, State.True);  
        
        // creation de decodeur dans l'interface graphique 
        DECODER_ig view = new DECODER_ig();
        List<State> inputStates = new ArrayList<>();
        inputStates.add(model.getInput(0));
        inputStates.add(model.getInput(1));
        view.setInputStates(inputStates);

        // creation du controller 
        DECODERController controller = new DECODERController(model, view);
        
        // fenetre swing 
        JFrame frame = new JFrame("Test DECODER 2→4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        // ajouter la vue a la fenetre 
        view.setBounds(30, 30, 200, 120);
        frame.add(view);
        frame.setVisible(true);

        // evaluation 
        controller.evaluateGate();
    }
}
