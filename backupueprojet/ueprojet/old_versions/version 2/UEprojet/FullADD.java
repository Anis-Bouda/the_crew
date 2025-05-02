Class FullADD extends Composant{

    public FullADD (String id)
    {
        super(id,3,2);
    }

    public void evaluate()
    {
        this.outputs[0]=this.inputs[0] ^ this.inputs[1] ^ this.inputs[2];
        this.outputs[1]=(this.inputs[0] && this.inputs[1]) || (this.inputs[1] && this.inputs[2] ) || (this.inputs[0] && this.inputs[2]);
    }
}