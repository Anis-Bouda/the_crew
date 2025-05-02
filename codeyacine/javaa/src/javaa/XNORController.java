public class XNORController {
    private XNOR model;
    private XNOR_ig view;

    public XNORController(XNOR model, XNOR_ig view) {
        this.model = model;
        this.view = view;

        // ajout d'un ecouteur pour les ports
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
        model.inputs.set(0, input1);
        model.inputs.set(1, input2);
        // evaluation du modele 
        model.evaluate();

        // on recupere la sortie de modele pour mettre a jour la sortie de la vue 
        // car juste le modele qui puisse nous donner les bons resultats avec 
        // la bonne comprhension du composant
        State output = model.getOutput(0);
        view.updateOutput(output); 
        
        // Afficher le résultat dans la console
        System.out.println("🔎 Résultat modèle : " + output);
    }
}
