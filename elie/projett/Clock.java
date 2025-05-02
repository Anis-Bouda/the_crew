public class Clock extends Composant{
	/*définit la période après laquelle l'horloge change d'état.*/
    private int period;
    /* compteur */
    private int cmpt;
    
    public Clock(String id, int x, int y, int period) {
    	super(id,x,y);
    	this.period = period;
    	this.cmpt = 0;
    	/* initialisitation de letat a unknown */
    	this.addInput(State.UNKNOWN);
    	this.addOutput(State.UNKNOWN);
   }
    
    @Override
    public void  evaluate() {
    	if (this.inputs.size() == 1 && this.outputs.size() == 1)
   	 {
        State input = this.inputs.get(0);;
        State output = State.UNKNOWN;
        
        /* le cas ou le state est unknown ou error*/
    	if(input == State.UNKNOWN || input== State.ERROR) {
    		output = input;
    	}
    	else {	
    	    cmpt++;
        	if(cmpt >= period) {
        		 if(input == State.FALSE) {
        			 output = State.TRUE;
        		 }
        		 else if(input == State.TRUE) {
        			 output = State.FALSE;
        		 }
        		 cmpt = 0;
        }}
        this.outputs.set(0,output);
        this.state=output; 
    }
    	else
        {
            throw new IllegalStateException("Erreur d'évaluation : Clock doit avoir exactement 1 input et 1 output.");
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
    Clock objet = (Clock) obj;
    return this.period == objet.period && this.cmpt == objet.cmpt;
}

   @Override
    public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + this.period;
    result = 31 * result + this.cmpt;
    return result;
    }

    @Override
    public String toString() {
    return super.toString() + ", period = " + this.period + ", compteur=" + this.cmpt;
    }
}