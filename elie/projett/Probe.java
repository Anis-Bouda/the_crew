public class Probe extends Composant {
	// Probe permet de serveiller l'etat d'un signal sans le modifier
	// donc il sert a lire l'etat d'un signal 
	public Probe(String id, int x, int y) {
        super(id, x, y);
        // y a juste une entree qu'on va lire 
        this.addInput(State.UNKNOWN);
  }
	
  @Override
   public void evaluate() {
        if (this.inputs.size() == 1) {
              // on recupere l'entree 
        	  State inputs = this.inputs.get(0);
        	  // on fais un print 
        	  System.out.println("etat du signal de la probe" +this.id + ": est :" + inputs);
         }
        else {
            throw new IllegalStateException("Erreur d'évaluation : Le Probe doit avoir exactement 1 entrée");
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
