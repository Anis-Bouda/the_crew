package projet;

public class Clock extends Composant{
    //  définit la période après laquelle l'horloge change d'état.
    private int period;
    // compteur 
    private int cmpt;
    private boolean state;
    
    private Clock(String id, int x, int y, int period) {
    	super(id,x,y);
    	this.period = period;
    	this.cmpt = 0;
    	this.state = false;
    }
    
    public void tic() {
    	cmpt++;
    	if(cmpt >= period) {
    		 state = !state;
    		 cmpt = 0;
    	}
    	Calculer();
    }
    
    public void Calculer() {
    	 for (int i = 0; i < outputs.size(); i++) {
    		outputs.get(i).setstate(state);
   }}}

