

public class BasculeT extends Composant{
		public BasculeT(String id, int x, int y) {
	        super(id,x,y);
	        this.addInput(State.UNKNOWN); // T
	        this.addInput(State.UNKNOWN); // CLK
	        this.addOutput(State.False); // Q
	        this.addOutput(State.True); // Qbar
	   }
		
	    public void evaluate() {
	    	 if (this.inputs.size() == 2 && this.outputs.size()==2)
	    	 {
	         State T=this.inputs.get(0);
	         State CLK=this.inputs.get(1);
	         
	         State[] outputs = new State[2];
	         // recuperer les outputs
	         for (int i = 0; i < outputs.length; i++) {
	             outputs[i] = this.outputs.get(i);
	         }
	         
	      if(CLK == State.True) {
	         // le cas ou unknown 
	         if (T == State.UNKNOWN) {
	             outputs[0] = State.UNKNOWN;
	             outputs[1] = State.UNKNOWN;
	         } 
	         
	         if (T == State.ERROR) {
	             outputs[0] = State.ERROR;
	             outputs[1] = State.ERROR;
	         }
	         if(T == State.False) {
        	     // on concerve Q
        	     if(outputs[0] == State.False) {
        	    	 outputs[1] = State.True;
        	     }
        	     if(outputs[0] == State.True) {
        	    	 outputs[1] = State.False;
        	     }
             }
	         if(T == State.True) {
	        		 if(this.outputs.get(0) == State.False) {
	        			 outputs[0] = State.True;
	        			 outputs[1] = State.False;
	        		 }
	        		 if(this.outputs.get(0) == State.True) {
	        			 outputs[0] = State.False;
	        			 outputs[1] = State.True;
	        		 }
	        		 if(this.outputs.get(0) == State.UNKNOWN) {
	        			 outputs[0] = State.UNKNOWN;
	        			 outputs[1] = State.UNKNOWN;
	        		 }
	        		 if(this.outputs.get(0) == State.ERROR) {
	        			 outputs[1] = State.ERROR;
	        			 outputs[0] = State.ERROR;
	        		 }
	        }}
	         
	         // si CLK = False alors on conserve Q 
	        
	         for (int i = 0; i < outputs.length; i++) {
	             this.outputs.set(i, outputs[i]);
	         }
	         }
	    	 else
		        {
		           throw new IllegalStateException("Erreur d'évaluation : La Bascule TA doit avoir exactement 2 inputs et 2 outputs.");
		        }
	    }}
