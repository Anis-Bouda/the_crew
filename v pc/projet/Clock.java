package projet;

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
                State newoutput = (outputpast == State.True) ? State.False : State.True;
                
                this.outputs.set(0, newoutput);
                this.state = newoutput;
                System.out.println("Clock bascule en " + newoutput);

                cmpt = 0;
            }
        } else {
            throw new IllegalStateException("Erreur d'évaluation : Clock doit avoir exactement 1 output.");
        }
    }
}
