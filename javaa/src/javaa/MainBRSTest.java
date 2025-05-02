import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainBRSTest {
    public static void main(String[] args) {
        // creation du modele
        BRS model = new BRS("BRS1", 100, 100);
        model.inputs.set(0, State.False);  
        model.inputs.set(1, State.True);
        
        // creation de encodeur de l'interface graphique 
        BRS_ig view = new BRS_ig();
        view.setInputState(0, State.False);
        view.setInputState(1, State.True);

        // creation de controlleur 
        BRSController controller = new BRSController(model, view);
        
        // fenetre swing
        JFrame frame = new JFrame("Test Bascule RS");
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
