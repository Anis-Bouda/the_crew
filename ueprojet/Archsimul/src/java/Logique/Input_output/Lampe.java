package Logique.Input_output ;
import Logique.Principale.*;
import java.util.Objects;
public class Lampe extends Composant {
    
    public Lampe(String id, int x, int y) {
        super(id, x, y);
        // y a juste une entree
        this.addInput(State.UNKNOWN);
        // une sortie pour respect le contrat de output 
        this.addOutput(State.UNKNOWN);
    }

    @Override
    public void evaluate() {
    	 
    		 // recuperer l'entrée
    		 State inputs = this.inputs.get(0);
    	     // on affiche l'etat de la lampe 
    	     System.out.println("État de la lampe " + this.id + " : " + inputs);
            // Mettre à jour l'état de la lampe
            this.state = inputs;
            if(state == State.TRUE) {
            	System.out.println("la lampe est allume yayyyyyyyyy 🟡");
            }
            // Mettre l'état de sortie à UNKNOWN pour respecter le contrat
            this.outputs.set(0, State.UNKNOWN);
    	 }
      
    
 
    
    public State getState() {
    	return state;
    }

    @Override
    public String toString() {
        return "Lampe {" + "id='" + id + '\'' + ", state=" + state + ", x=" + this.P.getX() + ", y=" + this.P.getY() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, this.P.getX(),this.P.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
             return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
        Lampe lampe = (Lampe) o;
        return this.P.getX() == lampe.P.getX() && this.P.getY() == lampe.P.getY() && Objects.equals(id, lampe.id) && state == lampe.state;
    }
}
