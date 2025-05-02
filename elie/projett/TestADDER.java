import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestADDER {
    public static void main(String[] args) {
        // Créez un ADDER de 2 bits
        ADDER adder = new ADDER("ADDER_2bits", 2, 0, 0);

        // Liste des états possibles
        State[] states = {State.TRUE, State.FALSE, State.UNKNOWN, State.ERROR};

        // Créez un writer pour écrire dans un fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test_results_2bits.txt"))) {

            // Teste toutes les combinaisons possibles pour deux nombres de 2 bits et un report d'entrée
            for (int i = 0; i < 16; i++) { // Première entrée de 2 bits (de 0 à 15)
                for (int j = 0; j < 16; j++) { // Deuxième entrée de 2 bits (de 0 à 15)
                    for (State cin : states) { // Report d'entrée (Cin peut être TRUE, FALSE, UNKNOWN ou ERROR)
                        
                        // Définir les entrées pour l'additionneur A et B (2 bits chacun)
                        for (int bit = 0; bit < 2; bit++) {
                            adder.setInputs(bit, getStateFromIndex((i >> bit) & 1, states)); // A
                            adder.setInputs(bit + 2, getStateFromIndex((j >> bit) & 1, states)); // B
                        }
                        adder.setInputs(4, cin); // Cin
                        
                        // Évaluation de l'additionneur
                        adder.evaluate();
                        
                        // Récupérer les résultats (somme et report)
                        int sum = 0;
                        for (int bit = 0; bit < 2; bit++) {
                            if (adder.getOutput(bit) == State.TRUE) {
                                sum |= (1 << bit);
                            }
                        }
                        State coutState = adder.getOutput(2);
                        int cout = (coutState == State.TRUE) ? 1 : 0;
                        
                        // Écrire les résultats dans le fichier
                        writer.write("Test A=" + getStateString(i) + 
                                ", B=" + getStateString(j) + 
                                ", Cin=" + cin + " => " +
                                "S=" + Integer.toBinaryString(sum) + 
                                ", Cout=" + cout + "\n");
                    }
                }
            }

            System.out.println("Les résultats ont été enregistrés dans le fichier 'test_results_2bits.txt'.");

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }

    // Convertit un indice en un état correspondant (0 = TRUE, 1 = FALSE, etc.)
    private static State getStateFromIndex(int index, State[] states) {
        return states[index];
    }

    // Génère une chaîne représentant les états binaires avec TRUE/FALSE/UNKNOWN/ERROR
    private static String getStateString(int value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            sb.append(getStateFromIndex((value >> i) & 1, State.values()));
            if (i < 1) sb.append(",");
        }
        return sb.toString();
    }
}