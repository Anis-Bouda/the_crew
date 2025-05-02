public class RAM extends Composant {
	// Tableau pour stocker les données
	private State[] memory;
	// la taille de la memoire 
	private static final int MEMORY_SIZE = 256;
	
	 public RAM(String id, int x, int y) {
	        super(id, x, y);
	        this.memory = new State[MEMORY_SIZE];
	        
	        for(int i =0; i<MEMORY_SIZE; i++) {
	        	memory[i] = State.UNKNOWN;
	        }
	        // l'adresse
	        this.addInput(State.UNKNOWN);
	        // donnée
	        this.addInput(State.UNKNOWN);
	        // signal ECRITURE/LECTURE 
	        this.addInput(State.UNKNOWN);
	        // la sortie dans la lecture 
	        this.addOutput(State.UNKNOWN);
	  }
@Override 
	 public void evaluate() {
	        if (this.inputs.size() == 3 && this.outputs.size() == 1) {
	           // on recupere les inputs 
	        	  State adresse = this.inputs.get(0);
	        	  State donnée = this.inputs.get(1);
	        	  State control = this.inputs.get(2);
	        	  State outputs = State.UNKNOWN;
	        
	              if(control == State.UNKNOWN  || adresse == State.UNKNOWN ) {
	            	  outputs = State.UNKNOWN;
	              }
	              else if(control == State.ERROR || adresse == State.ERROR ) {
	            	  outputs = State.ERROR;
	              }
	              else {
	            	  // adresse ou stocker notre donnée dans la memoire 
	            	  int address = adresse.ordinal() % MEMORY_SIZE;
 	            	  if(control == State.TRUE) {
 	            		  if(donnée != State.UNKNOWN)
 	            		      // Ecriture dans la memoire lorsque la donnée est
 	            			  // differente de unknown 
 	            		      memory[address] = donnée;
 	            		      outputs = donnée;
	                      }
	                  else if(control == State.FALSE) {
	                	  // Lecture de la memoire 
	        	          outputs = memory[address];
	              }}
	        	  this.outputs.set(0, outputs);
      }
  else {
      throw new IllegalStateException("Erreur d'évaluation : La RAM doit avoir exactement 3 entrées et 1 sortie.");
  }}}