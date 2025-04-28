import java.util.ArrayList;
import java.util.List;

public class SubstractorController {
    private Substractor model;
    private Substractor_ig view;

    public SubstractorController(Substractor model, Substractor_ig view) {
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
        State input2 = view.getSelectedInput(1); 
        State input3 = view.getSelectedInput(2); 
        State input4 = view.getSelectedInput(3); 
        State input5 = view.getSelectedInput(4); 
        model.inputs.set(0, input1);
        model.inputs.set(1, input2);
        model.inputs.set(2, input3);
        model.inputs.set(3, input4);
        model.inputs.set(4, input5);
    	// evaluation du modele 
        model.evaluate();
        
        // on recupere la sortie de modele pour mettre a jour la sortie de la vue 
        // car juste le modele qui puisse nous donner les bons resultats avec 
        // la bonne comprhension du composant 
        List<State> outputs = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            outputs.add(model.getOutput(i));
        }
        view.updateOutputs(outputs);
        for (int i = 0; i < 2; i++) {
            System.out.println(" Sortie " + i + " : " + model.getOutput(i));
    }}
}
