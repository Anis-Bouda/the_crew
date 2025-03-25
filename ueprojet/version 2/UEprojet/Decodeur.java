
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
         if(input1==State.FALSE) {
        	 if(input2==State.FALSE) {
        		 output1=State.FALSE;
        		 output2=State.FALSE;
        		 output3=State.FALSE;
        		 output4=State.TRUE;
        	 }
        	 else if(input2==State.TRUE) {
        		 output1=State.FALSE;
        		 output2=State.FALSE;
        		 output3=State.TRUE;
        		 output4=State.FALSE;
        }}
         if(input1==State.TRUE) {
        	 if(input2==State.FALSE) {
        		 output1=State.FALSE;
        		 output2=State.TRUE;
        		 output3=State.FALSE;
        		 output4=State.FALSE;
        	 }
        	 else if(input2==State.TRUE) {
        		 output1=State.TRUE;
        		 output2=State.FALSE;
        		 output3=State.FALSE;
        		 output4=State.FALSE;
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