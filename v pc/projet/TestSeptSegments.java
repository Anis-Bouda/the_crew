package projet;

public class TestSeptSegments {
    public static void main(String[] args) {
        Sept_Segments display = new Sept_Segments("7Seg", 0, 0);

        System.out.println("===== TEST DU DÉCODEUR 7 SEGMENTS =====");

        // Test 0000 (Affichage du chiffre 0)
        testDisplay(display, State.False, State.False, State.False, State.False, "0");

        // Test 0001 (Affichage du chiffre 1)
        testDisplay(display, State.False, State.False, State.False, State.True, "1");

        // Test 0010 (Affichage du chiffre 2)
        testDisplay(display, State.False, State.False, State.True, State.False, "2");

        // Test 0011 (Affichage du chiffre 3)
        testDisplay(display, State.False, State.False, State.True, State.True, "3");

        // Test 0100 (Affichage du chiffre 4)
        testDisplay(display, State.False, State.True, State.False, State.False, "4");

        // Test 0101 (Affichage du chiffre 5)
        testDisplay(display, State.False, State.True, State.False, State.True, "5");

        // Test 0110 (Affichage du chiffre 6)
        testDisplay(display, State.False, State.True, State.True, State.False, "6");

        // Test 0111 (Affichage du chiffre 7)
        testDisplay(display, State.False, State.True, State.True, State.True, "7");

        // Test 1000 (Affichage du chiffre 8)
        testDisplay(display, State.True, State.False, State.False, State.False, "8");

        // Test 1001 (Affichage du chiffre 9)
        testDisplay(display, State.True, State.False, State.False, State.True, "9");
        
        // Test 1101
        testDisplay(display, State.True, State.True, State.False, State.True, "UNKNOWN");
        
        // Test 1111
        testDisplay(display, State.True, State.True, State.True, State.True, "ERROR");
        
        // Test UNKNOWN
        testDisplay(display, State.UNKNOWN, State.False, State.False, State.False, "UNKNOWN");

        // Test ERROR
        testDisplay(display, State.ERROR, State.False, State.False, State.False, "ERROR");
    }

    private static void testDisplay(Sept_Segments display, State a, State b, State c, State d, String expected) {
        display.inputs.set(0, a);
        display.inputs.set(1, b);
        display.inputs.set(2, c);
        display.inputs.set(3, d);

        display.evaluate();

        System.out.print("Entrée : " + a + b + c + d + " -> Sortie : ");
        for (State output : display.outputs) {
            System.out.print(output + " ");
        }
        System.out.println(" | Attendu : " + expected);
    }
}
