package projet;

public class TestBitExtender {
    public static void main(String[] args) {
        System.out.println("===== TEST DU BIT EXTENDER =====");

        BitExtender bitExtenderZero = new BitExtender("ExtZero", 0, 0, 4, 11, ExtensionType.ZERO);
        BitExtender bitExtenderOne = new BitExtender("ExtOne", 0, 0, 6, 11, ExtensionType.ONE);
        BitExtender bitExtenderSign = new BitExtender("ExtSign", 0, 0, 4, 8, ExtensionType.SIGN);

        System.out.println("\n🟢 Test Extension ZERO (ajout de 0) ");
        testExtension(bitExtenderZero, new State[]{State.True, State.False, State.True, State.False});

        System.out.println("\n🟢 Test Extension ONE (ajout de 1) ");
        testExtension(bitExtenderOne, new State[]{State.False,State.False, State.False, State.True, State.False,State.False});

        System.out.println("\n🟢 Test Extension SIGN (bit de gauche répété) ");
        testExtension(bitExtenderSign, new State[]{State.True, State.False, State.True, State.True});

        System.out.println("\n🟢 Test Extension SIGN (bit de gauche = 0) ");
        testExtension(bitExtenderSign, new State[]{State.False, State.False, State.True, State.False});

        System.out.println("\n🟢 Test Propagation UNKNOWN ");
        testExtension(bitExtenderZero, new State[]{State.UNKNOWN, State.False, State.True, State.False});

        System.out.println("\n🟢 Test Propagation ERROR ");
        testExtension(bitExtenderZero, new State[]{State.ERROR, State.False, State.True, State.False});
    }

    public static void testExtension(BitExtender bitExtender, State[] inputs) {
        System.out.print("Entrée : ");
        for (int i = 0; i < inputs.length; i++) {
            bitExtender.setInputs(i, inputs[i]);
            System.out.print(inputs[i] + " ");
        }

        bitExtender.evaluate();

        System.out.print("\nSortie : ");
        for (int i = 0; i < bitExtender.outputs.size(); i++) {
            System.out.print(bitExtender.getOutput(i) + " ");
        }
        System.out.println("\n----------------------------------");
    }
}
