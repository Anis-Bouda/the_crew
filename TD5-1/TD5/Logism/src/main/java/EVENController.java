public class EVENController {
    private EVEN model;
    private EVEN_ig view;

    public EVENController(EVEN model, EVEN_ig view) {
        this.model = model;
        this.view = view;

        // Ajout d'un écouteur pour la vue (MouseListener)
        this.view.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            	// on evalue la porte ET lorsque clicked 
                evaluateGate();
            }
        });
    }

    public void evaluateGate() {
    	for (int i = 0; i < model.inputs.size(); i++) {
    		// on recupere les entrees depuis la vue et mettre a jour les entrees de modele
            State state = view.getSelectedInput(i);
            model.setInputState(i, state);
        }
    	// evaluation du modele 
        model.evaluate();

        // on recupere la sortie de modele pour mettre a jour la sortie de la vue 
        // car juste le modele qui puisse nous donner les bons resultats avec 
        // la bonne comprhension du composant 
        State output = model.getOutputState();
        view.updateOutput(output);

        // Afficher le résultat dans la console
        System.out.println("🔎 Résultat modèle : " + output);
    }
}
