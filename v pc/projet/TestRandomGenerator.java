package projet;

public class TestRandomGenerator {
    public static void main(String[] args) {
        System.out.println("===== TEST DU RANDOM GENERATOR =====");

        // Test avec l'entrée True (génère des sorties aléatoires)
        testRandomGenerator(4, new State[]{State.True}, "Test avec activation (True)");

        // Test avec l'entrée False (devrait lever une exception)
        testRandomGenerator(4, new State[]{State.False}, "Test avec désactivation (False)");

        // Test avec l'entrée ERROR (devrait lever une exception)
        testRandomGenerator(4, new State[]{State.ERROR}, "Test avec erreur (ERROR)");

        // Test avec l'entrée UNKNOWN (devrait lever une exception)
        testRandomGenerator(4, new State[]{State.UNKNOWN}, "Test avec entrée inconnue (UNKNOWN)");

    }

    public static void testRandomGenerator(int n, State[] inputs, String description) {
        try {
            RandomGenerator generator = new RandomGenerator("RandomGen", n, 0, 0);
            
            // Remplir les entrées du générateur
            for (int i = 0; i < inputs.length; i++) {
                generator.inputs.set(i, inputs[i]);
            }

            // Appeler la méthode evaluate pour générer les sorties
            generator.evaluate();

            // Afficher le résultat
            System.out.println("\n🟢 " + description);
            System.out.print("Sorties générées : ");
            for (State s : generator.outputs) {
                System.out.print(s + " ");
            }
            System.out.println();

        } catch (IllegalStateException e) {
            // Gérer les exceptions et afficher les messages d'erreur
            System.out.println("\n🔴 " + description + " - Erreur : " + e.getMessage());
        }
    }
}

