import javax.swing.*;
import java.util.*;

public class MainBitFinderTest {
    public static void main(String[] args) {
        // un mot de 4 bits 
    	// on veut affiche le bit positionner a l'index = 2
        BitFinder model = new BitFinder("BF1", 0, 100, 100);
        model.setInputState(0, State.True);
        model.setInputState(1, State.False);
        model.setInputState(2, State.True);
        model.setInputState(3, State.False);
        model.setInputState(4, State.True); 

        // creer le BitFinder avec l'interface graphique 
        BitFinder_ig view = new BitFinder_ig();
        view.setInputState(0, State.True);
        view.setInputState(1, State.False);
        view.setInputState(2, State.True);
        view.setInputState(3, State.False);
        view.setInputState(4, State.True);

        // creation du controller
        BitFinderController controller = new BitFinderController(model, view);

        // fenetre swing 
        JFrame frame = new JFrame("Test BitFinder");
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
