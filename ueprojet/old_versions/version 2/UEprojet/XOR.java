Class XOR extends Composant{

    public XOR (String id)
    {
        super(id,2,1);
    }

    public void evaluate()
    {
        this.outputs[0]=this.inputs[0] ^ this.inputs[1];
    }
}