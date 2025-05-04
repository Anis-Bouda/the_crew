package Logique.Arithmetics;
import Logique.Principale.*;
public class MULTPLAY extends Composant {
     private int n;
     public MULTPLAY(String id, int n, int x, int y) {
     super(id, x, y);
     this.n = n;
     /*initialisation des entrées A[n]*/
     for (int i = 0; i < 2 * n; i++) {
    	 	this.addInput(State.UNKNOWN); 
     }
     /*initialisation de la sortie */
     for (int i = 0; i < 2 * n; i++) {
    	 this.addOutput(State.UNKNOWN);
     }}

    @Override
	public void evaluate() {
    	int a=0;
    	int b=0;
    	int caserr=0;
    	int casunk=0;
    	int res; 
    	if(this.inputs.size() == 2 * n && this.outputs.size() == 2 * n) {
    		for(int i=0;i< 2 * n;i++) {
    			if (this.inputs.get(i)==State.ERROR) {
    				caserr=caserr+1;
    			}
    			if(this.inputs.get(i)==State.UNKNOWN) {
    				casunk=casunk+1;
    	   }}
    		if(caserr != 0) {
    			for(int i=0; i< 2*n; i++) {
    				this.outputs.set(i,State.ERROR);
    			}
    	   return;
           }
    	   if(casunk != 0) {
    		   for(int i=0; i< 2*n; i++) {
    			   this.outputs.set(i,State.UNKNOWN);
    		   }
    	   return;
    	   }
           for(int i=0;i<n;i++) {
        	   if(this.inputs.get(i)==State.TRUE) {  
        		   a |= (1 << i);
        	   }

        	   if(this.inputs.get(n+i)==State.TRUE) {
        		   b |= (1 << i);
                } 
			}

          /*on calcule le produit en decimale */
          res = a * b;
          /*on vas convertir en binaire et on le stocke dans outputs */
          for (int i = 0; i < 2 * n; i++) {
        	  if ((res & (1 << i)) != 0) {
        		  this.outputs.set(i, State.TRUE);
        	  }
        	  else {
        		  this.outputs.set(i, State.FALSE);
          }
		}
	}	}
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
		 MULTPLAY objet=(MULTPLAY) obj;
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