public class DECODEUR extends Composant{

    public DECODEUR (String id)
    {
        super(id,2,4);
    }

    public void evaluate()
    {
        this.outputs[0]=!this.inputs[0] && !this.inputs[1];
        this.outputs[1]=this.inputs[0] && !this.inputs[1];
        this.outputs[2]=!this.inputs[0] && this.inputs[1];
        this.outputs[3]=this.inputs[0] && this.inputs[1];
    }
}