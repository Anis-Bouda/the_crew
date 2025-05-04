package plexers ;
import Principale.*;
public class ByteSelctor extends Composant {
	 public ByteSelctor(String id, int x, int y) {
	        super(id, x, y);
	        for(int i=0; i<8; i++) {
	           this.addInput(State.UNKNOWN);
	        }
	        this.addOutput(State.UNKNOWN);
	    }

	    public void evaluate() {
	        if (this.inputs.size() == 8 && this.outputs.size() == 1) {
	            // Un tableau des inputs 
	            State[] inputs = new State[8];
	            
	            for (int i = 0; i < inputs.length; i++) {
	                inputs[i] = this.inputs.get(i);
	            }
	            
	            // les outputs par defauts 
	            State outputs = State.UNKNOWN;
                
	            int cptU = 0;
	            int cptE = 0;
	            for(int i=0; i<inputs.length; i++) {
	            	if(inputs[i] == State.UNKNOWN) {
	            		cptU ++;
	            	}
	            	else if(inputs[i] == State.ERROR) {
	            		cptE ++;
	            	}
	            }
	            if(cptU > 0) {
	            	outputs = State.UNKNOWN;
	            }
	            else if(cptE > 0) {
	            	outputs = State.ERROR;
	            }
	            else { // 001 c'est 1
	                   int index1 = inputs[0] == State.TRUE ? 1 : 0;
	                   // 010 c'est 2
	                   int index2 = inputs[1] == State.TRUE ? 2 : 0;
	                   // 100 c'est 4
                       int index3 = inputs[2] == State.TRUE ? 4 : 0;
                       int index = index1 + index2 + index3;
                       // donc output va prendre la valeur de 3 + index de inputs
                       if (index >= 0 && index <= 4) {  
                    	   outputs = inputs[3 + index];  
                    	} else {  
                    	   // dans ce cas l'index est invalide 
                    	   outputs = State.ERROR;  
                    	}
	            }
	                this.outputs.set(0, outputs);
	            }
	        else {
	            throw new IllegalStateException("Erreur d'évaluation : Le Byte selector doit avoir exactement 8 entrées et 1 sortie.");
	        }}}