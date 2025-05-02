import javax.swing.*;
import java.util.*;

public class MainDividerTest {
    public static void main(String[] args) {
        // un mot de 4 bits 
    	// on veut affiche le bit positionner a l'index = 2
        Divider model = new Divider("D1", 100, 100);
        model.setInputState(0, State.True);
        model.setInputState(1, State.True);
        model.setInputState(2, State.True);
        model.setInputState(3, State.True);

        // creer le BitFinder avec l'interface graphique 
        Adder_ig view = new Adder_ig();
        view.setInputState(0, State.True);
        view.setInputState(1, State.True);
        view.setInputState(2, State.True);
        view.setInputState(3, State.True);
   
        // creation du controller
        DividerController controller = new DividerController(model, view);

        // fenetre swing 
        JFrame frame = new JFrame("Test Divider");
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
