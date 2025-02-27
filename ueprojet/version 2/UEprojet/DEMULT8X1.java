public class DEMULT8X1 extends Composant{

    public DEMULT8X1 (String id)
    {
        super(id,4,8);
    }
    /*not sure of this one : i need to recheck it  */
    public void evaluate()
    {

            this.outputs[0] = this.inputs[0] && !this.inputs[3] && !this.inputs[2] && !this.inputs[1]; 
            this.outputs[1] = this.inputs[0] && !this.inputs[3] && !this.inputs[2] && this.inputs[1];  
            this.outputs[2] = this.inputs[0] && !this.inputs[3] && this.inputs[2] && !this.inputs[1];  
            this.outputs[3] = this.inputs[0] && !this.inputs[3] && this.inputs[2] && this.inputs[1];  
            this.outputs[4] = this.inputs[0] && this.inputs[3] && !this.inputs[2] && !this.inputs[1];  
            this.outputs[5] = this.inputs[0] && this.inputs[3] && !this.inputs[2] && this.inputs[1];  
            this.outputs[6] = this.inputs[0] && this.inputs[3] && this.inputs[2] && !this.inputs[1]; 
            this.outputs[7] = this.inputs[0] && this.inputs[3] && this.inputs[2] && this.inputs[1]; 
    }
}
