package projet;

import java.util.ArrayList;
import java.util.List;

public class Counter extends Composant{
    private int n;
    private List<State> cmp;
    private State up_count;
    private ADDER addGate;
    private SUB subGate;

    public Counter(String id, int n,int x,int y)
    {
        super(id,x,y);
        this.n=n;
        this.cmp=new ArrayList<>();
        this.addGate=new ADDER(this.id+"Counter_ADDER",n,x,y);
        this.subGate= new SUB(this.id+"Counter_SUB",n,x,y);

        for(int i=0;i<n;i++)
        {
            cmp.add(State.UNKNOWN);
        }

        this.up_count=State.True; /*par defaut, compte en incrémentant */
        /*initialisation de la valeur initial du compteur dcp lentree*/
        for(int i=0;i<n;i++)
        {
            this.addInput(State.UNKNOWN);
        }
        /*initialisation du signal d'horloge */
        this.addInput(State.UNKNOWN);
        /*up_count ou down-count */
        this.addInput(State.UNKNOWN);

        /*initialisation de la sortie */
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
            up_count=this.inputs.get(n+1);
            cmp=this.inputs.subList(0,n);

            if(clock==State.ERROR || up_count==State.ERROR)
            {
                throw new IllegalStateException("Counter : Les signaux ne peuvent être ERROR.");
            }
            if(clock==State.UNKNOWN || up_count== State.UNKNOWN)
            {
                throw new IllegalStateException("Counter : Les signaux ne peuvent être UNKOWN.");
            }
            /* horloge activée la valeur du compteur change*/
            if(clock==State.True)
            {
                /*le compteur s'incremente */
                if(up_count==State.True)
                {
                    for(int i=0;i<n;i++)
                    {
                        addGate.setInputs(i,cmp.get(i));
                        addGate.setInputs(n+i,State.False);
                    }
                    //addGate.setInputs(n,State.TRUE);
                    addGate.setInputs(2*n,State.True);
                    addGate.evaluate();
                    for(int i=0;i<n;i++)
                    {
                        this.cmp.set(i,this.addGate.getOutput(i));
                    }
                }
                else
                {
                    /*la compteur d'ecremente */
                    if(up_count==State.False)
                    {
                        for(int i=0;i<n;i++)
                        {
                            subGate.setInputs(i,cmp.get(i));
                            subGate.setInputs(n+i,State.False);
                        }
                        subGate.setInputs(n,State.True);
                        subGate.setInputs(2*n+1,State.False);
                        subGate.evaluate();
                        for(int i=0;i<n;i++)
                        {
                            this.cmp.set(i,this.subGate.getOutput(i));
                        }
                    }
                }

            }
            for(int i=0;i<n;i++)
            {
                this.outputs.set(i,cmp.get(i));
            }
        }
        else
        {
            throw new IllegalStateException("Counter : doit avoir n bits d'entrees la valeur initial du compteur, 1 pour l'horloge, 1 pour la direction, et n sorties.");
        }
    }

    public void reset()
    {
        State clock=this.inputs.get(n);
        State up_count=this.inputs.get(n+1);

        if(clock==State.ERROR || up_count==State.ERROR)
        {
            throw new IllegalStateException("Counter : Les signaux ne peuvent être ERROR.");
        }
        if(clock==State.UNKNOWN || up_count== State.UNKNOWN)
        {
            throw new IllegalStateException("Counter : Les signaux ne peuvent être UNKOWN.");
        }
        if(clock==State.True)
        {
            if(up_count==State.True)
            {
                for(int i=0;i<n;i++)
                {
                    cmp.set(i,State.False);
                }
            }
            else
            {
                if(up_count==State.False)
                {
                    for(int i=0;i<n;i++)
                    {
                        cmp.set(i,State.True);
                    }
                }
            }
        }
    }
}