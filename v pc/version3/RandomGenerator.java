import java.util.Random;

public class RandomGenerator extends Composant {
    private int n; /*taille du registre */
    private Random random= new Random();

    public RandomGenerator( String id, int n,int x,int y)
    {
        super(id,x,y);
        this.n=n;
        this.addInput(State.UNKNOWN);/*active ou desactivé */
        for(int i=0;i<n;i++)
        {
            this.addOutput(State.UNKNOWN);
        }
    }

    @Override
    public void evaluate()
    {
        if(this.inputs.size()==1 && this.outputs.size()==n)
        {
            State activated=this.inputs.get(0);
            if(activated==State.TRUE)
            {
                for(int i=0;i<this.n;i++)
                {
                    /*length-2 : genere que des TRUE et FALSE si on veut UNKNOWN ET ERROR on enleve le mois deux! */
                    int index=random.nextInt(State.values().length-2);
                    this.outputs.set(i,State.values()[index]);
                }
            }
            else
            {
                if(activated==State.FALSE)
                {
                    throw new IllegalStateException("RandomGenerator : est désactivé");
                }
                else
                {
                    if(activated==State.ERROR)
                    {
                        throw new IllegalStateException("RandomGenerator : ne prend pas d'entree ERROR");
                    }
                    else
                    {
                        throw new IllegalStateException("RandomGenerator : ne prend pas d'entree UNKNOWN");
                    }
                }
            }
                
        }
        else
        {
            throw new IllegalStateException("RandomGenerator : doit avoir une seule entree qui l'active, et une sortie de n bits<=> n sorties.");
        }
    }
}
