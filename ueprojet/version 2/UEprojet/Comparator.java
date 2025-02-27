Class Comparator extends Composant{

    public Comparator (String id)
    {
        super(id,2,3);
    }

    public void evaluate()
    {
        this.outputs[0]=this.inputs[0] == this.inputs[1];
        this.outputs[1]=this.inputs[0] && !this.inputs[1];
        this.outputs[2]=!this.inputs[0] && this.inputs[1];
    }
}