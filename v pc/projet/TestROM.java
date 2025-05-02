package projet;

public class TestROM {
    public static void main(String[] args) {
        System.out.println("===== TEST DE LA ROM (MÉMOIRE EN LECTURE SEULE) =====");

        // Initialisation des données en ROM
        State[][] romData = {
            {State.False, State.False, State.False, State.False}, // Adresse 0
            {State.True, State.False, State.False, State.False},  // Adresse 1
            {State.False, State.True, State.False, State.False},  // Adresse 2
            {State.True, State.True, State.False, State.False},   // Adresse 3
            {State.False, State.False, State.True, State.False},  // Adresse 4
            {State.True, State.False, State.True, State.False},   // Adresse 5
            {State.False, State.True, State.True, State.False},   // Adresse 6
            {State.True, State.True, State.True, State.False}     // Adresse 7
        };

        ROM rom = new ROM("ROM1", 0, 0, 3, 4, romData); // 3 bits d'adresse → 8 cases, 4 bits par mot

        System.out.println("\n🟢 Contenu initial de la ROM :");
        rom.display();

        System.out.println("\n🟢 Lecture des valeurs :");
        testROM(rom, new State[]{State.False, State.False, State.False}); // Lire adresse 0
        testROM(rom, new State[]{State.True, State.False, State.False});  // Lire adresse 1
        testROM(rom, new State[]{State.False, State.True, State.False});  // Lire adresse 2
        testROM(rom, new State[]{State.True, State.True, State.False});   // Lire adresse 3
        testROM(rom, new State[]{State.False, State.False, State.True});  // Lire adresse 4
        testROM(rom, new State[]{State.True, State.False, State.True});   // Lire adresse 5
        testROM(rom, new State[]{State.False, State.True, State.True});   // Lire adresse 6
        testROM(rom, new State[]{State.True, State.True, State.True});    // Lire adresse 7
    }

    public static void testROM(ROM rom, State[] inputs) {
        System.out.print("Entrée : ");
        for (int i = 0; i < inputs.length; i++) {
            rom.setInputs(i, inputs[i]);
            System.out.print(inputs[i] + " ");
        }

        rom.evaluate();

        System.out.print("\nSortie : ");
        for (int i = 0; i < rom.outputs.size(); i++) {
            System.out.print(rom.getOutput(i) + " ");
        }
        System.out.println("\n----------------------------------");
    }
}
