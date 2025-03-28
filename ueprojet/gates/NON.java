
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
            if(input1==State.ERROR || input1==State.UNKNOWN)
            {
                output=input1;
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
}