import java.util.ArrayList;
import java.util.List;

public class Negator extends Composant {
     private List<NOT> NONgates;
     public Negator(String id, int x, int y) {
     super(id, x, y);
     this.NONgates = new ArrayList<>();

     /*initialisation des entrées */
     for (int i = 0; i < 2; i++) {
    	 this.addInput(State.UNKNOWN); 
     }

     /*initialistaion des sorties */
     for (int i = 0; i < 2; i++) {
    	 this.addOutput(State.UNKNOWN);
     }
     for (int i = 0; i < 2; i++) 
     {
    	 this.NONgates.add(new NOT("NON" + i, x, y));
     }
     }

     public void setInputState(int index, State state) {
         if (index >= 0 && index < inputs.size()) {
             inputs.set(index, state);
         } else {
             throw new IndexOutOfBoundsException("Index d'entrée invalide : " + index);
         }
     }
     
     public State getOuputState(int index) {
         if (index >= 0 && index < outputs.size()) {
         	return outputs.get(index);
         } else {
             throw new IndexOutOfBoundsException("Index d'entrée invalide : " + index);
         }
     }
     
     @Override
     public void evaluate()
     {
    	 if (this.inputs.size() == 2 && this.outputs.size() == 2 )
    	 {
    		 for(int i=0;i<2;i++)
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

