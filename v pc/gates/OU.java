package gates ;

public class OU extends Composant {

    public OU(String id, int x , int y)
    {
        super(id,x,y);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
    }

    @Override
    public void evaluate()
    {
        if (this.inputs.size() == 2 && this.outputs.size()==1)
        {
            State input1=this.inputs.get(0);
            State input2=this.inputs.get(1);
            State output;
            if(input1==State.ERROR || input2==State.ERROR)
            {
                output=State.ERROR;
            }
            else
            {
                if(input1==State.UNKNOWN && input2==State.UNKNOWN)
                {
                    output=State.UNKNOWN;
                }
                else
                {
                    if(input1==State.TRUE || input2==State.TRUE)
                    {
                        output=State.TRUE;
                    }
                    else
                    {    
                        output=State.FALSE;
                    }
                }
            }
            this.outputs.set(0,output);
            this.state=output;
        }
        else
        {
            throw new IllegalStateException("Erreur d'évaluation : La porte OU doit avoir exactement 2 inputs et 1 output.");
        }
    }
}