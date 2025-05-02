import java.util.Objects;

public class LED extends Composant {
    
    public LED(String id, int x, int y) {
        super(id, x, y);
        // y a juste une entree
        this.addInput(State.UNKNOWN);
        // une sortie pour respect le contrat de output 
        this.addOutput(State.UNKNOWN);
    }
    
    public void setInputState(State state) {
            inputs.set(0, state);
    }

    public String getId() {
    	return this.id;
    }
   
    @Override
    public void evaluate() {
    	 if (this.inputs.size() == 1) {
    		 // recuperer l'entrée
    		 State inputs = this.inputs.get(0);
    	     // on affiche l'etat de la lampe 
    	     System.out.println("État de la lampe " + this.id + " : " + inputs);
            // Mettre à jour l'état de la lampe
            this.state = inputs;
            if(state == State.True) {
            	System.out.println("la lampe est allume yayyyyyyyyy 🟡");
            }
            // Mettre l'état de sortie à UNKNOWN pour respecter le contrat
            this.outputs.set(0, State.UNKNOWN);
    	 }
        else {
         throw new IllegalStateException("Erreur d'évaluation : Une Lampe doit avoir exactement 1 entrée");
     }
 }
    
    public State getState() {
    	return state;
    }
}
