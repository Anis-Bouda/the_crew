public class Tunnel extends Composant {
	private String label;
	
	// le tunnel relie deux fils de meme label sans connexion physique visible
	public Tunnel(String id, int x, int y, String label) {
        super(id, x, y);
        if (label == null || label.isEmpty()) {
            throw new IllegalArgumentException("probleme dans le label");
        }
        this.label = label;
        // entree
        this.addInput(State.UNKNOWN);
        // sortie 
        this.addOutput(State.UNKNOWN);
  }
	
   public String getLabel() {
	    return label;
   }

  @Override
   public void evaluate() {
        if (this.inputs.size() == 1 && this.outputs.size() == 1) {
           // la sortie recoit l'entree 
        	  State inputs = this.inputs.get(0);
        	  this.outputs.set(0, inputs);
        }
        else {
            throw new IllegalStateException("Erreur d'évaluation : Le Tunnel doit avoir exactement 1 entrées et 1 sortie.");
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
        Tunnel objet = (Tunnel) obj;
        return this.label.equals(objet.label);
    }

    @Override
    public String toString() {
        return super.toString() + ", le label :" + this.label;
    }

    @Override
    public int hashCode() {
        int res=super.hashCode();
        res=res * 31 + this.label.hashCode();
        return res;
    }
}