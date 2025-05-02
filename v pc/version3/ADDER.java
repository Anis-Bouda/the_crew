import java.util.ArrayList;
import java.util.List;

public class ADDER extends Composant{
    private int n;
    private List<BiteADDER> bitaddGates;
    
    public ADDER(String id,int n, int x, int y)
    {
        super(id,x,y);
        this.n = n;
        this.bitaddGates = new ArrayList<>();
        /*deux entrees de n bits avec une retenue */
        for(int i=0;i<2*this.n+1;i++)
        {
            this.addInput(State.UNKNOWN);
        }
        /* une sortie de n bits de somme avec retenue */
        for (int i = 0; i < n + 1; i++) 
        { 
            this.addOutput(State.UNKNOWN);
        }
        for (int i = 0; i < n; i++) 
        {
            this.bitaddGates.add(new BiteADDER("BiteADDER_ADDER" + i, x, y));
        }
    }

    @Override
    public void evaluate()
    {
        if (this.inputs.size() == 2 * n + 1 && this.outputs.size() == n + 1)
        {
            State retenue = this.inputs.get(2 * n); /*Cin initial*/
            for(int i=0;i<this.n;i++)
            {
                BiteADDER bitAddGate = bitaddGates.get(i);
                /*Définir les entrées du BiteADDER (Ai, Bi, et retenue)*/
                bitAddGate.setInputs(0, this.inputs.get(i));
                bitAddGate.setInputs(1, this.inputs.get(n + i));
                bitAddGate.setInputs(2, retenue);
                bitAddGate.evaluate();
                this.outputs.set(i,bitAddGate.getOutput(0));
                retenue=bitAddGate.getOutput(1);

                /*afficher les étapes pour debogage*/
                System.out.println("Bit " + i + " : A=" + this.inputs.get(i) + 
                ", B=" + this.inputs.get(n + i) + 
                ", Cin=" + this.inputs.get(2 * n) + 
                " -> S=" + bitAddGate.getOutput(0) + 
                ", Cout=" + bitAddGate.getOutput(1));
            }
            this.outputs.set(n, retenue);
        }
        else
        {
            throw new IllegalStateException("ADDER doit avoir 2n+1 entrées et n+1 sorties.");
        }
    }
}