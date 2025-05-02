import javax.swing.*;
import java.util.*;

public class MainBUFFERTest {
    public static void main(String[] args) {
        // un mot de 4 bits 
    	// on veut affiche le bit positionner a l'index = 2
        BUFFER model = new BUFFER("BF1", 100, 100);
        model.setInputState(0, State.True);

        // creer le BitFinder avec l'interface graphique 
        BUFFER_ig view = new BUFFER_ig();
        view.setInputState(0, State.True);
     
        // creation du controller
        BUFFERController controller = new BUFFERController(model, view);

        // fenetre swing 
        JFrame frame = new JFrame("Test BUFFER");
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
