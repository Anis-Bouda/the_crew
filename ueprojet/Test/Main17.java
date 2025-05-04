package projet;

public class Main17 {
    public static void main(String[] args) {
        Circuit circuit = new Circuit();
        
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
        testROM(rom, new State[]{State.True, State.True, State.True});
        
        
        Transistor transistor1 = new Transistor("transistor1", 1, 1);
        Transistor transistor2 = new Transistor("transistor2", 1, 1);
        
      
        Probe probe = new Probe("probe", 3, 3);
        
      
        Tunnel tunnel = new Tunnel("tunnel", 4, 4, "label");
        
        Fil fil1 = new Fil("fil1", rom, transistor1, 0, 0);
        Fil fil2 = new Fil("fil2", rom, transistor1, 1, 1);
        Fil fil3 = new Fil("fil3", rom, transistor2, 0, 2);
        Fil fil4 = new Fil("fil4", rom, transistor2, 1, 3);
        Fil fil5 = new Fil("fil5", transistor1 , probe , 0, 0);
        Fil fil6 = new Fil("fil5", transistor2 , tunnel , 0, 0);
                                                                              
        circuit.addComposant(rom);
        circuit.addComposant(transistor1);
        circuit.addComposant(transistor2);
        circuit.addComposant(probe);
        circuit.addComposant(tunnel);
        
        circuit.addFil(fil1);
        circuit.addFil(fil2);
        circuit.addFil(fil3);
        circuit.addFil(fil4);
        circuit.addFil(fil5);
        circuit.addFil(fil6);

        System.out.println("===================================================================================");
        System.out.println("===============================État initial du circuit:============================");
        System.out.println("Les composants : ");
        System.out.println("================");
        circuit.afficheState();

        System.out.println("Les fils : ");
        System.out.println("============");
        for (int i = 0; i < circuit.fils.size(); i++) {
            System.out.println("Fil " + circuit.fils.get(i).getId() + " " + circuit.fils.get(i).getValue());
        }

        System.out.println("Les entrées et sorties des composants : ");
        System.out.println("========================================");
        for (int i = 0; i < circuit.composants.size(); i++) {
            System.out.println("Composant " + circuit.composants.get(i).getid() 
                    + " les entrées : " + circuit.composants.get(i).getInputs() 
                    + " les sorties : " + circuit.composants.get(i).getOutputs());
        }

        try {
            circuit.simulate(50); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("===================================================================================");
        System.out.println("=====================État du circuit après simulation:=============================");
        System.out.println("Les composants : ");
        System.out.println("================");
        circuit.afficheState();

        System.out.println("Les fils : ");
        System.out.println("============");
        for (int i = 0; i < circuit.fils.size(); i++) {
            circuit.fils.get(i).update();
            System.out.println("Fil " + circuit.fils.get(i).getId() + " " + circuit.fils.get(i).getValue());
        }

        System.out.println("Les entrées et sorties des composants : ");
        System.out.println("========================================");
        for (int i = 0; i < circuit.composants.size(); i++) {
            circuit.composants.get(i).evaluate();
            System.out.println("Composant " + circuit.composants.get(i).getid() 
                    + " les entrées : " + circuit.composants.get(i).getInputs() 
                    + " les sorties : " + circuit.composants.get(i).getOutputs());
        }}
        
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