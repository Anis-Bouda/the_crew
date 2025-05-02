public class BUFFERController {
    private BUFFER model;
    private BUFFER_ig view;

    public BUFFERController(BUFFER model, BUFFER_ig view) {
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
        State output = model.getOutputState();
        view.updateOutput(output);

        // Afficher le résultat dans la console
        System.out.println("Résultat modèle : " + output);
    }
}
