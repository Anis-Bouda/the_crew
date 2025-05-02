public class BRS extends Composant {
	public BRS(String id, int x, int y) {
        super(id,x,y);
        this.addInput(State.UNKNOWN); // S
        this.addInput(State.UNKNOWN); // R
        this.addOutput(State.UNKNOWN); // Q
        this.addOutput(State.UNKNOWN); // Q bar
   }
	
    public void evaluate() {
    	 if (this.inputs.size() == 2 && this.outputs.size()==2)
    	 {
         State S=this.inputs.get(0);
         State R=this.inputs.get(1);
         // les outputs
         State[] outputs = new State[2];
         // recuperer les outputs 
         for (int i = 0; i < outputs.length; i++) {
             outputs[i] = this.outputs.get(i);
         }
         
         // le cas ou y a un unknown
         if(S == State.UNKNOWN || R == State.UNKNOWN) {
        	 for (int i = 0; i < outputs.length; i++) {
                 outputs[i] = State.UNKNOWN;
             }
         }
         
         // le cas ou y a un error
         else if(S == State.ERROR || R == State.ERROR) {
        	 for (int i = 0; i < outputs.length; i++) {
                 outputs[i] = State.ERROR;
             }
         }
         
         else if(S == State.True && R == State.True) {
        	 for (int i = 0; i < outputs.length; i++) {
                 outputs[i] = State.ERROR;
             }
         }
         
         else if(S == State.True && R == State.False) {
                 outputs[0] = State.True;
                 outputs[1] = State.False;
             }
         
         else if(S == State.False && R == State.True) {
        	 outputs[0] = State.False;
             outputs[1] = State.True;
         }
         
         // dans le cas S==False et R==False, on modifie pas Q et Qbar
         for (int i = 0; i < outputs.length; i++) {
             this.outputs.set(i, outputs[i]);
         }
    }
    	 else
	        {
	           throw new IllegalStateException("Erreur d'évaluation : La Bascule RS doit avoir exactement 2 inputs et 2 outputs.");
	        }
    }}