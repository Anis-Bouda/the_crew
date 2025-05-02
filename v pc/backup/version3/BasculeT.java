public class BasculeT extends Composant{
		public BasculeT(String id, int x, int y) {
	        super(id,x,y);
	        this.addInput(State.UNKNOWN); // D
	        this.addInput(State.UNKNOWN); // CLK
	        this.addOutput(State.UNKNOWN); // Q
	        this.addOutput(State.UNKNOWN); // Qbar
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
	         
	      if(CLK == State.TRUE) {
	         // le cas ou unknown 
	         if (T == State.UNKNOWN) {
	             outputs[0] = State.UNKNOWN;
	             outputs[1] = State.UNKNOWN;
	         } 
	         
	         else if (T == State.ERROR) {
	             outputs[0] = State.ERROR;
	             outputs[1] = State.ERROR;
	         }
	         else if(T == State.FALSE) {
        	     // on concerve Q
        	     if(outputs[0] == State.FALSE) {
        	    	 outputs[1] = State.TRUE;
        	     }
        	     else if(outputs[0] == State.TRUE) {
        	    	 outputs[1] = State.FALSE;
        	     }
             }
	         else  if(T == State.TRUE) {
	        		 if(this.outputs.get(0) == State.FALSE) {
	        			 outputs[0] = State.TRUE;
	        			 outputs[1] = State.FALSE;
	        		 }
	        		 if(this.outputs.get(0) == State.TRUE) {
	        			 outputs[0] = State.FALSE;
	        			 outputs[1] = State.TRUE;
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
	         
	         // si CLK = FALSE alors on conserve Q 
	        
	         for (int i = 0; i < outputs.length; i++) {
	             this.outputs.set(i, outputs[i]);
	         }
	         }
	    	 else
		        {
		           throw new IllegalStateException("Erreur d'évaluation : La Bascule TA doit avoir exactement 2 inputs et 2 outputs.");
		        }
	    }}