package projet;

public class TestSHIFTER {
    public static void main(String[] args) {
        System.out.println("===== TEST DU SHIFTER =====");

        // Test 1 : Décalage à gauche (Shift Left)
        SHIFTER shifter1 = new SHIFTER("SHIFTER1", 4, 0, 0);
        System.out.println("\n🟢 Test 1 : Décalage à gauche (Shift Left)");

        // Entrée : 1010 (A = 1010) avec shift left
        State[] inputs1 = new State[] { 
            State.True, State.False, State.True, State.False, // A = 1010
            State.True }; // Shift left = True
        testShifter(shifter1, inputs1); // Décalage à gauche -> Sortie attendue : 0100

        // Test 2 : Décalage à droite (Shift Right)
        SHIFTER shifter2 = new SHIFTER("SHIFTER2", 4, 0, 0);
        System.out.println("\n🟢 Test 2 : Décalage à droite (Shift Right)");

        // Entrée : 1010 (A = 1010) avec shift right
        State[] inputs2 = new State[] { 
            State.True, State.False, State.True, State.False, // A = 1010
            State.False }; // Shift left = False, donc shift right
        testShifter(shifter2, inputs2); // Décalage à droite -> Sortie attendue : 0100

        // Test 3 : Entrée avec erreur
        SHIFTER shifter3 = new SHIFTER("SHIFTER3", 4, 0, 0);
        System.out.println("\n🟢 Test 3 : Entrée avec erreur");

        // Entrée : erreur dans A
        State[] inputs3 = new State[] { 
            State.ERROR, State.False, State.True, State.False, // A = erreur
            State.True }; // Shift left = True
        testShifter(shifter3, inputs3); // Entrée avec erreur -> Sortie ERROR

        // Test 4 : Entrée avec valeur inconnue
        SHIFTER shifter4 = new SHIFTER("SHIFTER4", 4, 0, 0);
        System.out.println("\n🟢 Test 4 : Entrée avec valeur inconnue");

        // Entrée : inconnue dans A
        State[] inputs4 = new State[] { 
            State.UNKNOWN, State.True, State.False, State.False, // A = UNKNOWN
            State.True }; // Shift left = True
        testShifter(shifter4, inputs4); // Entrée inconnue -> Sortie UNKNOWN

        // Test 5 : Test de décalage avec une autre taille
        SHIFTER shifter5 = new SHIFTER("SHIFTER5", 6, 0, 0);
        System.out.println("\n🟢 Test 5 : Décalage à gauche pour n = 6");

        // Entrée : 101101 (A = 101101) avec shift left
        State[] inputs5 = new State[] { 
            State.True, State.False, State.True, State.True, State.False, State.True, // A = 101101
            State.True }; // Shift left = True
        testShifter(shifter5, inputs5); // Décalage à gauche -> Sortie attendue : 011010
    }

    // Fonction pour tester le SHIFTER
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
