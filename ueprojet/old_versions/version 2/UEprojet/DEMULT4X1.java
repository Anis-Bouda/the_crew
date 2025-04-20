public class DEMULT4X1 extends Composant{

    public DEMULT4X1 (String id)
    {
        super(id,3,4);
    }

    public void evaluate()
    {
        this.outputs[0]=!this.inputs[0] && !this.inputs[1] && this.inputs[2];
        this.outputs[1]=!this.inputs[0] && this.inputs[1] && this.inputs[2];
        this.outputs[3]=this.inputs[0] && !this.inputs[1] && this.inputs[2];
        this.outputs[4]=this.inputs[0] && this.inputs[1] && this.inputs[2];
    }
}