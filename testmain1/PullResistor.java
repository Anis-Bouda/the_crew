public class PullResistor extends Composant {
	private State pullState;
	
	 public PullResistor(String id, int x, int y, State pullState) {
	        super(id, x, y);
	        // l'entree
	        this.addInput(State.UNKNOWN);
	        // sortie 
	        this.addOutput(State.UNKNOWN);
	        // le state soit c'est 0 ou 1, c'est la sortie force
	        if(pullState == State.TRUE) {
	        	this.pullState = State.TRUE;
	        }
	        else if(pullState == State.FALSE) {
	        	this.pullState = State.FALSE;
	        }
	  }
     
	  @Override
	   public void evaluate() {
	        if (this.inputs.size() == 1 && this.outputs.size() == 1) {
	           // on recupere les inputs 
	        	  State inputs = this.inputs.get(0);
	        	  State outputs = State.UNKNOWN;

	        	  if(inputs == State.ERROR) {
	        		  outputs = State.ERROR;
	        	  }
	        	  else if(inputs == State.UNKNOWN) {
	        		  /*  forcer la valeur soit c'est 1, soit c'est 0*/
	        		  outputs = this.pullState;
	        	  }
	        	  else  {
	        		  /*  si l'entree c'est 0 ou 1 ou erreur, le sortie c'est l'entree*/
	        		  outputs= inputs;
	        	}
	        	  this.outputs.set(0, outputs);
	        }
	        else {
	            throw new IllegalStateException("Erreur d'évaluation : Le Pull Resistor doit avoir exactement 1 entrée et 1 sortie.");
	        }}}