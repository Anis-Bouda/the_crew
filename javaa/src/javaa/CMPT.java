import java.util.ArrayList;
import java.util.List;

public class CMPT extends Composant{
    private List<State> cmp;
    private State up_count;
    private Adder addGate;
    private Substractor subGate;

    public CMPT(String id,int x,int y)
    {
        super(id,x,y);
        this.cmp=new ArrayList<>();
        this.addGate=new Adder(this.id+"Counter_ADDER",x,y);
        this.subGate= new Substractor(this.id+"Counter_SUB",x,y);

        for(int i=0;i<2;i++)
        {
            cmp.add(State.UNKNOWN);
        }

        this.up_count=State.True; /*par defaut, compte en incrémentant */
        /*initialisation de la valeur initial du compteur dcp lentree*/
        for(int i=0;i<2;i++)
        {
            this.addInput(State.UNKNOWN);
        }
        /*initialisation du signal d'horloge */
        this.addInput(State.UNKNOWN);
        /*up_count ou down-count */
        this.addInput(State.UNKNOWN);

        /*initialisation de la sortie */
        for(int i=0;i<2;i++)
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
        if(this.inputs.size()==4 && this.outputs.size()==2)
        {
            State clock=this.inputs.get(2);
            up_count=this.inputs.get(3);
            cmp=this.inputs.subList(0,2);

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
                    for(int i=0;i<2;i++)
                    {
                        addGate.setInputs(i,cmp.get(i));
                        addGate.setInputs(2+i,State.False);
                    }
                    //addGate.setInputs(n,State.TRUE);
                    addGate.setInputs(4,State.True);
                    addGate.evaluate();
                    for(int i=0;i<2;i++)
                    {
                        this.cmp.set(i,this.addGate.getOutput(i));
                    }
                }
                else
                {
                    /*la compteur d'ecremente */
                    if(up_count==State.False)
                    {
                        for(int i=0;i<2;i++)
                        {
                            subGate.setInputs(i,cmp.get(i));
                            subGate.setInputs(2+i,State.False);
                        }
                        subGate.setInputs(2,State.True);
                        subGate.setInputs(5,State.False);
                        subGate.evaluate();
                        for(int i=0;i<2;i++)
                        {
                            this.cmp.set(i,this.subGate.getOutput(i));
                        }
                    }
                }

            }
            for(int i=0;i<2;i++)
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
        State clock=this.inputs.get(2);
        State up_count=this.inputs.get(3);

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
                for(int i=0;i<2;i++)
                {
                    cmp.set(i,State.False);
                }
            }
            else
            {
                if(up_count==State.False)
                {
                    for(int i=0;i<2;i++)
                    {
                        cmp.set(i,State.True);
                    }
                }
            }
        }
    }
}