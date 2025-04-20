/*une version avec deux soties Q ert Qbar*/
class FlipFlopD extends Composant {
    private boolean state = false; 

    public FlipFlopD(String id) {
        super(id, 2, 2); 
    }

    public void evaluate() {
        if (inputs.length < 2|| outputs.length < 2) {
            throw new IllegalStateException("FLIPFLOP 'D': Nombre d'entrées ou sorties incorrect !");
        }
        boolean D = this.inputs[0];
        boolean CLK = this.inputs[1];

        if (CLK == true) { 
            state=D;
        }

        this.outputs[0] = state;
        this.outputs[1] = !state;
    }
}