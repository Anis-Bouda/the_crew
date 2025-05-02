public class MULT8X1 extends Composant{

    public MULT8X1 (String id)
    {
        super(id,11,1);
    }

    public void evaluate()
    {
        boolean A=this.inputs[8];
        boolean B=this.inputs[9];
        boolean C=this.inputs[10];

      this.outputs[0]=(this.inputs[0] && !A && !B && !C) || 
      (this.inputs[1] && !A && !B && C) ||
      (this.inputs[2] && !A && B && !C) ||
      (this.inputs[3] && !A && B && C) ||
      (this.inputs[4] && A && !B && !C) ||
      (this.inputs[5] && A && !B && C) ||
      (this.inputs[6] && A && B && !C) ||
      (this.inputs[7] && A && B && C);
    }
}