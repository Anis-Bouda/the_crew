import javax.swing.JFrame;
import java.awt.Point;

public class MainNANDTest {
    public static void main(String[] args) {
        // creation du modele 
        NAND model = new NAND("NAND1", 100, 100);
        model.inputs.set(0, State.False);
        model.inputs.set(1, State.False);

        // creation de l'interface graphique 
        NAND_ig view = new NAND_ig();
        view.setInputState(0, State.False);
        view.setInputState(1, State.False);

        // creation du controller 
        NANDController controller = new NANDController(model, view);

        // fenetre swing 
        JFrame frame = new JFrame("Test Porte NAND");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setLayout(null);

        // ajouter la vue a la fenetre 
        // la position (30,30)
        // la taille 100x100 pixels 
        view.setBounds(30, 30, 100, 100); 
        frame.add(view);
        frame.setVisible(true);

        // evaluation 
        controller.evaluateGate();
    }
}
