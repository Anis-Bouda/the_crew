package projet;

import java.util.ArrayList;
import java.util.List;

public class MUX extends Composant {

    private int n;

    public MUX(String id, int n, int x, int y) {
        super(id, x, y);
        this.n = n;
        /*initialisation des entrées A[n]*/
        for (int i = 0; i < (1<<n); i++) {
            this.addInput(State.UNKNOWN);  // A et B
        }
        /*initialisation des bits de séléction */
        for (int i = 0; i < n; i++) {
            this.addInput(State.UNKNOWN);  // A et B
        }

        /*initialisation de la sortie */
        this.addOutput(State.UNKNOWN);
    }

    @Override
    public void evaluate()
    {
        if(this.inputs.size() == (1<<n)+this.n && this.outputs.size()==1)
        {
            List<State> A = new ArrayList<>(this.inputs.subList(0,(1<<n)));
            List<State> BitsSelection = new ArrayList<>(this.inputs.subList((1<<n), (1<<n)+n));
            State res=State.True;
            for(int i=0; i<this.n;i++)
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
                for (int i=0;i< n;i++)
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