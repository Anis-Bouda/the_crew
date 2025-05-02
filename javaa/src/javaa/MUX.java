import java.util.ArrayList;
import java.util.List;

public class MUX extends Composant {
    public MUX(String id, int x, int y) {
        super(id, x, y);
        /*initialisation des entrées A[n]*/
        for (int i = 0; i < 4; i++) {
            this.addInput(State.UNKNOWN);  // A et B
        }
        /*initialisation des bits de séléction */
        for (int i = 0; i < 2; i++) {
            this.addInput(State.UNKNOWN);  // A et B
        }

        /*initialisation de la sortie */
        this.addOutput(State.UNKNOWN);
    }
    
    public void setInputState(int index, State state) {
        if (index >= 0 && index < inputs.size()) {
            inputs.set(index, state);
        } else {
            throw new IndexOutOfBoundsException("Index d'entrée invalide : " + index);
        }
    }

    public State getOutputState() {
        return outputs.get(0);
    }
    

    @Override
    public void evaluate()
    {
        if(this.inputs.size() == 6 && this.outputs.size()==1)
        {
            List<State> A = new ArrayList<>(this.inputs.subList(0,4));
            List<State> BitsSelection = new ArrayList<>(this.inputs.subList(4, 6));
            State res=State.True;
            for(int i=0; i<2;i++)
            {
                /*on verifie que les bits de selection pour erreur et unknown */
                State bitselecstate=BitsSelection.get(i);
                //State bitentreestate=A.get(i);
                if(bitselecstate==State.ERROR )//|| bitentreestate==State.ERROR)
                {
                    res=State.ERROR;
                    break;
                }
                else
                {
                    if(bitselecstate==State.UNKNOWN )//|| bitentreestate==State.UNKNOWN)
                    {
                        res=State.UNKNOWN;
                    }
                }
            }
            if(res!=State.UNKNOWN && res!= State.ERROR)
            {
                int k=0;
                for (int i=0;i< 2;i++)
                {
                    int b=0;
                    State bitstate=BitsSelection.get(i);
                    if(bitstate==State.True)
                    {
                        b=1;
                    }
                    k=k+b*(1<<i);
                }
                res=A.get(k);
            }
            this.outputs.set(0,res);
        }
        else
        {
            throw new IllegalStateException("MUX : doit avoir 2^n+n entrées et une seule sortie.");
        }
    }
}