package projet;

public class Main2 {
    public static void main(String[] args) {

        Circuit circuit = new Circuit();

        State input1 = State.True;  
        ExtensionType extn = ExtensionType.ZERO;
        
        Splitter SPL = new Splitter("SPL", 0, 1, 4);
        SPL.setInputs(0, State.False);
        
        OU ou = new OU("ou", 0, 0);
        NON non = new NON("non", 2, 2);
        NOR nor = new NOR("nor", 2, 2);
        nor.setInputs(0, State.True);
        
        BitExtender bit = new BitExtender("bit", 3, 3, 2, 5, extn);
        testExtension(bit, new State[]{State.True});

        Constant cons = new Constant("cons",1,1, input1);
        
        Fil fil1 = new Fil("fil1", SPL, ou, 0, 0);  
        Fil fil2 = new Fil("fil2", cons, ou, 1, 0);  
        Fil fil3 = new Fil("fil3", SPL, non, 0, 1);
        Fil fil4 = new Fil("fil4", SPL, nor, 1, 2);
        Fil fil5 = new Fil("fil5", SPL, bit, 1, 3);
        
        circuit.addComposant(SPL);
        circuit.addComposant(non);
        circuit.addComposant(nor);
        circuit.addComposant(cons);
        circuit.addComposant(ou);
        circuit.addComposant(bit);


        circuit.addFil(fil1);
        circuit.addFil(fil2);
        circuit.addFil(fil3);
        circuit.addFil(fil4);
        circuit.addFil(fil5);

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
        }
 }
    
    public static void testExtension(BitExtender bitExtender, State[] inputs) {
        System.out.print("Entrée : ");
        for (int i = 0; i < inputs.length; i++) {
            bitExtender.setInputs(i, inputs[i]);
            System.out.print(inputs[i] + " ");
        }

        bitExtender.evaluate();

        System.out.print("\nSortie : ");
        for (int i = 0; i < bitExtender.outputs.size(); i++) {
            System.out.print(bitExtender.getOutput(i) + " ");
        }
        System.out.println("\n----------------------------------");
        

    }
}
