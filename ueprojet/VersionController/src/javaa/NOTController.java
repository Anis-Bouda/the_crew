public class NOTController {
    private NOT model;
    private NOT_ig view;

    public NOTController(NOT model, NOT_ig view) {
        this.model = model;
        this.view = view;

        // Ajout d'un écouteur pour la vue (MouseListener)
        this.view.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            	// on evalue la porte NOT lorsque clicked 
                evaluateGate();
            }
        });
    }

    public void evaluateGate() {
    	// on recupere les entrees depuis la vue et mettre a jour les entrees de modele
        State input = view.getSelectedInput(0); 
        model.inputs.set(0, input);
        // evaluation du modele 
        model.evaluate();


        // on recupere la sortie de modele pour mettre a jour la sortie de la vue 
        // car juste le modele qui puisse nous donner les bons resultats avec 
        // la bonne comprhension du composant 
        State output = model.getOutput(0);
        view.updateOutput(output); 
        
        // Afficher le résultat dans la console
        System.out.println(" Résultat modèle : " + output);
    }
}
