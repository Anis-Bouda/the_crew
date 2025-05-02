public class Multiplier extends Composant {
     public Multiplier(String id, int x, int y) {
     super(id, x, y);
     /*initialisation des entrées A[n]*/
     for (int i = 0; i < 4; i++) {
    	 	this.addInput(State.UNKNOWN); 
     }
     /*initialisation de la sortie */
     for (int i = 0; i < 4; i++) {
    	 this.addOutput(State.UNKNOWN);
     }}

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
	public void evaluate() {
    	int a=0;
    	int b=0;
    	int caserr=0;
    	int casunk=0;
    	int res; 
    	if(this.inputs.size() == 4 && this.outputs.size() == 4) {
    		for(int i=0;i< 4;i++) {
    			if (this.inputs.get(i)==State.ERROR) {
    				caserr=caserr+1;
    			}
    			if(this.inputs.get(i)==State.UNKNOWN) {
    				casunk=casunk+1;
    	   }}
    		if(caserr != 0) {
    			for(int i=0; i< 4; i++) {
    				this.outputs.set(i,State.ERROR);
    			}
    	   return;
           }
    	   if(casunk != 0) {
    		   for(int i=0; i< 4; i++) {
    			   this.outputs.set(i,State.UNKNOWN);
    		   }
    	   return;
    	   }
           for(int i=0;i<2;i++) {
        	   if(this.inputs.get(i)==State.True) {
        		   a |= (1 << i);
        	   }

        	   if(this.inputs.get(2+i)==State.True) {
        		   b |= (1 << i);
          }}

          /*on calcule le produit en decimale */
          res = a * b;
          /*on vas convertir en binaire et on le stocke dans outputs */
          for (int i = 0; i < 4; i++) {
        	  if ((res & (1 << i)) != 0) {
        		  this.outputs.set(i, State.True);
        	  }
        	  else {
        		  this.outputs.set(i, State.False);
          }}}}}
