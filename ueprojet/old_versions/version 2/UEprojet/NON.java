Class NON extends Composant{

    public NON (String id)
    {
        super(id,1,1);
    }

    public void evaluate()
    {
        this.outputs[0]=!this.inputs[0];
    }
}