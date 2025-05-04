package projet;

public class Main7 {
    public static void main(String[] args) {

        Circuit circuit = new Circuit();

        RAM ram = new RAM("ram", 0, 0, 3, 4);
        testRAM(ram, new State[]{State.False, State.False, State.False, State.True, State.False, State.True, State.False, State.True}); 

        Constant cons = new Constant("cons", 0, 0, State.True); 

        SHIFTER shft = new SHIFTER("shft",4, 0, 0);
        shft.setInputs(4, State.True);
 
        Fil fil1 = new Fil("fil1", ram, shft, 0, 0);
        Fil fil2 = new Fil("fil2", ram, shft, 1, 1);
        Fil fil3 = new Fil("fil3", ram, shft, 2, 2);
        Fil fil4 = new Fil("fil4", ram, shft, 3, 3);

        circuit.addComposant(ram);
        circuit.addComposant(cons);
        circuit.addComposant(shft);

        circuit.addFil(fil1);
        circuit.addFil(fil2);
        circuit.addFil(fil3);
        circuit.addFil(fil4);

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
        
        ram.display();

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
        }
        
        ram.display();
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
