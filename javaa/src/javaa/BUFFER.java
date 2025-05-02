public class BUFFER extends Composant {
	 public BUFFER(String id, int x, int y) {
	        super(id, x, y);
	        this.addInput(State.UNKNOWN);
	        this.addOutput(State.UNKNOWN);
	  }
	    public State getOutputState() {
	        return outputs.get(0);
	    }
	 
	    public void setInputState(int index, State state) {
	        if (index >= 0 && index < inputs.size()) {
	            inputs.set(index, state);
	        } else {
	            throw new IndexOutOfBoundsException("Index d'entrée invalide : " + index);
	        }
	    }
	    
	    public void evaluate() {
	        if (this.inputs.size() == 1 && this.outputs.size() == 1) {
	           // on recupere le inputs 
	        	  State inputs = this.inputs.get(0);
	        	  State outputs = State.UNKNOWN;
	           // le buffer transmet son entree a sa sortie 
	        	  outputs = inputs;
	        	  
	        	  this.outputs.set(0, outputs);
            }
        else {
            throw new IllegalStateException("Erreur d'évaluation : Le Buffer doit avoir exactement 1 entrée et 1 sortie.");
        }}}  
