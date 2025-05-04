package Logique.plexers ;
import Logique.Principale.*;
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
            
            int cptE = 0;
            for (int j = 0; j < inputs.length; j++) {
                inputs[j] = this.inputs.get(j);
                if (inputs[j] == State.ERROR) {
                    cptE++;
            }}
            // si y a une entree qui est error alors les sorties sont error
            if (cptE > 0) {
                for (int i = 0; i < outputs.length; i++) {
                    outputs[i] = State.ERROR;
            }} 
            else {
                int cptU = 0;
                for (int j = 0; j < inputs.length; j++) {
                    if (inputs[j] == State.ERROR) {
                        cptU++;
                }}
                // Si y a une entree qui est uknown alors les sorties sont tous unknown
                if (cptU > 0) {
                    for (int i = 0; i < outputs.length; i++) {
                        outputs[i] = State.UNKNOWN;
                }} 
                else {
                int cptF = 0;
                    for (int j = 0; j < inputs.length; j++) {
                        if (inputs[j] == State.FALSE) {
                            cptF++;
                    }}
                    // si y a tous les entrees sont FALSE alors les sorties sont tous unknown 
                if (cptF == 4) {
                        for (int i = 0; i < outputs.length; i++) {
                            outputs[i] = State.UNKNOWN;
                }}
                else {
                    int cptT = 0;
                    for (int i = 0; i < inputs.length; i++) {
                         if (inputs[i] == State.TRUE) {
                              cptT++;
                    }}
                    // si ya plus d'un un.TRUE alors c'est error 
                    if (cptT > 1) {
                        for (int i = 0; i < outputs.length; i++) {
                              outputs[i] = State.ERROR;
                    }}
                    else if(cptT == 1) {
                            // si un seul.TRUE alors 
                            if (inputs[0] == State.TRUE) {
                                outputs[0] = State.TRUE;
                                outputs[1] = State.TRUE;
                            }
                            else if (inputs[1] == State.TRUE) {
                                outputs[0] = State.TRUE;
                                outputs[1] = State.FALSE;
                            }
                            else if (inputs[3] == State.TRUE) {
                                outputs[0] = State.FALSE;
                                outputs[1] = State.FALSE;
                            }
                            else if (inputs[2] == State.TRUE) {
                                outputs[0] = State.FALSE;
                                outputs[1] = State.TRUE;
                            }}}}}
            for (int i = 0; i < outputs.length; i++) {
                this.outputs.set(i, outputs[i]);
            }
        } else {
            throw new IllegalStateException("Erreur d'évaluation : L'encodeur doit avoir exactement 4 entrées et 2 sorties.");
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
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
