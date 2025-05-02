package projet;

public class Encodeur extends Composant {
    public Encodeur(String id, int x, int y) {
        super(id, x, y);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);
        this.addInput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
        this.addOutput(State.UNKNOWN);
    }

    public void evaluate() {
        if (this.inputs.size() == 4 && this.outputs.size() == 2) {
            // Un tableau des inputs 
            State[] inputs = new State[4];
            
            // Un tableau des outputs 
            State[] outputs = new State[2];

            // par defaut 
            for (int i = 0; i < outputs.length; i++) {
                outputs[i] = State.UNKNOWN;
            }
            
            int cptU = 0;
            for (int j = 0; j < inputs.length; j++) {
                inputs[j] = this.inputs.get(j);
                if (inputs[j] == State.UNKNOWN) {
                    cptU++;
            }}
            // si y a une entree qui est unknown alors les sorties sont unknown 
            if (cptU > 0) {
                for (int i = 0; i < outputs.length; i++) {
                    outputs[i] = State.UNKNOWN;
            }} 
            else {
                int cptE = 0;
                for (int j = 0; j < inputs.length; j++) {
                    if (inputs[j] == State.ERROR) {
                        cptE++;
                }}
                // Si y a une entree qui est error alors les sorties sont tous errors 
                if (cptE > 0) {
                    for (int i = 0; i < outputs.length; i++) {
                        outputs[i] = State.ERROR;
                }} 
                else {
                int cptF = 0;
                    for (int j = 0; j < inputs.length; j++) {
                        if (inputs[j] == State.False) {
                            cptF++;
                    }}
                    // si y a tous les entrees sont false alors les sorties sont tous unknown 
                if (cptF == 4) {
                        for (int i = 0; i < outputs.length; i++) {
                            outputs[i] = State.UNKNOWN;
                }}
                else {
                    int cptT = 0;
                    for (int i = 0; i < inputs.length; i++) {
                         if (inputs[i] == State.True) {
                              cptT++;
                    }}
                    // si ya plus d'un un true alors c'est error 
                    if (cptT > 1) {
                        for (int i = 0; i < outputs.length; i++) {
                              outputs[i] = State.ERROR;
                    }}
                    else if(cptT == 1) {
                            // si un seul true alors 
                            if (inputs[0] == State.True) {
                                outputs[0] = State.True;
                                outputs[1] = State.True;
                            }
                            else if (inputs[1] == State.True) {
                                outputs[0] = State.True;
                                outputs[1] = State.False;
                            }
                            else if (inputs[3] == State.True) {
                                outputs[0] = State.False;
                                outputs[1] = State.False;
                            }
                            else if (inputs[2] == State.True) {
                                outputs[0] = State.False;
                                outputs[1] = State.True;
                            }}}}}
            for (int i = 0; i < outputs.length; i++) {
                this.outputs.set(i, outputs[i]);
            }
        } else {
            throw new IllegalStateException("Erreur d'évaluation : L'encodeur doit avoir exactement 4 entrées et 2 sorties.");
        }
    }
}
