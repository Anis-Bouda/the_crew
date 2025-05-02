import java.util.ArrayList;
import java.util.List;

public class Substractor extends Composant{
    /*car A - B= A + "complemenet a 2 de B"((non B)+1) */
    private Adder addGate;
    private NOT nonGate;
    
    public Substractor(String id, int x, int y)
    {
        super(id,x,y);
        this.addGate= new Adder("SUB_ADDER",x,y);
        this.nonGate=new NOT("SUB_NON",x,y);
        /*initialisation des entrees A[n] et B[n] */
        for(int i=0;i<5;i++)
        {
            this.addInput(State.UNKNOWN);
        }
        /*initialisation des sorties S[n] */
        for (int i = 0; i < 2; i++) 
        { 
            this.addOutput(State.UNKNOWN);
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
        if (this.inputs.size() == 5 && this.outputs.size() == 2)
        {
            List<State> BComplement2= new ArrayList<>();
            for(int i=0;i<2;i++)
            {
                if(this.inputs.get(i)!=State.UNKNOWN && this.inputs.get(i)!=State.ERROR && this.inputs.get(i+2)!=State.UNKNOWN && this.inputs.get(2+i)!=State.ERROR)
                {
                    State bitB=this.inputs.get(2+i);
                    nonGate.setInputs(0,bitB);
                    nonGate.evaluate();
                    BComplement2.add(nonGate.getOutput(0));
                }
                else
                {
                    throw new IllegalStateException("SUB : ne doi pas avoir d'entree UNKNOWN ou ERROR");
                }
            }
            /*ajouter a nonB 1 dans la retenue afin d'obtenir le complement a 2 de B */
            addGate.setInputs(4, State.True);
            /*calculer A-B grace au ADDER */
            for(int i=0;i<2;i++)
            {
                addGate.setInputs(i,this.inputs.get(i));
                addGate.setInputs(2+i,BComplement2.get(i));
            }
            addGate.evaluate();
            for(int i=0;i<2;i++)
            {
                this.outputs.set(i,addGate.getOutput(i));
            }

        }
        else
        {
            throw new IllegalStateException("SUB : substractor doit avoir 2n+1 entrées et n+1 sorties.");
        }
    }
}
