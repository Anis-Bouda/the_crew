import javax.swing.*;
import java.util.*;

public class MainNegatorTest {
    public static void main(String[] args) {
        // un mot de 4 bits 
    	// on veut affiche le bit positionner a l'index = 2
    	Negator model = new Negator("Negator1", 100, 100);
        model.setInputState(0, State.False);
        model.setInputState(1, State.True);

        // creer le BitFinder avec l'interface graphique 
        Negator_ig view = new Negator_ig();
        view.setInputState(0, State.False);
        view.setInputState(1, State.True);
     
        // creation du controller
        NegatorController controller = new NegatorController(model, view);

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
