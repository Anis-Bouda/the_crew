Class MULT2X1 extends Composant{

    public MULT2X1 (String id)
    {
        super(id,3,1);
    }

    public void evaluate()
    {
        if(this.inputs[2]==false)
        {
            this.outputs[0]=this.inputs[0];
        }
        else
        {
            this.outputs[0]=this.inputs[1];
        }
    }
}