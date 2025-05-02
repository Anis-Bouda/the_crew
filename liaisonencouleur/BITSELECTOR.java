public class BITSELECTOR extends Composant {
	 public BITSELECTOR(String id, int x, int y) {
	        super(id, x, y);
	        for(int i=0; i<8; i++) {
	           this.addInput(State.UNKNOWN);
	        }
	        this.addOutput(State.UNKNOWN);
	    }

	 public void setInputState(int index, State state) {
	        if (index >= 0 && index < inputs.size()) {
	            inputs.set(index, state);
	        } else {
	            throw new IndexOutOfBoundsException("Index d'entrée invalide : " + index);
	        }
	    }

	    public State getOutputState() {
	        return outputs.get(0);
	    }	 
	 
	    public void evaluate() {
	        if (this.inputs.size() == 8 && this.outputs.size() == 1) {
	            int cptU = 0;
	            int cptE = 0;
	            for(State s : inputs) {
	            	if(s == State.UNKNOWN) {
	            		cptU ++;
	            	}
	            	else if(s == State.ERROR) {
	            		cptE ++;
	            	}
	            }
	            State res;
	            if(cptU > 0) {
	            	res = State.UNKNOWN;
	            }
	            else if(cptE > 0) {
	            	res = State.ERROR;
	            }
	            else { // 001 c'est 1
	                   int index1 = inputs.get(0) == State.True ? 1 : 0;
	                   // 010 c'est 2
	                   int index2 = inputs.get(1) == State.True ? 2 : 0;
	                   // 100 c'est 4
                       int index3 = inputs.get(2) == State.True ? 4 : 0;
                       int index = index1 + index2 + index3;
                       // donc output va prendre la valeur de 3 + index de inputs
                       if (index >= 0 && index <= 4) {  
                    	   res = inputs.get(3 + index);  
                    	} else {  
                    	   // dans ce cas l'index est invalide 
                    	   res = State.ERROR;  
                    	}
	            }
	                this.outputs.set(0, res);
	            }
	        else {
	            throw new IllegalStateException("Erreur d'évaluation : Le Byte selector doit avoir exactement 8 entrées et 1 sortie.");
	        }}}  
