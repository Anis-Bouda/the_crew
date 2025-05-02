import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainBJKTest {
    public static void main(String[] args) {
        // creation du modele
        BJK model = new BJK("BJK1", 100, 100);
        model.inputs.set(0, State.False);  
        model.inputs.set(1, State.True);
        model.inputs.set(2, State.True);
        
        // creation de encodeur de l'interface graphique 
        BJK_ig view = new BJK_ig();
        view.setInputState(0, State.False);
        view.setInputState(1, State.True);
        view.setInputState(2, State.True);

        // creation de controlleur 
        BJKController controller = new BJKController(model, view);
        
        // fenetre swing
        JFrame frame = new JFrame("Test Bascule JK");
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
