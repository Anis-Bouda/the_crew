public class ENCODEUR extends Composant{

    public ENCODEUR (String id)
    {
        super(id,4,2);
    }

    public void evaluate()
    {
        this.outputs[0]=this.inputs[1] || this.inputs[3];
        this.outputs[1]=this.inputs[2] || this.inputs[3];
    }
}