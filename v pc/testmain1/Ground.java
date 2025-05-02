package projet;

public class Ground extends Composant {   
    public Ground(String id, int x, int y) {
        super(id, x, y);
        // ya pas d'entree
        // la sortie est toujours a 0, FALSE
        this.addInput(State.False);   
    }
    
    @Override
    public void evaluate() {
        if (this.inputs.size() == 1 && this.outputs.size() == 0) 
        {
        	 this.inputs.set(0, State.False);
        	 this.state=State.False;
        	 this.outputs.set(0, State.True);
        }
        else
        {
            throw new IllegalStateException("Ground : doit une seule entree et pas de sorties.");
        
        }
    }
}

