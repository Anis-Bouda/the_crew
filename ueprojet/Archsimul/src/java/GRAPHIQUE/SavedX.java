package GRAPHIQUE;

import Logique.Principale.*;

public class SavedX extends Composant {
    private State inputState;  // Déclaration de la variable d'instance pour l'état d'entrée
    private State outputState; // Déclaration de la variable d'instance pour l'état de sortie

    public SavedX(String id, int x, int y, String in, String out) {
        super(id, x, y);  // Appel du constructeur parent
        this.inputState = State.fromString(in);  // Conversion du String en State pour l'entrée
        this.outputState = State.fromString(out); // Conversion du String en State pour la sortie
        this.addInput(inputState);   // Ajout de l'état d'entrée
        this.addOutput(outputState); // Ajout de l'état de sortie
    }

    @Override
    public void evaluate() {
        if (this.inputs.size() == 1 && this.outputs.size() == 1) {
            State input1 = this.inputs.get(0); // Récupération de l'entrée
            State output;

            // Si l'entrée est une erreur ou inconnue, la sortie prend la même valeur
            if (input1 == State.ERROR || input1 == State.UNKNOWN) {
                output = input1;
            } else {
                // Sinon, on compare l'état d'entrée à l'état prévu
                if (input1 == inputState) {
                    output = outputState; // Si l'entrée correspond à l'état d'entrée, on met la sortie à l'état de sortie
                } else {
                    output = State.FALSE; // Sinon, la sortie est fausse
                }
            }

            // Mise à jour de l'état de sortie
            this.outputs.set(0, output);
            this.state = output; // Mise à jour de l'état du composant
        } else {
            // Exception si le composant n'a pas exactement 1 entrée et 1 sortie
            throw new IllegalStateException("Erreur d'évaluation : Le composant doit avoir exactement 1 input et 1 output.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || super.equals(obj);
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
