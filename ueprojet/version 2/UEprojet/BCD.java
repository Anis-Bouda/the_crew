public class BCD extends Composant{

    public BCD (String id)
    {
        super(id,4,7);
    }

    public void evaluate()
    {
        boolean A=this.inputs[0];
        boolean B=this.inputs[1];
        boolean C=this.inputs[2];
        boolean D=this.inputs[3];

        this.outputs[0]= D || B || (A && C) ||(!A && !C);
        this.outputs[1]= !C || (!A && !B) || (A && B);
        this.outputs[2]= !B || A || C;
        this.outputs[3]= D || (!A && B) || (B && !C) || (!A && !C) || (A && !B && C);
        this.outputs[4]= (!A && B) || (!A && !C);
        this.outputs[5]= D || (!A && !B) || (!B && C) || (!A && C);
        this.outputs[6]= D || (!A && B) || (B && !C) || (!B && C);
    }
}