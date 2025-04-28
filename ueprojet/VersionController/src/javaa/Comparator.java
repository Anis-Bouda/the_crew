import java.util.ArrayList;
import java.util.List;
public class Comparator extends Composant {
    private int n;
    
    public Comparator(String id, int x, int y) {
        super(id, x, y);
        this.n = n;
        // Initialisation des entrées A[n] et B[n]
        for (int i = 0; i < 4; i++) {
            this.addInput(State.UNKNOWN);  // A et B
        }
        // Initialisation des sorties (A == B, A < B, A > B)
        this.addOutput(State.UNKNOWN);  // A == B
        this.addOutput(State.UNKNOWN);  // A < B
        this.addOutput(State.UNKNOWN);  // A > B
    }
    
    public void setInputState(int index, State state) {
        if (index >= 0 && index < inputs.size()) {
            inputs.set(index, state);
        } else {
            throw new IndexOutOfBoundsException("Index d'entrée invalide : " + index);
        }
    }
    
    public State getOuputState(int index) {
        if (index >= 0 && index < outputs.size()) {
        	return outputs.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index d'entrée invalide : " + index);
        }
    }

    @Override
    public void evaluate() {
        if (this.inputs.size() == 4 && this.outputs.size() == 3) {
            List<State> A = new ArrayList<>(this.inputs.subList(0, 2));
            List<State> B = new ArrayList<>(this.inputs.subList(2, 4));

            // Variables pour les résultats de comparaison
            State equalityState = State.True;
            State lessThanState = State.UNKNOWN;
            State greaterThanState = State.UNKNOWN;

            // Vérification des états inconnus ou erreurs dans les entrées
            for (int i = 0; i < 2; i++) {
                if (A.get(i) == State.ERROR || B.get(i) == State.ERROR) {
                    equalityState = State.ERROR;
                    lessThanState = State.ERROR;
                    greaterThanState = State.ERROR;
                    break;
                }
                else 
                {
                    if (A.get(i) == State.UNKNOWN || B.get(i) == State.UNKNOWN) {
                        equalityState = State.UNKNOWN;
                        lessThanState = State.UNKNOWN;
                        greaterThanState = State.UNKNOWN;
                        break;
                    }
                }
            }

            // Si aucune entrée n'est dans un état UNKNOWN ou ERROR, on procède à la comparaison
            if (equalityState != State.UNKNOWN && equalityState != State.ERROR) {
                boolean equal = true;
                boolean lessThan = false;
                boolean greaterThan = false;

                // Comparaison bit à bit de A et B
                for (int i = 0; i < 2; i++) {
                    if (A.get(i) != B.get(i)) {
                        equal = false;
                        if (A.get(i) == State.True) {
                            greaterThan = true;
                            break;
                        } else {
                            lessThan = true;
                            break;
                        }
                    }
                }

                /*Définir les sorties*/
                if (equal) {
                    equalityState = State.True;  // A == B
                } else {
                    equalityState = State.False; // A != B
                }

                if (lessThan) {
                    lessThanState = State.True;  // A < B
                } else {
                    lessThanState = State.False; // A >= B
                }

                if (greaterThan) {
                    greaterThanState = State.True;  // A > B
                } else {
                    greaterThanState = State.False; // A <= B
                }
            }

            // Définir les résultats des sorties
            this.outputs.set(0, equalityState);  // A == B
            this.outputs.set(1, lessThanState);  // A < B
            this.outputs.set(2, greaterThanState);  // A > B

        } else {
            throw new IllegalStateException("COMPARATOR : doit avoir 4 entrées et 3 sorties.");
        }
    }
}
