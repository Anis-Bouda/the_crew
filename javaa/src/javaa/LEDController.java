import java.util.ArrayList;
import java.util.List;

public class LEDController {
    private LED model;
    private LED_ig view;

    public LEDController(LED model, LED_ig view) {
        this.model = model;
        this.view = view;

        // Ajout d'un écouteur pour la vue (MouseListener)
        this.view.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
               // on evalue le BitFinder lorsque clicked 
                evaluateGate();
            }
        });
    }

    public void evaluateGate() {
    	// on recupere les entrees depuis la vue et mettre a jour les entrees de modele
    	State input1 = view.getSelectedInput(0); 
        model.inputs.set(0, input1);
    	// evaluation du modele 
        model.evaluate();
        
        // on recupere la sortie de modele pour mettre a jour la sortie de la vue 
        // car juste le modele qui puisse nous donner les bons resultats avec 
        // la bonne comprhension du composant 
        // Met à jour la vue selon le nouvel état
        view.updateFromModel(model.getState());

        System.out.println("💡 Lampe " + model.getId() + " : " + model.getState());
    }
}
