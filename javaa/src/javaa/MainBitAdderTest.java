import javax.swing.*;
import java.util.*;

public class MainBitAdderTest {
    public static void main(String[] args) {
        // un mot de 4 bits 
    	// on veut affiche le bit positionner a l'index = 2
        BitAdder model = new BitAdder("BA1", 100, 100);
        model.setInputState(0, State.True);
        model.setInputState(1, State.True);
        model.setInputState(2, State.False);

        // creer le BitFinder avec l'interface graphique 
        BitAdder_ig view = new BitAdder_ig();
        view.setInputState(0, State.True);
        view.setInputState(1, State.True);
        view.setInputState(2, State.False);
   
        // creation du controller
        BitAdderController controller = new BitAdderController(model, view);

        // fenetre swing 
        JFrame frame = new JFrame("Test BitAdder");
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
