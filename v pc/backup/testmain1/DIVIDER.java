public class DIVIDER extends Composant {
	private int n;
	public DIVIDER(String id, int n, int x, int y) {
	super(id, x, y);
	this.n = n;

	/*initialisation des entrées A[n]*/
	for (int i = 0; i < 2 * n; i++) {
		this.addInput(State.UNKNOWN); 
	}

	/*initialisation de la sortie */
	for (int i = 0; i < 2 * n; i++) 
	{
		this.addOutput(State.UNKNOWN);
	}
    }

	@Override
	public void evaluate()
	{
		int cas0=0;
		int caserr=0;
		int casind=0;
		int a=0;
		int b=0;
		int quotient;
		int reste;
		for(int i=0;i< 2 * n;i++)
		{
			if (this.inputs.get(i)==State.ERROR)
			{
				caserr=caserr+1;
			}
			if(this.inputs.get(i)==State.UNKNOWN)
			{
				casind=casind+1;
			}
        }
        for(int i=0;i< n;i++){
			if(this.inputs.get(n+i)==State.FALSE)
			{
				cas0=cas0+1;
			}
		}
		if(caserr != 0)
		{
			for(int i=0; i< 2*n; i++)
			{
				this.outputs.set(i,State.ERROR);
			}
			return;
		}
		if(casind != 0)
		{
			for(int i=0; i< 2*n; i++)
			{
				this.outputs.set(i,State.UNKNOWN);
			}
			return;
		}

		if(cas0 == this.n)
		{
			for(int i=0; i< 2*n; i++)
			{
				this.outputs.set(i,State.ERROR);
			}
			return;
		}

		for(int i=0;i<n;i++)
		{
			if(this.inputs.get(i)==State.TRUE)
			{
				a |= (1 << i);
			}
			if(this.inputs.get(n+i)==State.TRUE)
			{
				b |= (1 << i);
			}
		}
		quotient= a/b;
		reste= a % b;
		/*on stocke le quotient dans le 1er tableau */
		
		for (int i = 0; i < n; i++) {
			if ((quotient & (1 << i)) != 0) {
				this.outputs.set(i, State.TRUE);
			}
			else {
				this.outputs.set(i, State.FALSE);
			}
		}

		/*on stocke le reste dans le 2 eme tableau */
		for (int i = 0; i < n; i++) {
			if ((reste & (1 << i)) != 0) {
				this.outputs.set(n + i, State.TRUE);
			}
			else {
				this.outputs.set(n + i, State.FALSE);
			}
		}
	}
}