package Logique.wiring;
import Logique.Principale.*;
public class Ground extends Composant {   
    public Ground(String id, int x, int y) {
        super(id, x, y);
        this.addInput(State.FALSE);   
    }
    
    @Override
    public void evaluate() {
        if (this.inputs.size() == 1 && this.outputs.size() == 0) 
        {
        	 this.inputs.set(0, State.FALSE);
        	 this.state=State.FALSE;
        }
        else
        {
            throw new IllegalStateException("Ground : doit une seule entree sans sortie");
        
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

