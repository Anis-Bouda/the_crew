Class XNOR extends Composant{

    public XNOR (String id)
    {
        super(id,2,1);
    }

    public void evaluate()
    {
        if(this.inputs[0]==this.inputs[1])
        {
            this.outputs[0]=true;
        }
        else
        {
            this.outputs[0]=false;
        }
    }
}