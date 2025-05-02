package wiring;
import Principale.*;
public class BitExtender extends Composant {
	private int inputwidth;
	private int outputwidth;
	/* pour le Bit Extender, on a deux cas 
	// soit en zero extension, on ajoute des zeros 
	// soit en sign extension, on duplique le bit 
	// le plus a gauche */
	private ExtensionType extn;
	
	 public BitExtender(String id, int x, int y,
     int inputwidth, int outputwidth, ExtensionType extn) {
	        super(id, x, y);
	        this.inputwidth = inputwidth;
	        this.outputwidth = outputwidth;
	        this.extn = extn;
	        
	     // Vérification des tailles
	        if (inputwidth <= 0 || outputwidth <= 0 || outputwidth < inputwidth) {
	            throw new IllegalArgumentException("Les tailles doivent être positives et outputWidth >= inputWidth.");
	        }
	        
	        // l'entrée
	        for(int i=0;i<inputwidth;i++)
	        {
	            this.addInput(State.UNKNOWN);
	        }
	        // sortie 
	        for(int i=0;i<outputwidth;i++)
	        {
	        this.addOutput(State.UNKNOWN);
	  }}
     
	  @Override
	   public void evaluate() {
	        if (this.inputs.size() == inputwidth && this.outputs.size() == outputwidth) {
	           // on recupere le inputs 
	        	State[] inputs = new State[inputwidth];
	        	State[] outputs = new State[outputwidth];
	        	
	        	for(int i=0; i<outputwidth; i++) {
	        	   outputs[i] = State.UNKNOWN;
	        	}
	           // le cas ou le state est unknown ou erreur 
	           // on propage erreur ou unknown a la sortie 
	        	int cptU = 0;
	            for (int j = 0; j < inputs.length; j++) {
	                inputs[j] = this.inputs.get(j);
	                if (inputs[j] == State.UNKNOWN) {
	                    cptU++;
	            }}
	            // si y a une entree qui est unknown alors les sorties sont unknown 
	            if (cptU > 0) {
	                for (int i = 0; i < outputs.length; i++) {
	                    outputs[i] = State.UNKNOWN;
	            }} 
	            else {
	                int cptE = 0;
	                for (int j = 0; j < inputs.length; j++) {
	                    if (inputs[j] == State.ERROR) {
	                        cptE++;
	                }}
	                if (cptE > 0) {
	                    for (int i = 0; i < outputs.length; i++) {
	                        outputs[i] = State.ERROR;
	                }} 
	           else {
	        	   int difference = outputwidth - inputwidth;
	        	   for(int i=difference; i< outputwidth; i++) {
	        		      outputs[i] = inputs[i-difference];
	        	   }
	        	   switch(extn) {
	        	   case ZERO:
	        		   for(int i=0; i< difference; i++) {
	        		      outputs[i] = State.FALSE;
	        	       }
	        		   break;
	        	   case ONE:
	        		   for(int i=0; i< difference; i++) {
		        		      outputs[i] = State.TRUE;
		        	       }
	        		   break;
	        	   case SIGN:
	        		   State signBit = inputs[inputwidth- 1]; 
	        		   for(int i=0; i< inputwidth; i++) {
		        		      outputs[i] = signBit;
		        	       }
	        		   break;
	        	   }}}
	        	   // la sortie
	        	   for (int i = 0; i < outputs.length; i++) {
	                   this.outputs.set(i, outputs[i]);
	               }
	           }
	   	        else {
	   	            throw new IllegalStateException("Erreur d'évaluation : Le BitExtender doit avoir exactement 1 entrée et 1 sortie.");
	   	    }}}

