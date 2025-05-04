package projet;

import java.util.Arrays;

public class TestMULTPLAY {
    public static void main(String[] args) {
        testMultiplication(2, new State[]{State.True, State.False, State.True, State.True}, "Multiplication 3 × 3");  
        testMultiplication(2, new State[]{State.False, State.False, State.True, State.True}, "Multiplication 0 × 3");
        testMultiplication(3, new State[]{State.True, State.True, State.True, State.False, State.True, State.True}, "Multiplication 7 × 3");
        testMultiplication(3, new State[]{State.UNKNOWN, State.True, State.False, State.True, State.False, State.False}, "Multiplication avec UNKNOWN");
        testMultiplication(3, new State[]{State.ERROR, State.True, State.False, State.True, State.False, State.False}, "Multiplication avec ERROR");
    }

    public static void testMultiplication(int n, State[] inputs, String testName) {
        MULTPLAY multiplier = new MULTPLAY("MultiplierTest", n, 0, 0);
        for (int i = 0; i < inputs.length; i++) {
            multiplier.inputs.set(i, inputs[i]);
        }
        
        multiplier.evaluate();
        
        System.out.println("Test : " + testName);
        System.out.print("Entrée : " + Arrays.toString(inputs) + "\n");
        System.out.print("Sortie obtenue : " + Arrays.toString(multiplier.outputs.toArray()) + "\n");
        System.out.println("------------------------------------");
    }
}
