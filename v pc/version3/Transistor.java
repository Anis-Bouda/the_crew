public class Transistor extends Composant {
	 public Transistor(String id, int x, int y) {
	        super(id, x, y);
	        // le courant
	        this.addInput(State.UNKNOWN);
	        // signal de controle 
	        this.addInput(State.UNKNOWN);
	        // sortie du courant 
	        this.addOutput(State.UNKNOWN);
	  }
        
	  @Override
	   public void evaluate() {
	        if (this.inputs.size() == 2 && this.outputs.size() == 1) {
	           // on recupere les inputs 
	        	  State inputsignal = this.inputs.get(0);
	        	  State controlsignal = this.inputs.get(1);
	        	  State outputsignal = State.UNKNOWN;

	        	  if(inputsignal == State.ERROR || controlsignal == State.ERROR) {
	        		  outputsignal = State.ERROR;
	        	  }
	        	  else if(inputsignal == State.UNKNOWN || controlsignal == State.UNKNOWN) {
	        		  outputsignal = State.UNKNOWN;
	        	  }
	        	  else  {
	        		  if(controlsignal == State.TRUE) {
	        		  // le courant passe
	        		  outputsignal = inputsignal;
	        	      }
	        	      else if(controlsignal == State.FALSE) {
	        		  // coupure de courant
	        		  outputsignal = State.FALSE;
	        	}}
	        	  this.outputs.set(0, outputsignal);
	        }
	        else {
	            throw new IllegalStateException("Erreur d'évaluation : Le Transistor doit avoir exactement 2 entrées et 1 sortie.");
	        }}}