public class ROM extends Composant {
	// Tableau pour stocker les données
	private State[] memory;
	// la taille de la memoire 
	private static final int MEMORY_SIZE = 256;
	
	 public ROM(String id, int x, int y) {
	        super(id, x, y);
	        this.memory = new State[MEMORY_SIZE];
	        
	        for(int i =0; i<MEMORY_SIZE; i++) {
	        	memory[i] = State.UNKNOWN;
	        }
	        // l'adresse
	        this.addInput(State.UNKNOWN);
	        // la sortie dans la lecture 
	        this.addOutput(State.UNKNOWN);
	  }
	 
	 public void evaluate() {
	        if (this.inputs.size() == 2 && this.outputs.size() == 1) {
	           // on recupere les inputs 
	        	  State adresse = this.inputs.get(0);
	        	  State outputs = State.UNKNOWN;
	        
	              if(adresse == State.UNKNOWN) {
	            	  outputs = State.UNKNOWN;
	              }
	              else {
	            	  // convertir l'adresse
	            	  int address = adresse.ordinal() % MEMORY_SIZE;
 	            	   outputs = memory[address];
	              }
	        	  this.outputs.set(0, outputs);
      }
  else {
      throw new IllegalStateException("Erreur d'évaluation : La ROM doit avoir exactement 1 entrée et 1 sortie.");
  }}}