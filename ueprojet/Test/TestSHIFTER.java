package projet;

public class TestSHIFTER {
    public static void main(String[] args) {
        System.out.println("===== TEST DU SHIFTER =====");
        SHIFTER shifter1 = new SHIFTER("SHIFTER1", 4, 0, 0);
        System.out.println("\n Test 1 : Décalage à gauche (Shift Left)");
        State[] inputs1 = new State[] { 
            State.True, State.False, State.True, State.False,
            State.True };
        testShifter(shifter1, inputs1);

        SHIFTER shifter2 = new SHIFTER("SHIFTER2", 4, 0, 0);
        System.out.println("\n Test 2 : Décalage à droite (Shift Right)");

        State[] inputs2 = new State[] { 
            State.True, State.False, State.True, State.False,
            State.False };
        testShifter(shifter2, inputs2);

        
        SHIFTER shifter3 = new SHIFTER("SHIFTER3", 4, 0, 0);
        System.out.println("\n Test 3 : Entrée avec erreur");

        State[] inputs3 = new State[] { 
            State.ERROR, State.False, State.True, State.False,
            State.True }; 
        testShifter(shifter3, inputs3);
        SHIFTER shifter4 = new SHIFTER("SHIFTER4", 4, 0, 0);
        System.out.println("\n Test 4 : Entrée avec valeur inconnue");

        State[] inputs4 = new State[] { 
            State.UNKNOWN, State.True, State.False, State.False, 
            State.True }; 
        testShifter(shifter4, inputs4);

        
        SHIFTER shifter5 = new SHIFTER("SHIFTER5", 6, 0, 0);
        System.out.println("\n Test 5 : Décalage à gauche pour n = 6");

      
        State[] inputs5 = new State[] { 
            State.True, State.False, State.True, State.True, State.False, State.True,
            State.True }; 
        testShifter(shifter5, inputs5); 
    }

    public static void testShifter(SHIFTER shifter, State[] inputs) {
        System.out.print("Entrée : ");
        for (int i = 0; i < inputs.length; i++) {
            shifter.setInputs(i, inputs[i]);
            System.out.print(inputs[i] + " ");
        }

        shifter.evaluate();

        System.out.print("\nSortie : ");
        for (int i = 0; i < shifter.outputs.size(); i++) {
            System.out.print(shifter.getOutput(i) + " ");
        }
        System.out.println("\n----------------------------------");
    }
}
