package projet;

public class TestDIVIDER {
    public static void main(String[] args) {
        System.out.println("===== TEST DU DIVISEUR =====");

        testDivision(3, new State[]{State.True, State.True, State.False, State.True, State.False, State.False}, "Division 3 / 2");  // 3 / 2 = 1, reste 1  -> Quotient: 001, Reste: 001
        testDivision(3, new State[]{State.True, State.True, State.True, State.True, State.False, State.False}, "Division 7 / 2");  // 7 / 2 = 3, reste 1  -> Quotient: 011, Reste: 001
        testDivision(3, new State[]{State.True, State.True, State.False, State.False, State.False, State.False}, "Division 3 / 0 (Erreur attendue)");  // Division par 0 -> ERROR
        testDivision(3, new State[]{State.UNKNOWN, State.True, State.False, State.True, State.False, State.False}, "Division avec UNKNOWN");  // Résultat: UNKNOWN
        testDivision(3, new State[]{State.ERROR, State.True, State.False, State.True, State.False, State.False}, "Division avec ERROR");  // Résultat: ERROR
    }

    public static void testDivision(int n, State[] inputs, String description) {
        DIVIDER divider = new DIVIDER("DIV", n, 0, 0);
        
        for (int i = 0; i < inputs.length; i++) {
            divider.inputs.set(i, inputs[i]);
        }

        divider.evaluate();
        
        System.out.println("\n🟢 " + description);
        System.out.print("Sortie obtenue : ");
        for (State s : divider.outputs) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
