
public class GENERATOR extends Composant {   
    private State activée;

    public GENERATOR(String id, int x, int y, State actv) {
        super(id, x, y);
        this.addInput(actv);  /*L'entrée est Stocke l'état initial*/
        this.addOutput(State.UNKNOWN); /*Sortie initiale inconnue*/    
        }

    @Override
    public void evaluate() {
        if (this.inputs.size() != 1 || this.outputs.size() != 1) {
            throw new IllegalStateException("GENERATOR : doit avoir exactement une entrée et une sortie.");
        }

        activée = this.inputs.get(0);

        if (activée == State.ERROR || activée == State.UNKNOWN) {
            throw new IllegalStateException("GENERATOR : l'entrée ne peut pas être ERROR ou UNKNOWN.");
        }

        this.outputs.set(0, activée); /* La sortie suit l'état d'activation */
        this.state=this.outputs.get(0);
    }
}

