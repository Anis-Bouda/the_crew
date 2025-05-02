public class XNOR extends Composant {
     
    private XOR xorGate;  /*La sous-porte XOR du XNOR*/
    private NON nonGate; /*La sous-porte NON du XNOR*/

    public XNOR(String id, int x,int y)
    {
        super(id,x,y);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
        
        this.xorGate = new XOR("XNOR_XOR", x,y);
        this.nonGate = new NON("NNOR_NON", x,y);
    }

    @Override
    public void evaluate()
    {
        if (this.inputs.size() == 2 && this.outputs.size()==1)
        {
            State input1=this.inputs.get(0);
            State input2=this.inputs.get(1);
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
        }
        else
        {
            throw new IllegalStateException("Erreur d'évaluation : La porte XNOR doit avoir exactement 2 inputs et 1 output.");
        }
    }
    @Override
    public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (!super.equals(obj)) {
        return false;
    }
    XNOR objet = (XNOR) obj;
    return this.xorGate.equals(objet.xorGate) && this.nonGate.equals(objet.nonGate);
    }

   @Override
    public String toString() {
    return super.toString() + ", xorGate=" + this.xorGate + ", nonGate=" + this.nonGate;
    }

   @Override
    public int hashCode() {
    int res = super.hashCode();
    res = 31 * res + (this.xorGate != null ? this.xorGate.hashCode() : 0);
    res = 31 * res + (this.nonGate != null ? this.nonGate.hashCode() : 0);
    return res;
  }
}