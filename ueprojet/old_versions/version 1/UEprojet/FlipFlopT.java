/*une version avec deux soties Q ert Qbar*/
class FlipFlopT extends Composant {
    private boolean state = false; 

    public FlipFlopT(String id) {
        super(id, 2, 2); 
    }

    public void evaluate() {
        if (inputs.length < 2|| outputs.length < 2) {
            throw new IllegalStateException("FLIPFLOP 'T': Nombre d'entrées ou sorties incorrect !");
        }
        boolean T = this.inputs[0];
        boolean CLK = this.inputs[1];

        if (CLK == true) { 
            if (T == true) {
                state = !state;
            }
        }

        this.outputs[0] = state;
        this.outputs[1] = !state;
    }
}
