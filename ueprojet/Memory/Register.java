package Memory ;
import java.util.ArrayList;
import java.util.List;

public class Register extends Composant {
    private int n; /*taille du registre */
    private List<State> Data;
    private State writeEnable; /*Ecriture/Lecture */

    public Register( String id, int n,int x,int y)
    {
        super(id,x,y);
        this.n=n;
        this.Data=new ArrayList<>();
        /*initialisation de la memoire du registre */
        for(int i=0;i<n;i++)
        {
            this.Data.add(State.UNKNOWN);
        }
        this.writeEnable=State.FALSE; /*par defaut , l'écriture est désactivée */

        /*initialisation des entrées
         * data : une entree de n bits
         * un signal d'horloge
         * un signale Write Enable
         */
        for(int i=0;i<n;i++)
        {
            this.addInput(State.UNKNOWN);
        }
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);

        /*initialisation de la sortie
         * data : une sortie de n bits
         */
        for(int i=0;i<n;i++)
        {
            this.addOutput(State.UNKNOWN);
        }
    }

    @Override
    public void evaluate()
    {
        if(this.inputs.size()==n+2 && this.outputs.size()==n)
        {
            State clock=this.inputs.get(n);
            writeEnable=this.inputs.get(n+1);
            List<State> data=this.inputs.subList(0,n);
            /*verification des signal si ils sont a ERROR ou UNKNOWN */
            if (clock == State.ERROR || writeEnable == State.ERROR) {
                throw new IllegalStateException("Register : les signaux ne peuvent être ERROR.");
            }
            if (clock == State.UNKNOWN || writeEnable == State.UNKNOWN) {
                throw new IllegalStateException("Register : les signaux ne peuvent être UNKNOWN.");
            }

            if(writeEnable==State.TRUE)
            {
                if(clock==State.TRUE)
                {
                    for(int i=0;i<n;i++)
                    {
                        this.Data.set(i,data.get(i));
                    }
                }
                
            }
            else
            {
                if(writeEnable==State.FALSE)
                {
                    for(int i=0;i<n;i++)
                    {
                        this.outputs.set(i,Data.get(i));
                    }
                }
            }
                
        }
        else
        {
            throw new IllegalStateException("Register : doit avoir une entrée de n bits<=>(n bits de données, 1 horloge, 1 Write Enable) et  une sortie de n bits<=> n sorties.");
        }
    }
}