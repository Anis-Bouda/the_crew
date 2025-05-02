package projet;

public class Main17 {
    public static void main(String[] args) {
        // Création du circuit
        Circuit circuit = new Circuit();
        
        // Création de la ROM
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
        testROM(rom, new State[]{State.True, State.True, State.True});    // Lire adresse 7
        
        // Création de transistor
        Transistor transistor1 = new Transistor("transistor1", 1, 1);
        Transistor transistor2 = new Transistor("transistor2", 1, 1);
        
        // creation du probe 
        Probe probe = new Probe("probe", 3, 3);
        
        // creation du Tunnel
        Tunnel tunnel = new Tunnel("tunnel", 4, 4, "label");
        
        // Création des fils reliant les composants
        Fil fil1 = new Fil("fil1", rom, transistor1, 0, 0);
        Fil fil2 = new Fil("fil2", rom, transistor1, 1, 1);
        Fil fil3 = new Fil("fil3", rom, transistor2, 0, 2);
        Fil fil4 = new Fil("fil4", rom, transistor2, 1, 3);
        Fil fil5 = new Fil("fil5", transistor1 , probe , 0, 0);
        Fil fil6 = new Fil("fil5", transistor2 , tunnel , 0, 0);
                                                                              
        // Ajout des composants au circuit
        circuit.addComposant(rom);
        circuit.addComposant(transistor1);
        circuit.addComposant(transistor2);
        circuit.addComposant(probe);
        circuit.addComposant(tunnel);
        
        
        // Ajout des fils au circuit
        circuit.addFil(fil1);
        circuit.addFil(fil2);
        circuit.addFil(fil3);
        circuit.addFil(fil4);
        circuit.addFil(fil5);
        circuit.addFil(fil6);

       
        // Affichage de l'état initial
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

        // Simulation du circuit
        try {
            circuit.simulate(50);  // Max 50 itérations
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Affichage de l'état après simulation
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