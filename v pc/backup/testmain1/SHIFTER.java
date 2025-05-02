public class SHIFTER extends Composant {
	private int n;
    public SHIFTER(String id, int n, int x, int y) {
    super(id, x, y);
    this.n = n;

    /*initialisation des entrées */
    for (int i = 0; i < n+1; i++) {
    	this.addInput(State.UNKNOWN); 
    }

    /*initialisation de la sortie */
    for (int i = 0; i < n; i++) {
    	this.addOutput(State.UNKNOWN);
    }
    }

    @Override
    public void evaluate()
    {
    	int caserr = 0 ;
    	int casind = 0 ;
    	for(int i=0;i<n;i++)
    	{
    		if(this.inputs.get(i) == State.ERROR)
    		{
    			caserr=caserr+1;
    		}
    		if(this.inputs.get(i) == State.UNKNOWN)
    		{
    			casind=casind+1;
    		}
        }
    	
    	if(this.inputs.get(n+1) == State.ERROR || caserr != 0)
    	{
    		for(int i=0; i< n; i++)
    		{
    			this.outputs.set(i,State.ERROR);
    		}
    		return;
        }
    	
    	if( this.inputs.get(n+1) == State.UNKNOWN || casind != 0)
    	{
    		for(int i=0; i< n; i++)
    		{
    			this.outputs.set(i,State.UNKNOWN);
    		}
    		return;
        }

    	boolean shiftLeft = this.inputs.get(n+1) == State.TRUE;
    	if (shiftLeft) {
    		for (int i = 0; i < n; i++) {
    			this.outputs.set(i, this.inputs.get(i + 1));
    		}
    		this.outputs.set(n , State.FALSE); 
    	}
    	else {
    		for (int i = 1; i < n; i++) {
    			this.outputs.set(i, this.inputs.get(i - 1));
    		}
    		this.outputs.set(0, State.FALSE);
        }
     }
}