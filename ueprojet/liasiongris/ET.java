public class ET extends Composant {

    public ET(String id, int x, int y)
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
            System.out.println("💡 ET " + id + " inputs = " + inputs);

            if(input1==State.ERROR || input2==State.ERROR)
            {
                output=State.ERROR;
            }
            else
            {
                if(input1==State.False || input2==State.False)
                {
                    output=State.False;
                }
                else
                    if(input1==State.UNKNOWN || input2==State.UNKNOWN)
                    {
                        output=State.UNKNOWN;
                    }
                    else 
                    { 
                        /*c'est du plus juste au cas ou, les conditions avant le verifie déjà*/
                        if(input1==State.True)
                        {
                            output=input2;
                        }
                        else
                        {
                            output=State.False;
                        }
                    }
            }
            this.outputs.set(0,output);
            System.out.println("💡 ET " + id + " outputs= " + outputs);
            this.state=output;
        }
        else
        {
            throw new IllegalStateException("Erreur d'évaluation : La porte ET doit avoir exactement 2 inputs et 1 output.");
        }
    }
}
