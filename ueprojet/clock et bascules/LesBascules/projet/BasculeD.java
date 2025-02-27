package projet;

public class BasculeD extends Composant {
      private boolean state; 
      public BasculeD(String id, int x, int y) {
    	  super(id,x,y);
    	  this.state = false; 
      }
      
      public void Calculer() {
    	 boolean D = inputs.get(0).getstate();
    	 state = D; 
    	  for (int i = 0; i < outputs.size(); i++) {
		   		outputs.get(i).setstate(state);
	   }}}
