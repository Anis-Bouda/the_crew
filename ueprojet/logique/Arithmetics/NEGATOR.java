package Arithmetics;
import java.util.ArrayList;
import java.util.List;
import Principale.*;
import gates.*;

public class NEGATOR extends Composant {
     private int n;
     private List<NON> NONgates;
     public NEGATOR(String id, int n, int x, int y) {
     super(id, x, y);
     this.n = n;
     this.NONgates = new ArrayList<>();

     /*initialisation des entrées */
     for (int i = 0; i < n; i++) {
    	 this.addInput(State.UNKNOWN); 
     }

     /*initialistaion des sorties */
     for (int i = 0; i < n; i++) {
    	 this.addOutput(State.UNKNOWN);
     }
     for (int i = 0; i < n; i++) 
     {
    	 this.NONgates.add(new NON("NON" + i, x, y));
     }
     }

     @Override
     public void evaluate()
     {
    	 if (this.inputs.size() == n && this.outputs.size() == n )
    	 {
    		 for(int i=0;i<n;i++)
    		 {
    			 this.NONgates.get(i).setInputs(0, this.inputs.get(i));
    			 NONgates.get(i).evaluate();
    			 this.outputs.set(i,NONgates.get(i).getOutput(0));
    	     }
    	 }
    	 else
    	 {
    		 throw new IllegalStateException("ERREUR SUR LE NOMBRE D'ARGUMENTS");
    	 }
     }
}