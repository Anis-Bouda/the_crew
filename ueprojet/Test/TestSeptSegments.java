package projet;

public class TestSeptSegments {
    public static void main(String[] args) {
        Sept_Segments display = new Sept_Segments("7Seg", 0, 0);

        System.out.println("===== TEST DU DÉCODEUR 7 SEGMENTS =====");

        testDisplay(display, State.False, State.False, State.False, State.False, "0");
        testDisplay(display, State.False, State.False, State.False, State.True, "1");
        testDisplay(display, State.False, State.False, State.True, State.False, "2");
        testDisplay(display, State.False, State.False, State.True, State.True, "3");
        testDisplay(display, State.False, State.True, State.False, State.False, "4");
        testDisplay(display, State.False, State.True, State.False, State.True, "5");
        testDisplay(display, State.False, State.True, State.True, State.False, "6");
        testDisplay(display, State.False, State.True, State.True, State.True, "7");
        testDisplay(display, State.True, State.False, State.False, State.False, "8");
        testDisplay(display, State.True, State.False, State.False, State.True, "9");
        testDisplay(display, State.True, State.True, State.False, State.True, "UNKNOWN");
 
        testDisplay(display, State.True, State.True, State.True, State.True, "ERROR");

        testDisplay(display, State.UNKNOWN, State.False, State.False, State.False, "UNKNOWN");
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
