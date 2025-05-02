package Logique.wiring;
import Logique.Principale.*;

public class Clock extends Composant {
    private int period;
    private int cmpt;

    public Clock(String id, int x, int y, int period) {
        super(id, x, y);
        this.period = period;
        this.cmpt = 0;
        this.addOutput(State.UNKNOWN);
        this.state = State.UNKNOWN;
    }

    @Override
    public void evaluate() {
        if (this.outputs.size() == 1) {
            cmpt++;

            if (cmpt >= period) {
                State outputpast = this.outputs.get(0);
                State newoutput = (outputpast == State.TRUE) ? State.FALSE : State.TRUE;
                
                this.outputs.set(0, newoutput);
                this.state = newoutput;
                System.out.println("Clock bascule en " + newoutput);

                cmpt = 0;
            }
        } else {
            throw new IllegalStateException("Erreur d'évaluation : Clock doit avoir exactement 1 output.");
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
    Clock objet = (Clock) obj;
    return this.period == objet.period && this.cmpt == objet.cmpt;
}

   @Override
    public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + this.period;
    result = 31 * result + this.cmpt;
    return result;
    }

    @Override
    public String toString() {
    return super.toString() + ", period = " + this.period + ", compteur=" + this.cmpt;
    }

}