package projet;

public class TestRandomGenerator {
    public static void main(String[] args) {
        System.out.println("===== TEST DU RANDOM GENERATOR =====");
        testRandomGenerator(4, new State[]{State.True}, "Test avec activation (True)");
        testRandomGenerator(4, new State[]{State.False}, "Test avec désactivation (False)");
        testRandomGenerator(4, new State[]{State.ERROR}, "Test avec erreur (ERROR)");
        testRandomGenerator(4, new State[]{State.UNKNOWN}, "Test avec entrée inconnue (UNKNOWN)");

    }

    public static void testRandomGenerator(int n, State[] inputs, String description) {
        try {
            RandomGenerator generator = new RandomGenerator("RandomGen", n, 0, 0);
            for (int i = 0; i < inputs.length; i++) {
                generator.inputs.set(i, inputs[i]);
            }
            generator.evaluate();
            System.out.println("\n " + description);
            System.out.print("Sorties générées : ");
            for (State s : generator.outputs) {
                System.out.print(s + " ");
            }
            System.out.println();

        } catch (IllegalStateException e) {
            System.out.println("\n " + description + " - Erreur : " + e.getMessage());
        }
    }
}

