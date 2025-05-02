package projet;

public class ShiftRegister extends Composant {
	// taile du registre 
	private int size;
	// le shift type 
	// soit c'est logique ou arithmetique ou circulaire
	private ShiftType type;
	// si true donc decalage a gauche 
	// si false donc decalage a droite 
	private boolean direction;

	 public ShiftRegister(String id, int x, int y,
	 int size, ShiftType type, boolean direction) {
	        super(id, x, y);
	        this.size = size;
	        this.type = type;
	        this.direction = direction;
	        // entrée de données
	        for(int i=0; i<size; i++) {
		           this.addInput(State.UNKNOWN);
		        }
	        // entree des sorties
	        for(int i=0; i<size; i++) {
	           this.addOutput(State.UNKNOWN);
	        }
	  }

	    public void evaluate() {
	        if (this.inputs.size() == size && this.outputs.size() == this.size) {
	            State outputs[] = new State[size];
	            // declaage a gauche 
	            if(direction) {
	            	ShiftLeft(outputs);	
	            }
	            else {
	            	// decalage a droite 
	            	ShiftRight(outputs);
	            }
	            for(int i=0; i<size; i++) {
	 	           this.outputs.set(i, outputs[i]);
	 	        }
	       }
	       else {
	            throw new IllegalStateException("Erreur d'évaluation : Le Shift Register doit avoir exactement" + size + "entrées et" + size + "sortie.");
	 }}
  
      // decalage a gauche 
      public void ShiftLeft(State[] outputs) {
    	  switch(type) {
    	  // on decale tous a gauche 
    	  // et on ajoute false au bit 0
    	  case LOGIQUE:
    		  for (int i = 0; i < size - 1; i++) {
                  outputs[i] = this.inputs.get(i + 1);
              }
    		  outputs[size - 1] = State.False;
    	      break;
    	  
    	  // c'est la meme chose que le type logique
    	  case ARITHMETIQUE: 
    		  for (int i = 0; i < size - 1; i++) {
                  outputs[i] = this.inputs.get(i + 1);
              }
    		  outputs[size - 1] = State.False;
    		  break;
    		
    	  // on decale a gauche 
    	  // et le bit zero aura la valeur de bit size-1
    	  case CIRCULAIRE:
    		  for (int i = 0; i < size - 1; i++) {
                  outputs[i] = this.inputs.get(i + 1);
              }
    		  outputs[size - 1] = this.inputs.get(0);
    		  break;
      }}
      
      // decalage a droite
      public void ShiftRight(State[] outputs) {
    	  switch(type) {
    	// on decale tous a droite
    	  // et on ajoute false au bit size -1
    	  case LOGIQUE:
    		  for (int i = size - 1; i > 0; i--) {
                  outputs[i] = this.inputs.get(i - 1);
              }
    		  outputs[0] = State.False;
    	      break;
    	  
    	  // on decale tous a droite
    	  // et on conserve le bit de signe 
    	  case ARITHMETIQUE: 
    		  State BitDeSigne = this.inputs.get(0);
    		  for (int i = size - 1; i > 0; i--) {
                  outputs[i] = this.inputs.get(i - 1);
              }
    		  outputs[0] =  BitDeSigne;
    		  break;
    		 
    	  // on decale tous a droite 
    	  // le bit de size -1 aura la valeur de bit 0 de inputs
    	  case CIRCULAIRE:
    		  for (int i = size - 1; i > 0; i--) {
                  outputs[i] = this.inputs.get(i - 1);
              }
    		  outputs[0] = this.inputs.get(size - 1);
    		  break;
      }}
  }
