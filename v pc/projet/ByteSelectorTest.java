package projet;

public class ByteSelectorTest {
    public static void main(String[] args) {
        System.out.println("===== TEST DU BYTE SELECTOR =====");

        // Crée une instance du ByteSelector
        ByteSelctor byteSelector = new ByteSelctor("ByteSel", 0, 0);

        System.out.println("\n🟢 Test avec entrées toutes à FALSE ");
        testByteSelector(byteSelector, new State[]{
                State.True, State.False, State.False, State.False, 
                State.True, State.False, State.False, State.False});

        System.out.println("\n🟢 Test avec entrée à TRUE à l'indice 0 ");
        testByteSelector(byteSelector, new State[]{
                State.True, State.False, State.False, State.False, 
                State.False, State.False, State.False, State.False});

        System.out.println("\n🟢 Test avec entrée à TRUE à l'indice 1 ");
        testByteSelector(byteSelector, new State[]{
                State.False, State.True, State.False, State.False, 
                State.False, State.False, State.False, State.False});

        System.out.println("\n🟢 Test avec entrée à TRUE à l'indice 2 ");
        testByteSelector(byteSelector, new State[]{
                State.False, State.False, State.True, State.False, 
                State.False, State.False, State.False, State.False});

        System.out.println("\n🟢 Test avec entrée à TRUE à l'indice 3 ");
        testByteSelector(byteSelector, new State[]{
                State.False, State.False, State.False, State.True, 
                State.False, State.False, State.False, State.False});

        System.out.println("\n🟢 Test avec entrée à TRUE à l'indice 4 ");
        testByteSelector(byteSelector, new State[]{
                State.False, State.False, State.False, State.False, 
                State.True, State.False, State.False, State.False});

        System.out.println("\n🟢 Test avec entrée à TRUE à l'indice 5 ");
        testByteSelector(byteSelector, new State[]{
                State.False, State.False, State.False, State.False, 
                State.False, State.True, State.False, State.False});

        System.out.println("\n🟢 Test avec entrée à TRUE à l'indice 6 ");
        testByteSelector(byteSelector, new State[]{
                State.False, State.False, State.False, State.False, 
                State.False, State.False, State.True, State.False});

        System.out.println("\n🟢 Test avec entrée à TRUE à l'indice 7 ");
        testByteSelector(byteSelector, new State[]{
                State.False, State.False, State.False, State.False, 
                State.False, State.False, State.False, State.True});
        
        System.out.println("\n🟢 Test avec entrée à TRUE à l'indice 3 et 2 ");
        testByteSelector(byteSelector, new State[]{
                State.False, State.True, State.True, State.False, 
                State.False, State.False, State.False, State.True});
        
        System.out.println("\n🟢 Test avec entrée à TRUE à l'indice 1 et 2 ");
        testByteSelector(byteSelector, new State[]{
                State.True, State.True, State.False, State.False, 
                State.False, State.False, State.True, State.True});

        System.out.println("\n🟢 Test avec une entrée ERROR ");
        testByteSelector(byteSelector, new State[]{
                State.ERROR, State.False, State.False, State.False, 
                State.False, State.False, State.False, State.False});

        System.out.println("\n🟢 Test avec une entrée UNKNOWN ");
        testByteSelector(byteSelector, new State[]{
                State.UNKNOWN, State.False, State.False, State.False, 
                State.False, State.False, State.False, State.False});
    }

    public static void testByteSelector(ByteSelctor byteSelector, State[] inputs) {
        System.out.print("Entrée : ");
        for (int i = 0; i < inputs.length; i++) {
            byteSelector.setInputs(i, inputs[i]);
            System.out.print(inputs[i] + " ");
        }

        byteSelector.evaluate();

        System.out.print("\nSortie : ");
        for (int i = 0; i < byteSelector.outputs.size(); i++) {
            System.out.print(byteSelector.getOutput(i) + " ");
        }
        System.out.println("\n----------------------------------");
    }
}
