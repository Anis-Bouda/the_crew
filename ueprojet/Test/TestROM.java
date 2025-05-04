package projet;

public class TestROM {
    public static void main(String[] args) {
        System.out.println("===== TEST DE LA ROM (MÉMOIRE EN LECTURE SEULE) =====");

        State[][] romData = {
            {State.False, State.False, State.False, State.False}, 
            {State.True, State.False, State.False, State.False},  
            {State.False, State.True, State.False, State.False}, 
            {State.True, State.True, State.False, State.False},  
            {State.False, State.False, State.True, State.False},
            {State.True, State.False, State.True, State.False},  
            {State.False, State.True, State.True, State.False},  
            {State.True, State.True, State.True, State.False} 
        };

        ROM rom = new ROM("ROM1", 0, 0, 3, 4, romData);

        System.out.println("\n Contenu initial de la ROM :");
        rom.display();

        System.out.println("\n Lecture des valeurs :");
        testROM(rom, new State[]{State.False, State.False, State.False}); 
        testROM(rom, new State[]{State.True, State.False, State.False}); 
        testROM(rom, new State[]{State.False, State.True, State.False}); 
        testROM(rom, new State[]{State.True, State.True, State.False});  
        testROM(rom, new State[]{State.False, State.False, State.True}); 
        testROM(rom, new State[]{State.True, State.False, State.True});   
        testROM(rom, new State[]{State.False, State.True, State.True});   
        testROM(rom, new State[]{State.True, State.True, State.True}); 
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
