package projet;

public class BiteADDER extends Composant {
    
    /* car BiteADDER(A,B,Cin)={S= (A XOR B XOR CIN), Cout= AETB OU AETCin ou BETCin} */
    private XOR xorGate; /*La sous-porte XOR du BiteADDER*/
    private OU ouGate; /*La sous-porte ou du BiteADDER*/
    private ET etGate; /*La sous-porte ET du BiteADDER*/
    public BiteADDER(String id, int x, int y)
    {
        super(id,x,y);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);

        this.addOutput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
    
        this.xorGate = new XOR("BiteADDER_XOR", x,y);
        this.ouGate = new OU("BiteADDER_OU", x,y);
        this.etGate = new ET("BiteADDER_ET", x,y);
    }

    @Override
    public void evaluate()
    {
        if (this.inputs.size() == 3 && this.outputs.size()== 2)
        {
            State A=this.inputs.get(0);
            State B=this.inputs.get(1);
            State Cin=this.inputs.get(2);
            State AxorB;
            State AetB;
            State CinetAxorB;
            State S;
            State Cout;

            /*evaluation du XOR avec les entrées du BiteADDER pour la somme*/
            /* A XOR B */
            xorGate.setInputs(0,A);
            xorGate.setInputs(1,B);
            xorGate.evaluate();
            AxorB=xorGate.getstate();

            /*A XOR B XOR CIN */
            xorGate.setInputs(0,AxorB);
            xorGate.setInputs(1,Cin);
            xorGate.evaluate();
            S=xorGate.getstate();

            /*evaluation du et et ou pour la retenue */
            /*A ET B */
            etGate.setInputs(0,A);
            etGate.setInputs(1,B);
            etGate.evaluate();
            AetB=etGate.getstate();

            /*Cin ET (A XOR B) */
            etGate.setInputs(0,Cin);
            etGate.setInputs(1,AxorB);
            etGate.evaluate();
            CinetAxorB=etGate.getstate();

            /*(A ET B) || (Cin ET (A XOR B) */
            ouGate.setInputs(0,AetB);
            ouGate.setInputs(1,CinetAxorB);
            ouGate.evaluate();
            Cout=ouGate.getstate();


            this.outputs.set(0,S);
            this.outputs.set(1,Cout);
            this.state=S;
        }
        else
        {
            throw new IllegalStateException("Erreur d'évaluation : La porte BiteADDER doit avoir exactement 3 inputs : bit1, bit2 et Cin et 2 output : s et Cout.");
        }
    }
}