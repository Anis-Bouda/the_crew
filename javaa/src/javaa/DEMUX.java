import java.util.ArrayList;
import java.util.List;
public class DEMUX extends Composant {

    public DEMUX(String id, int x, int y) {
        super(id, x, y);

        /*initialisation de l'entrée */
        this.addInput(State.UNKNOWN);
        /*initialisation des bits de séléction */
        for (int i = 0; i < 2; i++) {
            this.addInput(State.UNKNOWN);  // A et B
        }
        /*initialisation des soties */
        for (int i = 0; i < 4; i++) {
            this.addOutput(State.UNKNOWN);  // A et B
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
        if(this.inputs.size() == 3 && this.outputs.size()==4)
        {
            State entree=this.inputs.get(0);
            List<State> BitsSelection = new ArrayList<>(this.inputs.subList(1,3));
            State res=State.True;
            for(int i=0; i<2;i++)
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
                res=entree;
                //res=this.inputs.get(0);
            }
            /*on désactive toutes les sorties */
            for(int i=0;i<4;i++)
            {
                this.outputs.set(i,State.False);
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