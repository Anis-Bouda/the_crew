package Memory;
import Principale.*;
public class BasculeSR extends Composant {
	public BasculeSR(String id, int x, int y) {
        super(id,x,y);
        this.addInput(State.UNKNOWN); // S
        this.addInput(State.UNKNOWN); // R
        this.addOutput(State.UNKNOWN); // Q
        this.addOutput(State.UNKNOWN); // Q bar
   }
	
    public void evaluate() {
    	 if (this.inputs.size() == 2 && this.outputs.size()==2)
    	 {
         State S=this.inputs.get(0);
         State R=this.inputs.get(1);
         // les outputs
         State[] outputs = new State[2];
         // les outputs par defaut 
         for (int i = 0; i < outputs.length; i++) {
             outputs[i] = this.outputs.get(i);
         }
         
         // le cas ou y a un unknown
         if(S == State.UNKNOWN || R == State.UNKNOWN) {
        	 for (int i = 0; i < outputs.length; i++) {
                 outputs[i] = State.UNKNOWN;
             }
         }
         
         // le cas ou y a un error
         else if(S == State.ERROR || R == State.ERROR) {
        	 for (int i = 0; i < outputs.length; i++) {
                 outputs[i] = State.ERROR;
             }
         }
         
         else if(S == State.TRUE && R == State.TRUE) {
        	 for (int i = 0; i < outputs.length; i++) {
                 outputs[i] = State.ERROR;
             }
         }
         
         else if(S == State.TRUE && R == State.FALSE) {
                 outputs[0] = State.TRUE;
                 outputs[1] = State.FALSE;
             }
         
         else if(S == State.FALSE && R == State.TRUE) {
        	 outputs[0] = State.FALSE;
             outputs[1] = State.TRUE;
         }
         
         // dans le cas S==FALSE et R==FALSE, on modifie pas Q et Qbar
         
         for (int i = 0; i < outputs.length; i++) {
             this.outputs.set(i, outputs[i]);
         }
    }
    	 else
	        {
	           throw new IllegalStateException("Erreur d'évaluation : La Bascule RS doit avoir exactement 2 inputs et 2 outputs.");
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