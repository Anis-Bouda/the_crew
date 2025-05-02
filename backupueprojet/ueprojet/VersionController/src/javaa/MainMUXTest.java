import javax.swing.*;
import java.util.*;

public class MainMUXTest {
    public static void main(String[] args) {
        // un mot de 4 bits 
    	// on veut affiche le bit positionner a l'index = 2
    	MUX model = new MUX("MUX1", 100, 100);
        model.setInputState(0, State.True);
        model.setInputState(1, State.False);
        model.setInputState(2, State.UNKNOWN);
        model.setInputState(3, State.ERROR);
        model.setInputState(4, State.ERROR);
        model.setInputState(5, State.False);

        // creer le BitFinder avec l'interface graphique 
        MUX_ig view = new MUX_ig();
        view.setInputState(0, State.True);
        view.setInputState(1, State.False);
        view.setInputState(2, State.UNKNOWN);
        view.setInputState(3, State.ERROR);
        view.setInputState(4, State.ERROR);
        view.setInputState(5, State.False);
     
        // creation du controller
        MUXController controller = new MUXController(model, view);

        // fenetre swing 
        JFrame frame = new JFrame("Test Substractor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);

        // ajouter la vue a la fenetre
        view.setBounds(150, 150, 400, 400);
        frame.add(view);
        frame.setVisible(true);

        // evaluation de la sortie 
        controller.evaluateGate();
    }
}
