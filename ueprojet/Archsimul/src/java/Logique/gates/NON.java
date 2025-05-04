package Logique.gates ;
import Logique.Principale.*;

public class NON extends Composant {

    public NON(String id, int x,int y)
    {
        super(id,x,y);
        this.addInput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
    }

    @Override
    public void evaluate()
    {
        if (this.inputs.size() == 1 && this.outputs.size()==1)
        {
            State input1=this.inputs.get(0);
            State output;
            if(input1==State.ERROR ) {
	          output=input1;
	    }
	    else if(input1==State.UNKNOWN) {
	           output = State.ERROR; 
	    }
            else
            {
                if(input1==State.TRUE)
                {
                    output=State.FALSE;
                }
                else
                {
                    output=State.TRUE;
                }
            }
            this.outputs.set(0,output);
            this.state=output;  
        }
        else
        {
            throw new IllegalStateException("Erreur d'évaluation : La porte NON doit avoir exactement 1 input et 1 output.");
        }
    }
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
