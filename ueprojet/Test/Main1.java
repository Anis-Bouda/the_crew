package projet;

public class Main1 {
    public static void main(String[] args) {

        Circuit circuit = new Circuit();


        State input1 = State.True;  
        

        BasculeD bascule = new BasculeD("bascule", 0, 2);
        

        Clock CLK = new Clock("CLK",0, 0, 5);
        CLK.setOutputs(0, State.True);
        
  
        Lampe lmp = new Lampe("lmp", 1,1);

        
 
        Constant cons = new Constant("cons",1,1, input1);
        

        Fil fil1 = new Fil("fil1", CLK, bascule, 1, 0);  
        Fil fil2 = new Fil("fil2", cons, bascule, 0, 0);  
        Fil fil3 = new Fil("fil3", bascule, lmp, 0, 0);
        

        circuit.addComposant(CLK);
        circuit.addComposant(cons);
        circuit.addComposant(bascule);
        circuit.addComposant(lmp);


        circuit.addFil(fil1);
        circuit.addFil(fil2);
        circuit.addFil(fil3);


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
