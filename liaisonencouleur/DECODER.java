public class DECODER extends Composant{
	public DECODER(String id, int x, int y) {
        super(id,x,y);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
   }
	
    public void evaluate() {
    	 if (this.inputs.size() == 2 && this.outputs.size() == 4)
    	 {
         State input1=this.inputs.get(0);
         State input2=this.inputs.get(1);
         
         // un tableau de state
         State[] outputs = new State[4];
         
         // les outputs par defaut 
         for (int i = 0; i < outputs.length; i++) {
             outputs[i] = State.UNKNOWN;
         }
         
         // le cas d'unknown
         if(input1==State.UNKNOWN) {
        	 for (int i = 0; i < outputs.length; i++) {
                 outputs[i] = State.UNKNOWN;
             }
         }
         
         // le cas d'erreur
         if(input1==State.ERROR) {
        	 for (int i = 0; i < outputs.length; i++) {
                 outputs[i] = State.ERROR;
             }
         }
         
         // le cas de input1 est false 
         if(input1==State.False) {
        	 // le cas de input2 est false
        	 if(input2==State.False) {
        		 outputs[0]=State.True;
        		 outputs[1]=State.False;
        		 outputs[2]=State.False;
        		 outputs[3]=State.False;
        	 }
        	 // le cas de input2 est true 
        	 else if(input2==State.True) {
        		 outputs[0]=State.False;
        		 outputs[1]=State.True;
        		 outputs[2]=State.False;
        		 outputs[3]=State.False;
        }}
         
         // le cas de input1 est true 
         if(input1==State.True) {
        	 // le cas de input2 est false 
        	 if(input2==State.False) {
        		 outputs[0]=State.False;
        		 outputs[1]=State.False;
        		 outputs[2]=State.True;
        		 outputs[3]=State.False;
        	 }
        	 // le cas de input2 est true 
        	 else if(input2==State.True) {
        		 outputs[0]=State.False;
        		 outputs[1]=State.False;
        		 outputs[2]=State.False;
        		 outputs[3]=State.True;
        }}   
         
         for (int i = 0; i < outputs.length; i++) {
             this.outputs.set(i, outputs[i]);
         }
    }
    	 else
	        {
	           throw new IllegalStateException("Erreur d'évaluation : La porte Decodeur doit avoir exactement 2 inputs et 4 outputs.");
	        }
    }}