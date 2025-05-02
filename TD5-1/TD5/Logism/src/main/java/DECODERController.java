import java.util.ArrayList;
import java.util.List;

public class DECODERController {
    private DECODER model;
    private DECODER_ig view;

    public DECODERController(DECODER model, DECODER_ig view) {
        this.model = model;
        this.view = view;

       // Ajout d'un écouteur pour la vue (MouseListener)
        this.view.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            	// on evalue le decodeur lorsque clicked 
                evaluateGate();
            }
        });
    }

    public void evaluateGate() {
        for (int i = 0; i < 2; i++) { 
        	// on recupere les entrees depuis la vue et mettre a jour les entrees de modele
            State inputState = view.getSelectedInput(i);
            model.inputs.set(i, inputState);
        }
        // evaluation du modele 
        model.evaluate();

        // on recupere la sortie de modele pour mettre a jour la sortie de la vue 
        // car juste le modele qui puisse nous donner les bons resultats avec 
        // la bonne comprhension du composant 
        List<State> outputs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            outputs.add(model.getOutput(i));
        }
        view.updateOutputs(outputs);
        for (int i = 0; i < 4; i++) {
            System.out.println("🔎 Sortie " + i + " : " + model.getOutput(i));
        }
    }
}
