public class Buffer extends Composant {
	 public Buffer(String id, int x, int y) {
	        super(id, x, y);
	        this.addInput(State.UNKNOWN);
	        this.addOutput(State.UNKNOWN);
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
        }
	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}