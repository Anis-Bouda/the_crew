/*une version avec deux soties Q ert Qbar*/
class FlipFlopRS extends Composant {
    private boolean state = false; 

    public FlipFlopRS(String id) {
        super(id, 3, 2); 
    }

    public void evaluate() {
        if (inputs.length < 3|| outputs.length < 2) {
            throw new IllegalStateException("FLIPFLOP 'RS': Nombre d'entrées ou sorties incorrect !");
        }
        boolean R=this.inputs[0];
        boolean S = this.inputs[1];
        boolean CLK = this.inputs[2];

        if (CLK== true) {
            if (R == true && S == true) {
                throw new IllegalArgumentException("État interdit !");
            }
            if (R == true) state = false;
            if (S == true) state = true;
        }
        this.outputs[0] = state;
        this.outputs[1]= !state;
    }
}