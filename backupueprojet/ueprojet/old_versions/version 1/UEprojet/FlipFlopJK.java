/*une version avec deux soties Q ert Qbar*/
class FlipFlopJK extends Composant {
    private boolean state = false; 

    public FlipFlopJK(String id) {
        super(id, 3, 2); 
    }

    public void evaluate() {
        if (inputs.length < 3|| outputs.length < 2) {
            throw new IllegalStateException("FLIPFLOP 'JK': Nombre d'entrées ou sorties incorrect !");
        }
        boolean J=this.inputs[0];
        boolean K= this.inputs[1];
        boolean CLK = this.inputs[2];

        if (CLK== true) {
                if(J==true && K==false) state=true;
                else{
                    if(J==false && K==true) state=false;
                    if(J==true && K==true) state=!state;
                }
        }
        this.outputs[0] = state;
        this.outputs[0] = !state;
    }
}