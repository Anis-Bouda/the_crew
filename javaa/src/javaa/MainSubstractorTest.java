import javax.swing.*;
import java.util.*;

public class MainSubstractorTest {
    public static void main(String[] args) {
        // un mot de 4 bits 
    	// on veut affiche le bit positionner a l'index = 2
    	Substractor model = new Substractor("SUB1", 100, 100);
        model.setInputState(0, State.True);
        model.setInputState(1, State.True);
        model.setInputState(2, State.False);
        model.setInputState(3, State.False);
        model.setInputState(4, State.False);

        // creer le BitFinder avec l'interface graphique 
        Substractor_ig view = new Substractor_ig();
        view.setInputState(0, State.True);
        view.setInputState(1, State.True);
        view.setInputState(2, State.False);
        view.setInputState(3, State.False);
        view.setInputState(4, State.False);
   
        // creation du controller
        SubstractorController controller = new SubstractorController(model, view);

        // fenetre swing 
        JFrame frame = new JFrame("Test Substractor");
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
