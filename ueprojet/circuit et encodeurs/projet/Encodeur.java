package projet;

public class Encodeur extends Composant{
	  public Encodeur(String id, int x, int y) {
	        super(id,x,y);
	  }
	  
	 public void Calculer() {
	    int valeur = 0;
	    for (int i = 0; i < inputs.size(); i++) {
	       if (inputs.get(i).getstate()) {
	    	   // si c'est true, le bit a la position i sera activé
	             valeur |= (1 << i);
	       }}
	    // si positif, alors state c'est true
	        state = valeur > 0;
	    }}

