package projet;

public class NAND extends Composant {
    
    private ET etGate;  /*La sous-porte ET du NAND*/
    private NON nonGate; /*La sous-porte NON du NAND*/

    public NAND(String id, int x,int y)
    {
        super(id,x,y);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
        this.etGate = new ET("NAND_ET", x,y);
        this.nonGate = new NON("NAND_NON", x,y);
    }

    @Override
    public void evaluate()
    {
        if (this.inputs.size() == 2 && this.outputs.size()==1)
        {
            State input1=this.inputs.get(0);
            State input2=this.inputs.get(1);
            State output;

            /*evaluation du ET avec les entrées du NAND */
            etGate.setInputs(0,input1);
            etGate.setInputs(1,input2);
            etGate.evaluate();
            output=etGate.getstate();

            /*evaluation du NON avec la sortie du ET pour avoir la sortie du NAND */
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
