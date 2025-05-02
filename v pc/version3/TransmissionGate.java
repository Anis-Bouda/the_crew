public class TransmissionGate extends Composant {
	public TransmissionGate(String id, int x, int y) {
	        super(id, x, y);
	        // le signal d'entree
	        this.addInput(State.UNKNOWN);
	        // le signal de control 
	        this.addInput(State.UNKNOWN);
	        // sortie 
	        this.addOutput(State.UNKNOWN);
	  }
    
	  @Override
	   public void evaluate() {
	        if (this.inputs.size() == 2 && this.outputs.size() == 1) {
	           // on recupere les inputs 
	        	  State inputsignal = this.inputs.get(0);
	        	  State controlsignal = this.inputs.get(1);
	        	  State outputs = State.UNKNOWN;

	        	  if(inputsignal == State.ERROR || controlsignal == State.ERROR) {
	        		  outputs = State.ERROR;
	        	  }
	        	  else if(controlsignal == State.UNKNOWN) {
	        		  outputs = State.UNKNOWN;
	        	  }
	        	  else if(controlsignal == State.TRUE) {
	        		  // le signal d'entree est transmis a la sortie 
	        		  outputs= inputsignal;
	        	  }
	        	  else if(controlsignal == State.FALSE) {
	        		  // la sortie est unknown
	        		  outputs = State.UNKNOWN;
	        	  }
	        	  
	        	 this.outputs.set(0, outputs);
	        }
	        else {
	            throw new IllegalStateException("Erreur d'évaluation : Le Transmission Gate doit avoir exactement 2 entrées et 1 sortie.");
	        }}}