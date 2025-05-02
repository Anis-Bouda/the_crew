package Arithmetics ;
import Principale.*;
import java.util.ArrayList;
import java.util.List;
public class COMPARATOR extends Composant {
    private int n;
    
    public COMPARATOR(String id, int n, int x, int y) {
        super(id, x, y);
        this.n = n;
        // Initialisation des entrées A[n] et B[n]
        for (int i = 0; i < 2 * n; i++) {
            this.addInput(State.UNKNOWN);  // A et B
        }
        // Initialisation des sorties (A == B, A < B, A > B)
        this.addOutput(State.UNKNOWN);  // A == B
        this.addOutput(State.UNKNOWN);  // A < B
        this.addOutput(State.UNKNOWN);  // A > B
    }

    @Override
    public void evaluate() {
        if (this.inputs.size() == 2 * n && this.outputs.size() == 3) {
            List<State> A = new ArrayList<>(this.inputs.subList(0, n));
            List<State> B = new ArrayList<>(this.inputs.subList(n, 2 * n));

            // Variables pour les résultats de comparaison
            State equalityState = State.TRUE;
            State lessThanState = State.UNKNOWN;
            State greaterThanState = State.UNKNOWN;

            // Vérification des états inconnus ou erreurs dans les entrées
            for (int i = 0; i < n; i++) {
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
                for (int i = 0; i < n; i++) {
                    if (A.get(i) != B.get(i)) {
                        equal = false;
                        if (A.get(i) == State.TRUE) {
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
                    equalityState = State.TRUE;  // A == B
                } else {
                    equalityState = State.FALSE; // A != B
                }

                if (lessThan) {
                    lessThanState = State.TRUE;  // A < B
                } else {
                    lessThanState = State.FALSE; // A >= B
                }

                if (greaterThan) {
                    greaterThanState = State.TRUE;  // A > B
                } else {
                    greaterThanState = State.FALSE; // A <= B
                }
            }

            // Définir les résultats des sorties
            this.outputs.set(0, equalityState);  // A == B
            this.outputs.set(1, lessThanState);  // A < B
            this.outputs.set(2, greaterThanState);  // A > B

        } else {
            throw new IllegalStateException("COMPARATOR : doit avoir 2n entrées et 3 sorties.");
        }
    }
    
    @Override
	public boolean equals(Object obj)
	{
		if(this==obj)
		{
			return true;
		}
		if (!super.equals(obj))
		 {
			return false;
		 }
		 COMPARATOR objet=(COMPARATOR) obj;
		 return this.n==objet.n;

	}
	@Override
	public String toString()
	{
        return super.toString() + ", n=" + this.n;
	}
	@Override
	public int hashCode()
	{
		int res=super.hashCode();
		res=res*71+this.n;
		return res;
	}
}