public class RAMController {
    private RAM model;
    private RAM_ig view;

    public RAMController(RAM model, RAM_ig view) {
        this.model = model;
        this.view = view;

        // Ajout d'un écouteur pour la vue (MouseListener)
        this.view.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            	// on evalue la porte RAM lorsque clicked 
                evaluateGate();
            }
        });
    }

    public void evaluateGate() {
    	// on recupere les entrees depuis la vue et mettre a jour les entrees de modele
    	for(int i=0; i<7; i++) {
           State input = view.getSelectedInput(i); 
           model.inputs.set(i, input);
    	}
        // evaluation du modele 
        model.evaluate();


        // on recupere la sortie de modele pour mettre a jour la sortie de la vue 
        // car juste le modele qui puisse nous donner les bons resultats avec 
        // la bonne comprhension du composant 
        for(int i=0; i<3; i++) {
            State output = model.getOutput(i);
            view.updateOutput(i, output); 
       
        
        // Afficher le résultat dans la console
        System.out.println("🔎 Résultat modèle : " + output);
    }}
}
