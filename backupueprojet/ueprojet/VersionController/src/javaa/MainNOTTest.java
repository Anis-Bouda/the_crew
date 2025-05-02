import javax.swing.JFrame;
import java.awt.Point;

public class MainNOTTest {
    public static void main(String[] args) {
        // creation du modele 
        NOT model = new NOT("NOT1", 100, 100);
        model.inputs.set(0, State.True);

        // creation de la porte et dans l'interface graphique 
        NOT_ig view = new NOT_ig();
        view.setInputState(0, State.True);

        // creation du controller 
        NOTController controller = new NOTController(model, view);

        // fenetre swing
        JFrame frame = new JFrame("Test Porte NOT");
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
