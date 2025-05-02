public class ODDController {
    private ODD model;
    private ODD_ig view;

    public ODDController(ODD model, ODD_ig view) {
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
        State output = model.getOutputState();
        view.updateOutput(output);

        // Afficher le résultat dans la console
        System.out.println("🔎 Résultat modèle : " + output);
    }
}
