import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainDECODERTest {
    public static void main(String[] args) {
        // creation du modele
        DECODER model = new DECODER("DECODER1", 100, 100);
        model.inputs.set(0, State.False);  
        model.inputs.set(1, State.False);

        
        // creation de encodeur de l'interface graphique 
        DECODER_ig view = new DECODER_ig();
        view.setInputState(0, State.False);
        view.setInputState(1, State.False);

        // creation de controlleur 
        DECODERController controller = new DECODERController(model, view);
        
        // fenetre swing
        JFrame frame = new JFrame("Test DECODEUR 4->2");
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
