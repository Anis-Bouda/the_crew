import java.util.ArrayList;
import java.util.List;

public class Adder extends Composant{
    private List<BitAdder> bitaddGates;
    
    public Adder(String id, int x, int y)
    {
        super(id,x,y);
        this.bitaddGates = new ArrayList<>();
        // 5 entrees
        for(int i=0;i<5;i++)
        {
            this.addInput(State.UNKNOWN);
        }
        /* une sortie de n bits de somme avec retenue */
        for (int i = 0; i < 3; i++) 
        { 
            this.addOutput(State.UNKNOWN);
        }
        for (int i = 0; i < 2; i++) 
        {
            this.bitaddGates.add(new BitAdder("BiteADDER_ADDER" + i, x, y));
        }
    }
    
    public void setInputState(int index, State state) {
        if (index >= 0 && index < inputs.size()) {
            inputs.set(index, state);
        } else {
            throw new IndexOutOfBoundsException("Index d'entrée invalide : " + index);
        }
    }
    
    public State getOuputState(int index) {
        if (index >= 0 && index < outputs.size()) {
        	return outputs.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index d'entrée invalide : " + index);
        }
    }

    @Override
    public void evaluate()
    {
        if (this.inputs.size() == 5 && this.outputs.size() == 3)
        {
            State retenue = this.inputs.get(4); /*Cin initial*/
            for(int i=0;i<2;i++)
            {
            	State Ai = this.inputs.get(i);
                State Bi = this.inputs.get(2 + i); // B0 à l'index 2, B1 à 3

                if (Ai != State.UNKNOWN && Ai != State.ERROR && Bi != State.UNKNOWN && Bi != State.ERROR) {
                    BitAdder bitAddGate = bitaddGates.get(i);
                    bitAddGate.setInputs(0, Ai);
                    bitAddGate.setInputs(1, Bi);
                    bitAddGate.setInputs(2, retenue);
                    bitAddGate.evaluate();

                    this.outputs.set(i, bitAddGate.getOutput(0)); 
                    retenue = bitAddGate.getOutput(1);
                }
                else
                {
                    throw new IllegalStateException("ADDER : ne doit pas avoir une entree UNKNOWN ou ERROR");
                }
            }
            this.outputs.set(2, retenue);
        }
        else
        {
            throw new IllegalStateException("ADDER : doit avoir 2n+1 entrées et n+1 sorties.");
        }
    }
}
