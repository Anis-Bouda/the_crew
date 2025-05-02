package projet;

public class TestRAM {
    public static void main(String[] args) {
        System.out.println("===== TEST DE LA RAM (MATRICE) =====");
        
        RAM ram = new RAM("RAM1", 0, 0, 3, 4); // 3 bits d'adresse → 8 cases, 4 bits par mot

        System.out.println("\n🟢 État initial de la RAM :");
        ram.display(); 

        System.out.println("\n🟢 Écriture en mémoire :");
        // Écrit 1011 à l'adresse 0
        testRAM(ram, new State[]{State.False, State.False, State.False, State.True, State.False, State.True, State.False, State.True}); 

        // Écrit 0101 à l'adresse 5
        testRAM(ram, new State[]{State.True, State.False, State.True, State.False, State.True, State.False, State.True, State.True});

        System.out.println("\n🟢 Lecture des valeurs :");
        // Lire adresse 0 (doit afficher 1011)
        testRAM(ram, new State[]{State.False, State.False, State.False, State.False, State.False, State.False, State.False, State.False});

        // Lire adresse 1 (doit afficher 0101)
        testRAM(ram, new State[]{State.True, State.False, State.False, State.False, State.False, State.False, State.False, State.False}); 

        System.out.println("\n🟢 Test :");
        // Lire adresse 7 (valide)
        testRAM(ram, new State[]{State.True, State.True, State.True, State.False, State.False, State.False, State.False, State.False}); 
    }
    
    public static void testRAM(RAM ram, State[] inputs) {
        System.out.print("Entrée : ");
        for (int i = 0; i < inputs.length; i++) {
            ram.setInputs(i, inputs[i]);
            System.out.print(inputs[i] + " ");
        }

        ram.evaluate();

        System.out.print("\nSortie : ");
        for (int i = 0; i < ram.outputs.size(); i++) {
            System.out.print(ram.getOutput(i) + " ");
        }
        System.out.println("\n----------------------------------");

        ram.display();
    }
}
