package projet;

public class BasculeT extends Composant{
    private boolean state;
    public BasculeT(String id, int x, int y) {
    	super(id,x,y);
    	this.state = false;
    }
    
    public void Calculer() {
    	boolean T = inputs.get(0).getstate();
    	if(T) {
    		state = !state;
    	}
    	for (int i = 0; i < outputs.size(); i++) {
	   		outputs.get(i).setstate(state);
   }}}
