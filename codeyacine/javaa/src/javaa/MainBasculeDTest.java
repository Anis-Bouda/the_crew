import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainBasculeDTest {
    public static void main(String[] args) {
        // creation du modele
        BasculeD model = new BasculeD("BD1", 100, 100);
        model.inputs.set(0, State.True);  
        model.inputs.set(1, State.True);
        
        // creation de encodeur de l'interface graphique 
        BasculeD_ig view = new BasculeD_ig();
        view.setInputState(0, State.True);
        view.setInputState(1, State.True);

        // creation de controlleur 
        BasculeDController controller = new BasculeDController(model, view);
        
        // fenetre swing
        JFrame frame = new JFrame("Test Bascule D");
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
