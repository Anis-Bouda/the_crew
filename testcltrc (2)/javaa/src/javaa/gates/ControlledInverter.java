package gates ;
import Principale.*;
public class ControlledInverter extends Composant {
	 public ControlledInverter(String id, int x, int y) {
	        super(id, x, y);
	        this.addInput(State.UNKNOWN);
	        this.addInput(State.UNKNOWN);
	        this.addOutput(State.UNKNOWN);
	  }

	    public void evaluate() {
	        if (this.inputs.size() == 2 && this.outputs.size() == 1) {
	           /*  on recupere les inputs */
	        	  State inputs = this.inputs.get(0);
	        	  State control = this.inputs.get(1);
	        	  State outputs = State.UNKNOWN;

	        	/*le controller inverter inverse son entree lorsque 
	        	   le signal est a TRUE sinon non  */
	              if(control == State.UNKNOWN || inputs == State.UNKNOWN) {
	            	  outputs = State.UNKNOWN;
	              }
	              else if(control == State.ERROR || inputs == State.ERROR) {
	            	  outputs = State.ERROR;
	              }
	              else if(control == State.FALSE) {
	            	  outputs = inputs;
	              }
	              else if(control == State.TRUE) {
 	        	       if(inputs == State.FALSE) {
 	        	    	    outputs = State.TRUE;
 	        	       }
 	        	       else if(inputs == State.TRUE) {
 	        	    	   outputs = State.FALSE;
 	        	       }
	              }
	        	  this.outputs.set(0, outputs);
         }
     else {
         throw new IllegalStateException("Erreur d'évaluation : Le ControlledInverter doit avoir exactement 2 entrées et 1 sortie.");
     }}

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