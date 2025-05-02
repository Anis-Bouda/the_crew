package plexers ;
import Principale.*;
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
         
         // le cas de input1 est FALSE 
         if(input1==State.FALSE) {
        	 // le cas de input2 est FALSE
        	 if(input2==State.FALSE) {
        		 outputs[0]=State.TRUE;
        		 outputs[1]=State.FALSE;
        		 outputs[2]=State.FALSE;
        		 outputs[3]=State.FALSE;
        	 }
        	 // le cas de input2 est TRUE 
        	 else if(input2==State.TRUE) {
        		 outputs[0]=State.FALSE;
        		 outputs[1]=State.TRUE;
        		 outputs[2]=State.FALSE;
        		 outputs[3]=State.FALSE; 	
        }}
         
         // le cas de input1 est TRUE 
         if(input1==State.TRUE) {
        	 // le cas de input2 est FALSE 
        	 if(input2==State.FALSE) {
        		 outputs[0]=State.FALSE;
        		 outputs[1]=State.FALSE;
        		 outputs[2]=State.TRUE;
        		 outputs[3]=State.FALSE;
        	 }
        	 // le cas de input2 est TRUE 
        	 else if(input2==State.TRUE) {
        		 outputs[0]=State.FALSE;
        		 outputs[1]=State.FALSE;
        		 outputs[2]=State.FALSE;
        		 outputs[3]=State.TRUE;
        }}   
         
         for (int i = 0; i < outputs.length; i++) {
             this.outputs.set(i, outputs[i]);
         }
    }
    	 else
	        {
	           throw new IllegalStateException("Erreur d'évaluation : La porte Decodeur doit avoir exactement 2 inputs et 4 outputs.");
	        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}