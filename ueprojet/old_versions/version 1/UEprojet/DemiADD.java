class DemiADD extends Composant{

    public DemiADD (String id)
    {
        super(id,2,2);
    }

    public void evaluate()
    {
        this.outputs[0]=this.inputs[0] ^ this.inputs[1];
        this.outputs[1]=this.inputs[0] && this.inputs[1];
    }
}