package Logique.Input_output ;
import Logique.Principale.*;

public class determinateGenerator extends Composant {


    public determinateGenerator( String id,int x,int y, State s)

    {
        super(id,x,y);
        this.addInput(s);
        this.addOutput(s);
    }

    @Override

    public void evaluate()

    {

        if(this.inputs.size()==1 && this.outputs.size()==1)

        {
             State s=this.inputs.get(0);
             this.outputs.set(0,s);

        }
        else

        {

            throw new IllegalStateException("RandomGenerator : doit avoir une seule entree qui l'active, et une sortie de n bits<=> n sorties.");

        }

    }

}
