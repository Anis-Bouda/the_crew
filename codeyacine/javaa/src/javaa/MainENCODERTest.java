import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainENCODERTest {
    public static void main(String[] args) {
        // creation du modele
        ENCODER model = new ENCODER("ENCODER1", 100, 100);
        model.inputs.set(0, State.False);  
        model.inputs.set(1, State.False);
        model.inputs.set(2, State.True);  
        model.inputs.set(3, State.False);

        
        // creation de encodeur de l'interface graphique 
        ENCODER_ig view = new ENCODER_ig();
        view.setInputState(0, State.False);
        view.setInputState(1, State.False);
        view.setInputState(2, State.True);
        view.setInputState(3, State.False);

        // creation de controlleur 
        ENCODEURController controller = new ENCODEURController(model, view);
        
        // fenetre swing
        JFrame frame = new JFrame("Test ENCODEUR 4->2");
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
