package projet;

public class NOR extends Composant {
     
    private OU ouGate;  /*La sous-porte OU du NOR*/
    private NON nonGate; /*La sous-porte NON du NOR*/

    public NOR(String id, int x,int y)
    {
        super(id,x,y);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
        this.ouGate = new OU("NOR_OU", x,y);
        this.nonGate = new NON("NOR_NON", x,y);
    }

    @Override
    public void evaluate()
    {
        if (this.inputs.size() == 2 && this.outputs.size()==1)
        {
            State input1=this.inputs.get(0);
            State input2=this.inputs.get(1);
            State output;

            /*evaluation du OU avec les entrées du NOR */
            ouGate.setInputs(0,input1);
            ouGate.setInputs(1,input2);
            ouGate.evaluate();
            output=ouGate.getstate();

            /*evaluation du NON avec la sortie du OU pour avoir la sortie du NOR */
            nonGate.setInputs(0,output);
            nonGate.evaluate();

            output=nonGate.getstate();

            this.outputs.set(0,output);
            this.state=output;
        }
        else
        {
            throw new IllegalStateException("Erreur d'évaluation : La porte NAND doit avoir exactement 2 inputs et 1 output.");
        }
    }
}