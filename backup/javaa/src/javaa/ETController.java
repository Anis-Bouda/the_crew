public class ETController {
    private ET model;
    private ET_ig view;

    public ETController(ET model, ET_ig view) {
        this.model = model;
        this.view = view;

        // Ajout d'un écouteur pour la vue (MouseListener)
        //il va directement evaluer le composant des que il est placer 
        this.view.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            	// on evalue la porte ET lorsque on clique dessu
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
