public class ET extends Composant {

    public ET(String id, int x,int y )
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
                if(input1==State.FALSE || input2==State.FALSE)
                {
                    output=State.FALSE;
                }
                else
                    if(input1==State.UNKNOWN || input2==State.UNKNOWN)
                    {
                        output=State.UNKNOWN;
                    }
                    else 
                    { 
                        /*c'est du plus juste au cas ou, les conditions avant le verifie déjà*/
                        if(input1==State.TRUE)
                        {
                            output=input2;
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
            throw new IllegalStateException("Erreur d'évaluation : La porte ET doit avoir exactement 2 inputs et 1 output.");
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