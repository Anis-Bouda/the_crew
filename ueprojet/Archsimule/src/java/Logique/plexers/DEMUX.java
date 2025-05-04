package Logique.plexers;
import Logique.Principale.*;
import java.util.ArrayList;
import java.util.List;

public class DEMUX extends Composant {

    private int n;

    public DEMUX(String id, int n, int x, int y) {
        super(id, x, y);
        this.n = n;

        // Initialisation de l'entrée
        this.addInput(State.UNKNOWN);
        // Initialisation des bits de sélection
        for (int i = 0; i < n; i++) {
            this.addInput(State.UNKNOWN);
        }
        // Initialisation des sorties
        for (int i = 0; i < (1 << n); i++) {
            this.addOutput(State.UNKNOWN);
        }
    }

    @Override
    public void evaluate() {
        if (this.inputs.size() != n + 1 || this.outputs.size() != (1 << n)) {
            throw new IllegalStateException("DEMUX : doit avoir n+1 entrées (1 entrée + n bits de sélection) et 2^n sorties.");
        }

        State entree = this.inputs.get(0);
        List<State> BitsSelection = new ArrayList<>(this.inputs.subList(1, n + 1));

        // Vérification des états des bits de sélection
        boolean hasError = false;
        boolean hasUnknown = false;
        int k = 0;

        for (int i = 0; i < n; i++) {
            State bit = BitsSelection.get(i);
            if (bit == State.ERROR) {
                hasError = true;
                break;
            } else if (bit == State.UNKNOWN) {
                hasUnknown = true;
            } else if (bit == State.TRUE) {
                k += (1 << i);
            }
        }

        
        if (hasError) {
            // si le bit de selection est ERROR alors tous les sorties sont ERRORS
            for (int i = 0; i < (1 << n); i++) {
                this.outputs.set(i, State.ERROR);
            }
        } else if (hasUnknown) {
             // si le bit de selection est UNKNOWN alors tous les sorties sont UNKNOWN
            for (int i = 0; i < (1 << n); i++) {
                this.outputs.set(i, State.UNKNOWN);
            }
        } else {
            // une seule sortie reçoit l'entrée, le reste à FALSE
            for (int i = 0; i < (1 << n); i++) {
                this.outputs.set(i, State.FALSE);
            }
            this.outputs.set(k, entree);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (!(obj instanceof DEMUX)) return false;
        DEMUX other = (DEMUX) obj;
        return this.n == other.n;
    }

    @Override
    public String toString() {
        return super.toString() + ", n: " + this.n;
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 71 + this.n;
    }
}

