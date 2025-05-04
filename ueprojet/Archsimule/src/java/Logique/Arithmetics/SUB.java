package Logique.Arithmetics;
import Logique.Principale.*;
import Logique.gates.*;
import java.util.ArrayList;
import java.util.List;

public class SUB extends Composant{
    private int n;
    /*car A - B= A + "complemenet a 2 de B"((non B)+1) */
    private ADDER addGate;
    private NON nonGate;
    private State retenue = State.TRUE;
    public SUB(String id,int n, int x, int y)
    {
        super(id,x,y);
        this.n = n;
        this.addGate= new ADDER("SUB_ADDER",n,x,y);
        this.nonGate=new NON ("SUB_NON",x,y);
        /*initialisation des entrees A[n] et B[n] */
        for(int i=0;i<2*n + 1;i++)
        {
            this.addInput(State.UNKNOWN);
        }
        /*initialisation des sorties S[n] */
        for (int i = 0; i < n + 1; i++) 
        { 
            this.addOutput(State.UNKNOWN);
        }
    }

    @Override
    public void evaluate()
    {
        if (this.inputs.size() == 2 * n && this.outputs.size() == n + 1)
        {
            List<State> BComplement2= new ArrayList<>();
            for(int i=0;i<this.n;i++)
            {
                State bitB=this.inputs.get(n+i);
                nonGate.setInputs(0,bitB);
                nonGate.evaluate();
                BComplement2.add(nonGate.getOutput(0));
            }
            /*ajouter a nonB 1 dans la retenue afin d'obtenir le complement a 2 de B */
            addGate.setInputs(2*n, this.retenue);
            /*calculer A-B grace au ADDER */
            for(int i=0;i<n;i++)
            {
                addGate.setInputs(i,this.inputs.get(i));
                addGate.setInputs(n+i,BComplement2.get(i));
            }
            addGate.evaluate();
            for(int i=0;i<n;i++)
            {
                this.outputs.set(i,addGate.getOutput(i));
            }

        }
        else
        {
            throw new IllegalStateException("SUB : substractor doit avoir 2n entrées et n+1 sorties.");
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        SUB objet = (SUB) obj;
        if (this.n != objet.n) {
            return false;
        }
        return this.addGate.equals(objet.addGate) && this.nonGate.equals(objet.nonGate);
    }

    @Override
    public String toString() {
        return super.toString() + ", n=" + this.n + ", addGate=" + this.addGate + ", nonGate=" + this.nonGate;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 71 * result + this.n;
        result = 71 * result + (this.addGate != null ? this.addGate.hashCode() : 0);
        result = 71 * result + (this.nonGate != null ? this.nonGate.hashCode() : 0);
        return result;
    }
}
