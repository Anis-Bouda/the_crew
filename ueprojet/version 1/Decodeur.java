
public class Decodeur extends Composant{
	public Decodeur(String id, int x, int y) {
        super(id,x,y);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
   }
	
    public void evaluate() {
    	 if (this.inputs.size() == 2 && this.outputs.size()==4)
    	 {
         State input1=this.inputs.get(0);
         State input2=this.inputs.get(1);
         // les outputs par defaut 
         State output1=State.UNKNOWN;
         State output2=State.UNKNOWN;
         State output3=State.UNKNOWN;
         State output4=State.UNKNOWN;
         
         if(input1==State.UNKNOWN) {
        	  output1=State.UNKNOWN;
        	  output2=State.UNKNOWN;
        	  output3=State.UNKNOWN;
        	  output4=State.UNKNOWN;
         }
         if(input1==State.ERROR) {
        	 output1=State.ERROR;
       	  	 output2=State.ERROR;
       	     output3=State.ERROR;
       	     output4=State.ERROR;
         }
         if(input1==State.False) {
        	 if(input2==State.False) {
        		 output1=State.False;
        		 output2=State.False;
        		 output3=State.False;
        		 output4=State.True;
        	 }
        	 else if(input2==State.True) {
        		 output1=State.False;
        		 output2=State.False;
        		 output3=State.True;
        		 output4=State.False;
        }}
         if(input1==State.True) {
        	 if(input2==State.False) {
        		 output1=State.False;
        		 output2=State.True;
        		 output3=State.False;
        		 output4=State.False;
        	 }
        	 else if(input2==State.True) {
        		 output1=State.True;
        		 output2=State.False;
        		 output3=State.False;
        		 output4=State.False;
    }}   
         this.outputs.set(0,output1);
         this.outputs.set(1, output2);
         this.outputs.set(2, output3);
         this.outputs.set(3, output4);
         this.state=output1;
         this.state=output2;
         this.state=output3;
         this.state=output4;
    }
    	 else
	        {
	           throw new IllegalStateException("Erreur d'évaluation : La porte Decodeur doit avoir exactement 2 inputs et 4 outputs.");
	        }
    }}