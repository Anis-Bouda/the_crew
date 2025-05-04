package Logique.Memory;
import Logique.Principale.*;
public class BasculeJK extends Composant{
	public BasculeJK(String id, int x, int y) {
        super(id,x,y);
        this.addInput(State.UNKNOWN); // J
        this.addInput(State.UNKNOWN); // K
        this.addInput(State.UNKNOWN); // CLK
        this.addOutput(State.UNKNOWN); // Q
        this.addOutput(State.UNKNOWN); // Q bar
   }
	
    public void evaluate() {
    	 if (this.inputs.size() == 3 && this.outputs.size()==2)
    	 {
         State J=this.inputs.get(0);
         State K=this.inputs.get(1);
         State CLK=this.inputs.get(2);
         // les outputs
         State[] outputs = new State[2];
         // recuperer les outputs 
         for (int i = 0; i < outputs.length; i++) {
             outputs[i] = this.outputs.get(i);
         }
         
         if(CLK == State.TRUE) {
           // le cas ou y a un unknown
           if(J == State.UNKNOWN || K == State.UNKNOWN) {
        	 for (int i = 0; i < outputs.length; i++) {
                 outputs[i] = State.UNKNOWN;
           }}
         
         // le cas ou y a un error
         else if(J == State.ERROR || K == State.ERROR) {
        	 for (int i = 0; i < outputs.length; i++) {
                 outputs[i] = State.ERROR;
         }}
         
         else if(J == State.TRUE && K == State.TRUE) {
        	 if (outputs[0] == State.TRUE) {
                 outputs[0] = State.FALSE;
                 outputs[1] = State.TRUE;
             } else {
                 outputs[0] = State.TRUE;
                 outputs[1] = State.FALSE;
             }
         }
         
         else if(J == State.TRUE && K == State.FALSE) {
                 outputs[0] = State.TRUE;
                 outputs[1] = State.FALSE;
             }
         
         else if(J == State.FALSE && K == State.TRUE) {
        	 outputs[0] = State.FALSE;
             outputs[1] = State.TRUE;
         }
         
         // dans le cas S==FALSE et R==FALSE, on modifie pas Q et Qbar
         }
         
         // dans le cas CLK == FALSE, on modifie pas Q et Qbar
         
         for (int i = 0; i < outputs.length; i++) {
             this.outputs.set(i, outputs[i]);
         }
    }
    	 else
	        {
	           throw new IllegalStateException("Erreur d'évaluation : La Bascule JK doit avoir exactement 3 inputs et 2 outputs.");
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