public class Sept_Segments extends Composant {
	 public Sept_Segments(String id, int x, int y) {
	        super(id, x, y);
	        
	        this.addInput(State.UNKNOWN);
	        this.addInput(State.UNKNOWN);
	        this.addInput(State.UNKNOWN);
	        this.addInput(State.UNKNOWN);
	        this.addOutput(State.UNKNOWN);
	        this.addOutput(State.UNKNOWN);
	        this.addOutput(State.UNKNOWN);
	        this.addOutput(State.UNKNOWN);
	        this.addOutput(State.UNKNOWN);
	        this.addOutput(State.UNKNOWN);
	        this.addOutput(State.UNKNOWN);
	}

	    public void evaluate() {
	        if (this.inputs.size() == 4 && this.outputs.size() == 7) {
	            // Un tableau des inputs 
	            State[] inputs = new State[4];
	            for (int i = 0; i < inputs.length; i++) {
	                inputs[i] = this.inputs.get(i);
	            }
	                
	            // Un tableau des outputs 
	            State[] outputs = new State[7];

	            // par defaut 
	            for (int i = 0; i < outputs.length; i++) {
	                outputs[i] = State.UNKNOWN;
	            }
	            
	            int cptE = 0;
                for (int j = 0; j < inputs.length; j++) {
                    if (inputs[j] == State.ERROR) {
                        cptE++;
                }}
                // Si y a une entree qui est error alors les sorties sont tous errors 
                if (cptE > 0) {
                    for (int i = 0; i < outputs.length; i++) {
                        outputs[i] = State.ERROR;
                }}
                
		        // le cas ou y a une entree unknown 
		        int cptU = 0;
		            for (int j = 0; j < inputs.length; j++) {
		                 if (inputs[j] == State.UNKNOWN) {
		                    cptU++;
		        }}
		        // si y a une entree qui est unknown alors les sorties sont unknown 
		        if (cptU > 0) {
		           for (int i = 0; i < outputs.length; i++) {
		              outputs[i] = State.UNKNOWN;
		        }}
		          
		        // le cas de 0000
	            if(inputs[0] == State.FALSE && inputs[1] == State.FALSE
	            && inputs[2] == State.FALSE && inputs[3] == State.FALSE) {
	            	for(int i=0; i<outputs.length -1; i++) {
	            		outputs[i] = State.TRUE;
	            	}
	            	outputs[6] = State.FALSE;
	            }
	            // le cas de 0001
	            else if(inputs[0] == State.FALSE && inputs[1] == State.FALSE
	    	    && inputs[2] == State.FALSE && inputs[3] == State.TRUE) {
	            	outputs[0] = State.FALSE;
	            	outputs[1] = State.TRUE;
	            	outputs[2] = State.TRUE;
	            	for(int i=3; i<outputs.length; i++) {
	            		outputs[i] = State.FALSE;
	            	}
	            }
	            // le cas de 0010
	            else if(inputs[0] == State.FALSE && inputs[1] == State.FALSE
	    	    && inputs[2] == State.TRUE && inputs[3] == State.FALSE) {
	            	outputs[0] = State.TRUE; outputs[1] = State.TRUE;
	            	outputs[2] = State.FALSE; outputs[3] = State.TRUE;
	            	outputs[4] = State.TRUE; outputs[5] = State.FALSE;
	            	outputs[6] = State.TRUE;
	            }
	            // le cas de 0011
	            else if(inputs[0] == State.FALSE && inputs[1] == State.FALSE
	    	    && inputs[2] == State.TRUE && inputs[3] == State.TRUE) {
	    	        outputs[0] = State.TRUE; outputs[1] = State.TRUE;
	    	        outputs[2] = State.TRUE; outputs[3] = State.TRUE;
	    	        outputs[4] = State.FALSE; outputs[5] = State.FALSE;
	    	        outputs[6] = State.TRUE;
	    	    }
	            // le cas de 0100 
	            else if(inputs[0] == State.FALSE && inputs[1] == State.TRUE
	    	    && inputs[2] == State.FALSE && inputs[3] == State.FALSE) {
	    	        outputs[0] = State.FALSE; outputs[1] = State.TRUE;
	    	        outputs[2] = State.TRUE; outputs[3] = State.FALSE;
	    	        outputs[4] = State.FALSE; outputs[5] = State.TRUE;
	    	        outputs[6] = State.TRUE;
	    	   }
	           // le code de 0101
	            else if(inputs[0] == State.FALSE && inputs[1] == State.TRUE
	    	    && inputs[2] == State.FALSE && inputs[3] == State.TRUE) {
	    	        outputs[0] = State.TRUE; outputs[1] = State.FALSE;
	    	        outputs[2] = State.TRUE; outputs[3] = State.TRUE;
	    	        outputs[4] = State.FALSE; outputs[5] = State.TRUE;
	    	        outputs[6] = State.TRUE;
	    	    }
	           // le code de 0110
	            else if(inputs[0] == State.FALSE && inputs[1] == State.TRUE
	    	    && inputs[2] == State.TRUE && inputs[3] == State.FALSE) {
	    	        outputs[0] = State.TRUE; outputs[1] = State.FALSE;
	    	        outputs[2] = State.TRUE; outputs[3] = State.TRUE;
	    	        outputs[4] = State.TRUE; outputs[5] = State.TRUE;
	    	        outputs[6] = State.TRUE;
	    	    }
	           // le code de 0111
	            else if(inputs[0] == State.FALSE && inputs[1] == State.TRUE
	    	    && inputs[2] == State.TRUE && inputs[3] == State.TRUE) {
	    	        outputs[0] = State.TRUE; outputs[1] = State.TRUE;
	    	        outputs[2] = State.TRUE; outputs[3] = State.FALSE;
	    	        outputs[4] = State.FALSE; outputs[5] = State.FALSE;
	    	        outputs[6] = State.FALSE;
	    	    } 
	           // le code de 1000
	            else if(inputs[0] == State.TRUE && inputs[1] == State.FALSE
	    	    && inputs[2] == State.FALSE && inputs[3] == State.FALSE) {
	    	        outputs[0] = State.TRUE; outputs[1] = State.TRUE;
	    	        outputs[2] = State.TRUE; outputs[3] = State.TRUE;
	    	        outputs[4] = State.TRUE; outputs[5] = State.TRUE;
	    	        outputs[6] = State.TRUE;
	    	    }
	            // le code de 1001
	            else if(inputs[0] == State.TRUE && inputs[1] == State.FALSE
	    	    && inputs[2] == State.FALSE && inputs[3] == State.TRUE) {
	    	        outputs[0] = State.TRUE; outputs[1] = State.TRUE;
	    	        outputs[2] = State.TRUE; outputs[3] = State.TRUE;
	    	        outputs[4] = State.FALSE; outputs[5] = State.TRUE;
	    	        outputs[6] = State.TRUE;
	    	   }
	            // le cas de unknown 
	            else if((inputs[0] == State.TRUE && inputs[1] == State.TRUE && inputs[2] == State.FALSE)
	           || (inputs[0] == State.TRUE && inputs[1] == State.FALSE && inputs[2] == State.TRUE)) {
	               for(int i=0; i<outputs.length; i++) {
	            	   outputs[i] = State.UNKNOWN;
	            }}
	           // le cas de erreur 
	            else if(inputs[0] == State.TRUE && inputs[1] == State.TRUE && inputs[2] == State.TRUE) {
	            	for(int i=0; i<outputs.length; i++) {
	            		outputs[i] = State.ERROR;
	            	}
	            }
	            for (int i = 0; i < outputs.length; i++) {
	                this.outputs.set(i, outputs[i]);
	            }
	        }
	     else {
            throw new IllegalStateException("Erreur d'évaluation : Le 7-segments doit avoir exactement 4 entrées et 7 sorties.");
        }}}
