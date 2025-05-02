public class Ground extends Composant {   
    public Ground(String id, int x, int y) {
        super(id, x, y);
        // ya pas d'entree
        // la sortie est toujours a 0, FALSE
        this.addOutput(State.FALSE);   
    }
    
    @Override
    public void evaluate() {
        if (this.outputs.size() == 1) {
        	 this.outputs.set(0, State.FALSE);
    }}}