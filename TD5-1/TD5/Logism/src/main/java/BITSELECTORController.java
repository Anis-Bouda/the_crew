public class BITSELECTORController {
    private BITSELECTOR model;
    private BITSELECTOR_ig view;

    public BITSELECTORController(BITSELECTOR model, BITSELECTOR_ig view) {
        this.model = model;
        this.view = view;

        // Ajout d'un écouteur pour la vue (MouseListener)
        this.view.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            	// on evalue le BITSELECTOR lorsque clicked 
                evaluateGate();
            }
        });
    }

    public void evaluateGate() {
        for (int i = 0; i < 8; i++) {
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
