package projet;

public class BasculeSR extends Composant {
   private boolean state; 
   
   private BasculeSR(String id, int x, int y) {
	    super(id,x,y);
	    this.state = false; 
   }
   
   public void Calculer() {
	   boolean S = inputs.get(0).getstate();
       boolean R = inputs.get(1).getstate();
       
	   if(S && R) {
		   throw new IllegalArgumentException("etat indefini");
	   }
	   else if(S) {
		   state = true; 
	   }
	   else if(R) {
		   state = false;
	   }
	   // dans le case S=0 et R=0, pas de changement a lentrée
	   for (int i = 0; i < outputs.size(); i++) {
   		outputs.get(i).setstate(state);
  }}}
