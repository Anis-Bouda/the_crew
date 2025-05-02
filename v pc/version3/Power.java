public class Power extends Composant {   
    public Power(String id, int x, int y) {
        super(id, x, y);
        // ya pas d'entree
        // la sortie est toujours a 1, TRUE
        this.state = State.TRUE;
        this.addOutput(State.TRUE);   
    }
    
    @Override
    public void evaluate() {
        if (this.outputs.size() == 1 && this.inputs.size()==0) {
        	 this.outputs.set(0, State.TRUE);
        	 
        }
        else
        {
            throw new IllegalStateException("Power : doit une seule sortie et pas d'entree.");
        
        }
    }
}
