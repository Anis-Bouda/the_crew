package wiring;
import Principale.*;
public class Pin extends Composant {
	// true = il fournit un signal
	// false = il recoit un signal 
	private boolean isinput;
	
	public Pin(String id, int x, int y, boolean isinput) {
		super(id,x,y);
		this.isinput = isinput;
		
		if(isinput) {
			// la pin d'entrée recoit un etat par
			// l'utilisateur ou un autre composant 
			// il fournit un signal donc il a une sortie 
			 this.addOutput(State.UNKNOWN);
		}
		else {
			// la pin de sortie recoit un signal , le pin a une entree
			// elle va mettre a jour son etat
			this.addInput(State.UNKNOWN);
		}
	}
	
	// recuperer l'etat d'une pin 
	public State getPinState() {
		return this.state;
	}
	
	public void setState(State etat) {
		this.state = etat;
	}
	
	@Override
    public void evaluate() {
		if(isinput) {
			if(this.outputs.size() == 1) {
				// la sortie prend la valeur de l'etat du commposant
				this.outputs.set(0, this.state);
			}
			else {
				throw new IllegalStateException("Erreur d'évaluation : La Pin doit avoir exactement 1sortie car elle fournit un signal.");
			}
		}
		else {
			if(this.inputs.size() == 1) {
				// l'etat sera met a jour 
				this.state = this.inputs.get(0);
			}
			else {
				throw new IllegalStateException("Erreur d'évaluation : La Pin doit avoir exactement 1 entrée car elle recoie un signal.");
			}
		}
	}
}


