import javax.swing.*;
import java.util.*;

public class MainDEMUXTest {
    public static void main(String[] args) {
        // un mot de 4 bits 
    	// on veut affiche le bit positionner a l'index = 2
    	DEMUX model = new DEMUX("DEMUX1", 100, 100);
        model.setInputState(0, State.True);
        model.setInputState(1, State.True);
        model.setInputState(2, State.True);

        // creer le BitFinder avec l'interface graphique 
        DEMUX_ig view = new DEMUX_ig();
        view.setInputState(0, State.True);
        view.setInputState(1, State.True);
        view.setInputState(2, State.True);
     
        // creation du controller
        DEMUXController controller = new DEMUXController(model, view);

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
