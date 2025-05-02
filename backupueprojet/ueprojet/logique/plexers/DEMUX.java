package plexers ;
import Principale.*;
import java.util.ArrayList;
import java.util.List;
public class DEMUX extends Composant {

    private int n;

    public DEMUX(String id, int n, int x, int y) {
        super(id, x, y);
        this.n = n;

        /*initialisation de l'entrée */
        this.addInput(State.UNKNOWN);
        /*initialisation des bits de séléction */
        for (int i = 0; i < n; i++) {
            this.addInput(State.UNKNOWN);  // A et B
        }
        /*initialisation des soties */
        for (int i = 0; i < (1<<n); i++) {
            this.addOutput(State.UNKNOWN);  // A et B
        }
    }

    @Override
    public void evaluate()
    {
        if(this.inputs.size() == n+1 && this.outputs.size()==(1<<n))
        {
            State entree=this.inputs.get(0);
            List<State> BitsSelection = new ArrayList<>(this.inputs.subList(1,n+1));
            State res=State.TRUE;
            for(int i=0; i<this.n;i++)
            {
                /*on verifie que les bits de selection pour erreur et unknown */
                State bitselecstate=BitsSelection.get(i);
                if(bitselecstate==State.ERROR )
                {
                    res=State.ERROR;
                    break;
                }
                else
                {
                    if(bitselecstate==State.UNKNOWN )
                    {
                        res=State.UNKNOWN;
                    }
                }
            }
            int k=0;
            if(res!=State.UNKNOWN && res!= State.ERROR)
            {
                for (int i=0;i< n;i++)
                {
                    int b=0;
                    State bitstate=BitsSelection.get(i);
                    if(bitstate==State.TRUE)
                    {
                        b=1;
                    }
                    k=k+b*(1<<i);
                }
                res=entree;
                //res=this.inputs.get(0);
            }
            /*on désactive toutes les sorties */
            for(int i=0;i<(1<<n);i++)
            {
                this.outputs.set(i,State.FALSE);
            }
            /*on derije l'entree vers la sortie selection par les bits de sélection */
            this.outputs.set(k,res);
        }
        else
        {
            throw new IllegalStateException("DEMUX : doit avoir n+1 entrées : une entree et n bits de selection et 2^n sorties, tel qu'on lie une des sorties a l'entree donc on lui affecte la valeur de l'entrée et toutes les autres sorties sont désactivées donc mise a false.");
        }
    }
}