import javax.swing.*;
import java.util.*;

public class MainCMPTTest {
    public static void main(String[] args) {
        // un mot de 4 bits 
    	// on veut affiche le bit positionner a l'index = 2
    	CMPT model = new CMPT("CMPT1", 100, 100);
        model.setInputState(0, State.False);
        model.setInputState(1, State.False);
        model.setInputState(2, State.True);
        model.setInputState(3, State.True);

        // creer le BitFinder avec l'interface graphique 
        CMPT_ig view = new CMPT_ig();
        view.setInputState(0, State.False);
        view.setInputState(1, State.False);
        view.setInputState(2, State.True);
        view.setInputState(3, State.True);
     
        // creation du controller
        CMPTController controller = new CMPTController(model, view);

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
