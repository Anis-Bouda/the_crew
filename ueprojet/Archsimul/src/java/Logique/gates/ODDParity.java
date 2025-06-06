package Logique.gates ;
import Logique.Principale.*;

public class ODDParity extends Composant {
    private int n;

    public ODDParity(String id, int n, int x, int y) {
        super(id, x, y);
        this.n = n;
        /*Initialisation des entrées A[n]*/
        for (int i = 0; i < n; i++) {
            this.addInput(State.UNKNOWN); 
        }
        this.addOutput(State.UNKNOWN);  
    }

    @Override
    public void evaluate() {
        if (this.inputs.size() == n && this.outputs.size() == 1) {
            int onesCount = 0;
            State res=State.TRUE;
            /*Compter le nombre de bits à true donc 1*/
            for (int i = 0; i < n; i++) {
                State bitState = this.inputs.get(i);
                
                if (bitState == State.ERROR) {
                    res=State.ERROR; 
                    break; 
                } 
                else 
                {
                    if (bitState == State.UNKNOWN) 
                    {
                        res=State.UNKNOWN;
                        break; 
                    }
                    else 
                    {
                        if (bitState == State.TRUE) 
                        {
                            /*on compte les bits a true donc 1 */
                            onesCount++;  
                        }
                    }
                }
            }
            if(res!=State.ERROR && res!=State.UNKNOWN)
            {
                /* Si le nombre de bits à 1 est impair, la sortie est TRUE*/
                if (onesCount % 2 == 1) 
                {
                    res=State.TRUE;
                } 
                else 
                {
                    res=State.FALSE; 
                }
            }
            this.outputs.set(0,res);
        } else {
            throw new IllegalStateException("ParityODD : doit avoir n entrées et 1 sortie.");
        }
    }
    @Override
	public boolean equals(Object obj)
	{
		if(this==obj)
		{
			return true;
		}
		if (!super.equals(obj))
		 {
			return false;
		 }
		 ODDParity objet=(ODDParity) obj;
		 return this.n==objet.n;

	}
	@Override
	public String toString()
	{
        return super.toString() + ", n=" + this.n;
	}
	@Override
	public int hashCode()
	{
		int res=super.hashCode();
		res=res*71+this.n;
		return res;
	}
}