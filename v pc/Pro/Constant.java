package projet;

public class Constant extends Composant {  
	// le composant Constant genere un signal constant 
	// il a une seul sortie qui est egal a la valeur donnée
	private State valeur;
	
    public Constant(String id, int x, int y, State valeur) {
        super(id, x, y);
        this.valeur = valeur;
        this.addOutput(valeur);   
    }
   
    public State getValeur() {
    	return valeur;
    }
    
    // mettre a jour la valeur pendant l'execution du programme
    public void setValeur(State newValeur) {
    	this.valeur = newValeur;
    	this.outputs.set(0, newValeur);
    }
    
    // ya pas d'entree dans Constant
    // la sortie prend exactement la valeur de State
    @Override
    public void evaluate() {
    	if(this.outputs.size() == 1) {
        	 this.outputs.set(0, valeur);
    	}
    	else {
    		throw new IllegalStateException("Erreur d'évaluation : Constant doit avoir exactement 1 sortie.");
     }}}

