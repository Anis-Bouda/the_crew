package projet;

public class BasculeJK extends Composant{
	private boolean state; 
	   
	   private BasculeJK(String id, int x, int y) {
		    super(id,x,y);
		    this.state = false; 
	   }
	   
	   public void Calculer() {
		   boolean J = inputs.get(0).getstate();
	       boolean K = inputs.get(1).getstate();
		   
		   if(J && K) {
			   state = !state;
		   } 
		   else if(J) {
			   state = true; 
		   }
		   else if(K) {
			   state = false;
		   }
		   for (int i = 0; i < outputs.size(); i++) {
		   		outputs.get(i).setstate(state);
	   }}}
