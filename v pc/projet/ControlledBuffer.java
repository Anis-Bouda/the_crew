package projet;

public class ControlledBuffer extends Composant {
	 public ControlledBuffer(String id, int x, int y) {
	        super(id, x, y);
	        this.addInput(State.UNKNOWN);
	        this.addInput(State.UNKNOWN);
	        this.addOutput(State.UNKNOWN);
	  }

	    public void evaluate() {
	        if (this.inputs.size() == 2 && this.outputs.size() == 1) {
	           // on recupere les inputs 
	        	  State inputs = this.inputs.get(0);
	        	  State control = this.inputs.get(1);
	        	  State outputs = State.UNKNOWN;
	        
	        	  // le controlled buffer renvoie a la sortie la valeur 
	        	  // de l'entree quand le control qui est un signal est 
	        	  // true sinon c'est toujours unknown 
	              if(control == State.UNKNOWN) {
	            	  outputs = State.UNKNOWN;
	              }
	              else if(control == State.ERROR) {
	            	  outputs = State.UNKNOWN;
	              }
	              else if(control == State.False) {
	            	  outputs = State.UNKNOWN;
	              }
	              else if(control == State.True) {
 	        	      outputs = inputs;
	              }
	           // le buffer transmet son entree a sa sortie 
	        	  this.outputs.set(0, outputs);
         }
     else {
         throw new IllegalStateException("Erreur d'évaluation : Le ControlledBuffer doit avoir exactement 2 entrées et 1 sortie.");
     }}}  
