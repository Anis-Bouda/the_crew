public class MULT4X1 extends Composant{

    public MULT4X1 (String id)
    {
        super(id,6,1);
    }

    public void evaluate()
    {
      this.outputs[0]=(this.inputs[0] && !this.inputs[4] && !this.inputs[5]) || 
      (this.inputs[1] && this.inputs[4] && !this.inputs[5]) ||
      (this.inputs[2] && !this.inputs[4] && this.inputs[5]) ||
      (this.inputs[3] && this.inputs[4] && this.inputs[5]);
    }
}