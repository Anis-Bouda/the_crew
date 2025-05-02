public class XNOR extends Composant {
    
    private XOR xorGate;  /*La sous-porte XOR du XNOR*/
    private NOT nonGate; /*La sous-porte NON du XNOR*/

    public XNOR(String id, int x,int y)
    {
        super(id,x,y);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
        
        this.xorGate = new XOR("XNOR_XOR", x,y);
        this.nonGate = new NOT("NNOR_NON", x,y);
    }

    @Override
    public void evaluate()
    {
        if (this.inputs.size() == 2 && this.outputs.size()==1)
        {
            State input1=this.inputs.get(0);
            State input2=this.inputs.get(1);
            System.out.println("💡 XNOR " + id + " inputs = " + inputs);
            State output;

            /*evaluation du XOR avec les entrées du XNOR */
            xorGate.setInputs(0,input1);
            xorGate.setInputs(1,input2);
            xorGate.evaluate();
            output=xorGate.getstate();

            /*evaluation du NON avec la sortie du XOR pour avoir la sortie du XNOR */
            nonGate.setInputs(0,output);
            nonGate.evaluate();

            output=nonGate.getstate();

            this.outputs.set(0,output);
            this.state=output;
            System.out.println("💡 XNOR " + id + " outputs = " + outputs);
        }
        else
        {
            throw new IllegalStateException("Erreur d'évaluation : La porte XNOR doit avoir exactement 2 inputs et 1 output.");
        }
    }
}
